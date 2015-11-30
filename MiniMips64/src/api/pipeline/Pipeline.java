package api.pipeline;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import api.instruction.Instruction;
import api.instruction.InstructionMgr;
import api.instruction.alu.AddS;
import api.instruction.alu.MulS;
import api.instruction.branch.BranchInstruction;
import api.register.RegisterMgr;

public class Pipeline {
	private final int FP_ADD_CYCLES = 4;
	private final int FP_MULT_CYCLES = 8;
	
	private Instruction if_; // because if is a reserved keyword and I don't want to use capital letters for variables.
	private Instruction id;
	private Instruction ex;
	private Instruction mem;
	private Instruction wb;
	private Instruction[] fpadds;
	private Instruction[] fpmults;

	private long cycle = 0;
	private boolean stall = false;
	private boolean branchActive;
	private Exec lastExec = Exec.EX;
	private BranchStrategy strategy;
	
	private enum Exec {
		EX, FP_ADD, FP_MULT
	}
	
	
	public Pipeline() {
		fpadds = new Instruction[FP_ADD_CYCLES];
		fpmults = new Instruction[FP_MULT_CYCLES];
	}
	
	
	public void printContents() {
		System.out.println("-------");
		System.out.print("IF: ");
		if (if_ != null) {
			System.out.println(if_.getStringCode() + ": " + if_.getId());
		} else {
			System.out.println();
		}
		
		System.out.print("ID: ");
		if (id != null) {
			System.out.println(id.getStringCode() + ": " + id.getId());
		} else {
			System.out.println();
		}
		
		System.out.print("EX: ");
		if (ex != null) {
			System.out.println(ex.getStringCode() + ": " + ex.getId());
		} else {
			System.out.println();
		}
		
		System.out.print("MEM: ");
		if (mem != null) {
			System.out.println(mem.getStringCode() + ": " + mem.getId());
		} else {
			System.out.println();
		}
		
		System.out.print("WB: ");
		if (wb != null) {
			System.out.println(wb.getStringCode() + ": " + wb.getId());
		} else {
			System.out.println();
		}
	}
	
	
	public void performCycle() {
		RegisterMgr regs = RegisterMgr.getInstance();
		regs.cloneInternalRegs();
		
		if (branchActive) {
			this.performBranchCycle();
		} else {
			this.performDefaultCycle();
		}
		cycle++;
	}
	
	
	private void performBranchCycle() {
		if (strategy == null) {
			strategy = new PipelineFlush(this);
		}
		strategy.propagate();
		if (strategy.isDone()) {
			strategy = null;
			stall = false;
			branchActive = false;
		} 
	}
	
	
	void performDefaultCycle() {
		this.moveRegistersForward();
		this.runPipeline();
		this.checkForStall();
	}
	
	
	void moveRegistersForward() {
		wb = mem;
		
		// move last executed instruction to MEM 
		switch (lastExec) {
		case EX:
			mem = ex;
			ex = null;
			break;
		case FP_ADD:
			mem = fpadds[FP_ADD_CYCLES - 1];
			fpadds[FP_ADD_CYCLES - 1] = null;
			break;
		case FP_MULT:
			mem = fpmults[FP_MULT_CYCLES - 1];
			fpmults[FP_MULT_CYCLES - 1] = null;
			break;
		}
		
		// move FP ADDs
		for (int i = FP_ADD_CYCLES; i == 0; i--) {
			if (fpadds[i] == null) {
				fpadds[i] = fpadds[i - 1];
				fpadds[i - 1] = null;
			}
		}
		
		// move FP MULs
		for (int i = FP_MULT_CYCLES; i == 0; i--) {
			if (fpmults[i] == null) {
				fpmults[i] = fpmults[i - 1];
				fpmults[i - 1] = null;
			}
		}
		
		if (!stall) {
			// move ID to appropriate Execution stage
			if (id instanceof AddS) {
				if (fpadds[0] == null) { // don't combine with the above expression!  
					fpadds[0] = id;	
				}
			} else if (id instanceof MulS) {
				if (fpmults[0] == null) {
					fpmults[0] = id;	
				}
			} else if (ex == null) {
				ex = id;
			} else {
				stall = true; // stall because appropriate Execution stage is full 
			}
			
			if (!stall) {
				id = if_;
				if_ = this.getNextInstruction();
				if (id instanceof BranchInstruction && !branchActive) {
					branchActive = true;
					return;
				} 
			}
		} else if (branchActive) {
			ex = id; 
		}
	}
	
	
	Instruction getIF() {
		return if_;
	}
	
	
	void setIF(Instruction if_) {
		this.if_ = if_;
	}
	
	
	Instruction getID() {
		return id;
	}
	
	
	void setID(Instruction id) {
		this.id = id;
	}
	
