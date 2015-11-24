package instruction.rtype;

import instruction.AluInstruction;

public class Daddu extends AluInstruction {
	private Rtype opcode;
	
	public Daddu(String registerString) {
		super();
		opcode = new Rtype("DADDU", 45);
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
