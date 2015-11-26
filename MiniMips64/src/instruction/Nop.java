package instruction;

import instruction.opcode.Rtype;

public class Nop extends Instruction {
	
	public Nop() {
		super();
		Rtype opcode = new Rtype("NOP", 0);
		this.setOpcode(opcode);
	}
	
	
	@Override
	public String getStringCode() {
		return "NOP";
	}

	
	
	@Override
	public void id() {}
	
	
	@Override
	public void ex() {}

	
	@Override
	public void mem() {}

	
	@Override
	public void wb() {}

}
