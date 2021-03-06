package api.instruction.alu;

import api.instruction.opcode.Rtype;
import api.register.RegisterMgr;

public class Or extends AluInstruction {
	private Rtype opcode;
	
	public Or(String registerString) {
		super();
		opcode = new Rtype("OR", 37);
		this.setOpcode(opcode);
		String registers[] = registerString.split(","); // rd, rs, rt
		opcode.setRd(registers[0]);
		opcode.setRs(registers[1]);
		opcode.setRt(registers[2]);
		opcode.setDestination("r" + registers[0]);
	}

	@Override
	public String getStringCode() {
		return opcode.getStringCode3regs();
	}

	@Override
	long getExOperation() {
		RegisterMgr regs = RegisterMgr.getInstance();
		long a = regs.getValue(RegisterMgr.ID_EX_A);
		long b = regs.getValue(RegisterMgr.ID_EX_B);
		return a | b;
	}
}
