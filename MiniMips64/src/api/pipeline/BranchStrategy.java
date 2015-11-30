package api.pipeline;

import api.instruction.Instruction;
import api.instruction.branch.BranchInstruction;
import api.register.RegisterMgr;

public abstract class BranchStrategy {
	public enum StrategyType {
		PIPELINE_FLUSH, PIPELINE_FREEZE, PREDICT_NOT_TAKEN, PIPELINE2
	}
	
	
	public static BranchStrategy getNewStrategy(Pipeline pipeline) {
		return new PipelineFlush(pipeline);
	}
	
	
	private boolean isDoneChecking;
	private Pipeline pipeline;
	private boolean willBranch;
	
	// internal pipeline
	protected Instruction if_;
	protected Instruction id;
	protected Instruction ex;
	protected Instruction mem; 
	protected Instruction wb;
	
	
	public BranchStrategy(Pipeline pipeline) {
		this.pipeline = pipeline;
	}
	
	
	public boolean isDoneChecking() {
		return isDoneChecking;
	}
	
	
	protected boolean willBranch() {
		return willBranch;
	}
	
	
	protected void checkIfWillBranch() {
		Instruction ex = pipeline.getEX();
		if (ex instanceof BranchInstruction) {
			isDoneChecking = true;
			RegisterMgr regs = RegisterMgr.getInstance();
			long cond = regs.getValue(RegisterMgr.EX_MEM_COND);
			if (cond == 0) {
				willBranch = true;
			}
		}
	}

	
	protected Pipeline getPipeline() {
		return pipeline;
	}
	
	abstract void propagate();
	abstract void performWillBranch();
	abstract void performWillNotBranch();
}
