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
	
	
	private boolean isDone;
	private Pipeline pipeline;
	private boolean willBranch = false;
	
	
	public BranchStrategy(Pipeline pipeline) {
		this.pipeline = pipeline;
	}
	
	
	public boolean isDone() {
		return isDone;
	}
	
	
	protected boolean willBranch() {
		return willBranch;
	}
	
	void propagate() {
		pipeline.moveRegistersForward();
		
		Instruction mem = pipeline.getMem();
		if (mem != null && mem instanceof BranchInstruction) {
			RegisterMgr regs = RegisterMgr.getInstance();
			long cond = regs.getValue(RegisterMgr.EX_MEM_COND);
			if (cond == 1) {
				willBranch = true;
			}
		}
		
		Instruction wb = pipeline.getWb();
		if (wb != null && wb instanceof BranchInstruction) {
			isDone = true;
			if (willBranch) {
				doneBranch();
			} else {
				doneNotBranch();
			}
		} else {
			this.notDone();
		}
	}

	
	protected Pipeline getPipeline() {
		return pipeline;
	}
	
	abstract void notDone();
	abstract void doneBranch();
	abstract void doneNotBranch();
}
