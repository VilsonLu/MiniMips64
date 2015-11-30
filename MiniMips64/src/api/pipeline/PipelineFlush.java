package api.pipeline;

import api.instruction.Instruction;
import api.instruction.InstructionMgr;
import api.register.RegisterMgr;

public class PipelineFlush extends BranchStrategy {
	
	public PipelineFlush(Pipeline pipeline) {
		super(pipeline);
	}

	
	@Override
	void notDone() {
		Pipeline pipeline = this.getPipeline();
		pipeline.setID(null);
		Instruction if_ = pipeline.getNextInstruction();
		pipeline.setIF(if_);
		pipeline.runPipeline();
	}


	@Override
	void doneBranch() {
		Pipeline pipeline = this.getPipeline();
		Instruction if_ = pipeline.getNextInstruction();
		RegisterMgr regs = RegisterMgr.getInstance();
		pipeline.setID(null);
		pipeline.runPipeline();
	}


	@Override
	void doneNotBranch() {
		Pipeline pipeline = this.getPipeline();
		
		RegisterMgr regs = RegisterMgr.getInstance();
		InstructionMgr insts = InstructionMgr.getInstance();
		
		int index = (int) regs.getPc() / 4 - 3;
		regs.setValue(RegisterMgr.PC, index * 4);
		Instruction if_ = insts.getInstruction(index);
		pipeline.setID(null);
		pipeline.setIF(if_);
		pipeline.runPipeline();
		
	}
}
