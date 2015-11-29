package api.instruction;

import java.util.ArrayList;
import java.util.List;

import api.instruction.opcode.Rtype;

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
	public List<String> getInputs() {
		ArrayList<String> inputs = new ArrayList<>();
		return inputs;
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
