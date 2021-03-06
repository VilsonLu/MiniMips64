package api.instruction.loadstore;

import api.instruction.Instruction;
import api.register.RegisterMgr;

public abstract class LoadStoreInstruction extends Instruction {
	public LoadStoreInstruction() {
		super();
	}
	
	public void ex() {
		RegisterMgr regs = RegisterMgr.getInstance();
		regs.setExMemCodeWasBranch(false);
		regs.setValue(RegisterMgr.EX_MEM_COND, 0);
		
		long aluoutput = regs.getOldValue(RegisterMgr.ID_EX_A) + regs.getOldValue(RegisterMgr.ID_EX_IMM);
		long b = regs.getOldValue(RegisterMgr.ID_EX_B);
		long ir = regs.getOldValue(RegisterMgr.ID_EX_IR);
		
		regs.setValue(RegisterMgr.EX_MEM_ALUOUTPUT, aluoutput);
		regs.setValue(RegisterMgr.EX_MEM_B, b);
		regs.setValue(RegisterMgr.EX_MEM_IR, ir);
	}
}
