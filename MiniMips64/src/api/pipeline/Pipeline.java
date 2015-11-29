package api.pipeline;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import api.instruction.Instruction;
import api.instruction.InstructionMgr;
import api.register.RegisterMgr;

public class Pipeline {
	private final int FP_ADD_CYCLES = 4;
	private final int FP_MULT_CYCLES = 8;
	
	private Instruction if_; // ife(tch) because if is a reserved keyword and I don't want to use capital letters for variables.
	private Instruction id;
	private Instruction ex;
	private Instruction mem;
	private Instruction wb;
	private List<Instruction> fpadd;
	private List<Instruction> fpmult;
	private long cycle = 0;
	private boolean stall;
	
	
	public void printContents() {
		System.out.print("IF: ");
		if (if_ != null) {
			System.out.println(if_.getStringCode());
		} else {
			System.out.println();
		}
		
		System.out.print("ID: ");
		if (id != null) {
			System.out.println(id.getStringCode());
		} else {
			System.out.println();
		}
		
		System.out.print("EX: ");
		if (ex != null) {
			System.out.println(ex.getStringCode());
		} else {
			System.out.println();
		}
		
		
		System.out.print("MEM: ");
		if (mem != null) {
			System.out.println(mem.getStringCode());
		} else {
			System.out.println();
		}
		
		
		System.out.print("WB: ");
		if (wb != null) {
			System.out.println(wb.getStringCode());
		} else {
			System.out.println();
		}
	}
	
	public void performCycle() {
		/* Move registers forward */
		wb = mem;
		mem = ex;
		if (!stall) {
			ex = id;
			id = if_;
			if_ = this.getNextInstruction();	
		} else {
			ex = null;
		}
		
		/* Check for stall */
		List<String> inputs = null;
		if (id != null) {
			inputs = id.getInputs();
		}
		stall = false; 
		if (ex != null) {
			stall = ex.outputsTo(inputs);
		}
		if (!stall && mem != null) {
			stall = mem.outputsTo(inputs);
		}
		if (!stall && wb != null) {
			stall = wb.outputsTo(inputs);
		}
		
		/* Run pipeline */ 
		if (wb != null) {
			wb.wb();
		}
		if (mem != null) {
			mem.mem();	
		}
		if (ex != null) {
			ex.ex();	
		}
		if (id != null && !stall) {
			id.id();
		}
		if (if_ != null && !stall) {
			if_.ife();
		}
		this.printContents();
		cycle++;
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
		return result;
	}
	
	
	private Instruction getNextInstruction() {
		RegisterMgr regs = RegisterMgr.getInstance();
		long pc = regs.getPc();
		InstructionMgr instructions = InstructionMgr.getInstance();
		return instructions.getInstruction((int) pc / 4);
	}
}
