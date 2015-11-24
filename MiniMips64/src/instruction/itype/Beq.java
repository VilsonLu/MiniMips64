package instruction.itype;

import instruction.BranchInstruction;

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

	
}
