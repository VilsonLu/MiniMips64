package api.instruction.alu;

import api.instruction.Instruction;
import api.register.RegisterMgr;

public abstract class AluInstruction extends Instruction {
	
	abstract long getExOperation();
	
	
	public AluInstruction() {
		super();
	}
	
	
	public void ex() {
		RegisterMgr regs = RegisterMgr.getInstance();
		regs.setExMemCodeIsBranch(false);
		long ir = regs.getValue(RegisterMgr.ID_EX_IR);
		
		regs.setValue(RegisterMgr.EX_MEM_IR, ir);
		regs.setValue(RegisterMgr.EX_MEM_COND, 0);
		regs.setValue(RegisterMgr.EX_MEM_ALUOUTPUT, this.getExOperation());
	}
	
	
	public void mem() {
		RegisterMgr regs = RegisterMgr.getInstance();
		long ir = regs.getValue(RegisterMgr.EX_MEM_IR);
		long aluoutput = regs.getValue(RegisterMgr.EX_MEM_ALUOUTPUT);
		regs.setValue(RegisterMgr.MEM_WB_IR, ir);
		regs.setValue(RegisterMgr.MEM_WB_ALUOUTPUT, aluoutput);
	}
	
	
	public void wb() {
		RegisterMgr regs = RegisterMgr.getInstance();
		long aluoutput = regs.getValue(RegisterMgr.MEM_WB_ALUOUTPUT);
		regs.setValue(this.getOpcode().getDestination(), aluoutput);	
	}
}
