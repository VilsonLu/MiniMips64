package api.pipeline;

import api.instruction.Instruction;
import api.instruction.branch.BranchInstruction;

public class PipelineFlush extends BranchStrategy {
	
	public PipelineFlush(Pipeline pipeline) {
		super(pipeline);
	}

	
	@Override
	void notDone() {
		Pipeline pipeline = this.getPipeline();
		pipeline.moveRegistersForward();
		
		Instruction id = pipeline.getID();
		if (!(id instanceof BranchInstruction)) {
			pipeline.setID(null);
		}
		pipeline.runPipeline();
		
		
	}


	@Override
	void doneBranch() {
		this.notDone();
	}


	@Override
	void doneNotBranch() {
		this.notDone();
	}

}
