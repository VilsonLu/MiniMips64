package api.instruction.alu;

import api.instruction.opcode.RtypeShift;
import api.register.RegisterMgr;

public class Dsll extends AluInstruction {
	private RtypeShift opcode;
	
	public Dsll(String registerString) {
		super();
		opcode = new RtypeShift("DSLL", 56);
		String registers[] = registerString.split(","); // rd, rs, shf
		opcode.setRd(registers[0]);
		opcode.setRs(registers[1]);
		opcode.setShf(registers[2]);
		opcode.setDestination("r"+ registers[0]);
	}
	
	@Override
	long getExOperation() {
		RegisterMgr regs = RegisterMgr.getInstance();
		long a = regs.getValue(RegisterMgr.ID_EX_A);
		long b = regs.getValue(RegisterMgr.ID_EX_B);
		return a << b;
		
	}

	@Override
	public String getStringCode() {
		return opcode.getStringCode3regs();
	}

}
