package instruction.alu;

import instruction.opcode.Rtype;

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
	}

	@Override
	public String getStringCode() {
		return opcode.getStringCode3regs();
	}
}
