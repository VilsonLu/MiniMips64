package pipeline;

import java.util.List;

import instruction.Instruction;
import instruction.InstructionMgr;
import register.RegisterMgr;

public class Pipeline {
	private final int FP_ADD_CYCLES = 4;
	private final int FP_MULT_CYCLES = 8;
	
	private Instruction ife; // ife(tch) because if is a reserved keyword and I don't want to use capital letters for variables
	private Instruction id;
	private Instruction ex;
	private Instruction mem;
	private Instruction wb;
	private List<Instruction> fpadd;
	private List<Instruction> fpmult;
	
	
	void performCycle() {
		wb = mem;
		mem = ex;
		ex = id;
		id = ife;
		ife = this.getNextInstruction(); 
	}
	
	
	private Instruction getNextInstruction() {
		RegisterMgr regs = RegisterMgr.getInstance();
		long pc = regs.getPc();
		InstructionMgr instructions = InstructionMgr.getInstance();
		instructions.getInstruction((int) pc / 4);
		
		return null;
	}
}
