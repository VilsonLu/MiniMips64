package api.instruction.branch;

import api.instruction.Instruction;
import api.register.RegisterMgr;
import util.Helper;

/**
 * Special Instruction which pretty much overrides everything.
 * @author Ivan
 *
 */
public class J extends Instruction {
	private long imm;
	private final int numOpcode = 2;
	
	public J(String registerString) {
		this.imm = Long.parseLong(registerString);
	}
	
	@Override
	public String getBinaryCode() {
		String code = Helper.IntToBinary6(numOpcode) + Helper.IntToBinary26(imm);
		return code;
	}
	
	
	
	@Override
	public String getStringCode() {
		return "J " + imm;
	}

	
	@Override
	public void ex() {
		RegisterMgr regs = RegisterMgr.getInstance();
		regs.setExMemCodeWasBranch(true);
		long aluoutput = regs.getOldValue(RegisterMgr.ID_EX_IMM) * 4;
		long ir = regs.getOldValue(RegisterMgr.ID_EX_IR);
		regs.setValue(RegisterMgr.EX_MEM_IR, ir);
		regs.setValue(RegisterMgr.EX_MEM_COND, 1);
		regs.setValue(RegisterMgr.EX_MEM_ALUOUTPUT, aluoutput);
	}

	
	@Override
	public void mem() {
		RegisterMgr regs = RegisterMgr.getInstance();
		regs.setValue(RegisterMgr.EX_MEM_COND, 0);
	}

	
	@Override
	public void wb() {}

}
