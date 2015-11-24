package instruction.alu;

import instruction.opcode.Rtype;
import register.RegisterMgr;

public class Dmult extends AluInstruction {
	private Rtype opcode;
	private long hi;
	private long lo;
	
	public Dmult(String registerString) {
		super();
		opcode = new Rtype("DMULT", 28);
		this.setOpcode(opcode);
		
		String registers[] = registerString.split(","); // rs, rt
		opcode.setRs(registers[0]);
		opcode.setRt(registers[1]);
		opcode.setDestination("-1");
		
	}

	@Override
	public String getStringCode() {
		return opcode.getStringCode2regs();
	}

	@Override
	long getExOperation() {
		RegisterMgr regs = RegisterMgr.getInstance();
		long a = regs.getValue(RegisterMgr.ID_EX_A);
		long b = regs.getValue(RegisterMgr.ID_EX_B);
		lo = a * b;
		return lo;
	}
}
