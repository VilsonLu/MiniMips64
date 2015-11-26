package api.instruction.branch;

import api.instruction.opcode.Itype;
import api.register.RegisterMgr;

public class Beq extends BranchInstruction {
	private Itype opcode;
	
	public Beq(String registerString) {
		super();
		opcode = new Itype("BEQ", 4);
		this.setOpcode(opcode);
		String registers[] = registerString.split(","); // rs, rt, offset
		opcode.setRs(registers[0]);
		opcode.setRt(registers[1]);
		opcode.setImm(registers[2]);
	}
	
	
	@Override
	public String getStringCode() {
		return opcode.getStringCode2regOffset();
	}


	@Override
	long getExOperation() {
		RegisterMgr regs = RegisterMgr.getInstance();
		long a = regs.getValue(RegisterMgr.ID_EX_A);
		long b = regs.getValue(RegisterMgr.ID_EX_B);
		return a == b ? 1 : 0;
	}

	
}