	Instruction getEx() {
		return ex;
	}
	
	
	Instruction getMem() {
		return mem;
	}
	
	Instruction getWb() {
		return wb;
	}
	
	
	void checkForStall() {
		List<String> inputs = null;
		stall = false;
		if (id != null) {
			inputs = id.getInputs();
		} else {
			return;
		}
		 
		if (ex != null) {
			stall = ex.outputsTo(inputs);
		}
		if (!stall && mem != null) {
			stall = mem.outputsTo(inputs);
		}
//		if (!stall && wb != null) {
//			stall = wb.outputsTo(inputs);
//		}
		if (!stall) {
			for (int i = 0; i < FP_ADD_CYCLES; i++) {
				Instruction fpadd = fpadds[i];
				if (fpadd == null)  
					continue;
				stall = fpadd.outputsTo(inputs);
				if (stall) break;
			}
		}
		if (!stall) {
			for (int i = 0; i < FP_MULT_CYCLES; i++) {
				Instruction fpmult = fpmults[i];
				if (fpmult == null) 
					continue;
				stall = fpmult.outputsTo(inputs);
				if (stall) 
					break;
			}
		}
	}
	
	
	void runPipeline() {
		if (if_ != null && !stall) {
			if_.ife();
		}
	
		if (id != null && !stall) {
			id.id();
		}
		
		
		// check which Exec will execute based on Instruction id
		Exec toExecute = Exec.EX;
		int minId = Integer.MAX_VALUE;
		if (ex != null) {
			minId = ex.getId();
		}
		Instruction fpaddLast = fpadds[FP_ADD_CYCLES - 1];
		Instruction fpmultLast = fpmults[FP_MULT_CYCLES - 1];
		
		if (fpaddLast != null && fpaddLast.getId() < minId) {
			minId = fpaddLast.getId();
			toExecute = Exec.FP_ADD;
		}
		if (fpmultLast != null && fpmultLast.getId() < minId) {
			minId = fpmultLast.getId();
			toExecute = Exec.FP_MULT;
		}
				
		if (toExecute.equals(Exec.EX) && ex != null) {
			ex.ex();	
		} else if (toExecute.equals(Exec.FP_ADD)) {
			fpaddLast.ex();
		} else if (toExecute.equals(Exec.FP_MULT)){
			fpmultLast.ex();
		}
		
		
		if (mem != null) {
			mem.mem();	
		}
		
		
		if (wb != null) {
			wb.wb();
		}
		
		
		lastExec = toExecute;
	}
	
	public long getCycle() {
		return cycle;
	}
	
	
	public Map<Instruction, String> getInstructionPipelines() {
		Map<Instruction, String> result = new HashMap<>();
		
		if (if_ != null) {
			result.put(if_, "IF");
		}
		if (id != null) {
			result.put(id, "ID");
		}
		if (ex != null) {
			result.put(ex, "EX");
		}
		if (mem != null) {
			result.put(mem, "MEM");
		}
		if (wb != null) {
			result.put(wb, "WB");
		}
		for (int i = 0; i < FP_ADD_CYCLES; i++) {
			if (fpadds[i] != null) {
				result.put(fpadds[i], "A" + i);
			}
		}
		for (int i = 0; i < FP_MULT_CYCLES; i++) {
			if (fpmults[i] != null) {
				result.put(fpmults[i], "M" + i);
			}
		}
		
		return result;
	}
	
	
	protected Instruction getNextInstruction() {
		RegisterMgr regs = RegisterMgr.getInstance();
		long pc = regs.getPc();
		InstructionMgr instructions = InstructionMgr.getInstance();
		return instructions.getInstruction((int) pc / 4);
	}
}
