package api.instruction.alu;

import java.util.List;

import api.instruction.opcode.Rtype;
import api.register.RegisterMgr;

public class Slt extends AluInstruction {
	private Rtype opcode;
	
	public Slt(String registerString) {
		super();
		opcode = new Rtype("SLT", 42);
		this.setOpcode(opcode);
		String registers[] = registerString.split(","); // rd, rs, rt	
		opcode.setRd(registers[0]);
		opcode.setRs(registers[1]);
		opcode.setRt(registers[2]);
		opcode.setDestination("r"+ registers[0]);
	}
	
	
	@Override
	long getExOperation() {
		RegisterMgr regs = RegisterMgr.getInstance();
		long a = regs.getValue(RegisterMgr.ID_EX_A);
		long b = regs.getValue(RegisterMgr.ID_EX_B);
		return a < b ? 1 : 0;
		
	}

	@Override
	public String getStringCode() {
		return opcode.getStringCode3regs();
	}

}
