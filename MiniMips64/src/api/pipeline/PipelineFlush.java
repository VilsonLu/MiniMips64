package api.pipeline;

import api.instruction.Instruction;
import api.instruction.branch.BranchInstruction;

public class PipelineFlush extends BranchStrategy {
	
	public PipelineFlush(Pipeline pipeline) {
		super(pipeline);
	}

	
	@Override
	void propagate() {
		Pipeline pipeline = this.getPipeline();
		pipeline.moveRegistersForward();
		
		Instruction id = pipeline.getID();
		if (!(id instanceof BranchInstruction)) {
			pipeline.setID(null);
		}
		pipeline.runPipeline();
		
		this.checkIfWillBranch();
	}


	@Override
	void performWillBranch() {
		
	}


	@Override
	void performWillNotBranch() {
		
	}

}
