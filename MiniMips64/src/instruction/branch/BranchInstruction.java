package instruction.branch;

import instruction.Instruction;
import register.RegisterMgr;

public abstract class BranchInstruction extends Instruction {
	public BranchInstruction() {
		super();
	}
	
	public void ex() {
		RegisterMgr regs = RegisterMgr.getInstance();
		regs.setExMemCodeIsBranch(true);
		
		long aluoutput = regs.getValue(RegisterMgr.ID_EX_NPC) + regs.getValue(RegisterMgr.ID_EX_IMM) * 4;
		
		regs.setValue(RegisterMgr.EX_MEM_COND, 0);
		regs.setValue(RegisterMgr.EX_MEM_ALUOUTPUT, aluoutput);
	}
	
	public void mem() {}
	
	public void wb() {}
	
	abstract long getExOperation();
}
