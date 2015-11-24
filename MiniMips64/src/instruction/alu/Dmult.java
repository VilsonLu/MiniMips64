package instruction.alu;

import instruction.opcode.Rtype;

public class Dmult extends AluInstruction {
	private Rtype opcode;
	
	public Dmult(String registerString) {
		super();
		opcode = new Rtype("DMULT", 28);
		this.setOpcode(opcode);
		
		String registers[] = registerString.split(","); // rs, rt
		opcode.setRs(registers[0]);
		opcode.setRt(registers[1]);
		
	}

	@Override
	public String getStringCode() {
		return opcode.getStringCode2regs();
	}
}
