package instruction.loadstore;

import instruction.opcode.Itype;

public class Lw extends LoadStoreInstruction {
	private Itype opcode; 
	public Lw(String registerString) {
		super();
		opcode = new Itype("LW", 35);
		this.setOpcode(opcode);
		String registers[] = registerString.split(","); // rd, offset, rs
		opcode.setRt(registers[0]);
		opcode.setImm(registers[1]);
		opcode.setRs(registers[2]);
	}

	
	@Override
	public String getStringCode() {
		return opcode.getStringCodeRegOffsetReg();
	}
}
