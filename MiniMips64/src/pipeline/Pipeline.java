package pipeline;

import java.util.List;

import instruction.Instruction;
import instruction.InstructionMgr;
import register.RegisterMgr;

public class Pipeline {
	private final int FP_ADD_CYCLES = 4;
	private final int FP_MULT_CYCLES = 8;
	
	private Instruction ife; // ife(tch) because if is a reserved keyword and I don't want to use capital letters for variables.
	private Instruction id;
	private Instruction ex;
	private Instruction mem;
	private Instruction wb;
	private List<Instruction> fpadd;
	private List<Instruction> fpmult;
	private long cycle = 0;
	
	
	public void printContents() {
		System.out.print("IF: ");
		if (ife != null) {
			System.out.println(ife.getStringCode());
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
		wb = mem;
		mem = ex;
		ex = id;
		id = ife;
		ife = this.getNextInstruction();
		
		if (wb != null) {
			wb.wb();
		}
		if (mem != null) {
			mem.mem();	
		}
		if (ex != null) {
			ex.ex();	
		}
		if (id != null) {
			id.id();
		}
		if (ife != null) {
			ife.ife();
		}
		
		cycle++;
	}
	
	
	private Instruction getNextInstruction() {
		RegisterMgr regs = RegisterMgr.getInstance();
		long pc = regs.getPc();
		InstructionMgr instructions = InstructionMgr.getInstance();
		return instructions.getInstruction((int) pc / 4);
	}
}
