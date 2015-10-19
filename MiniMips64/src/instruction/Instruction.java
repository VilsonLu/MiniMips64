package instruction;

import util.Helper;

public abstract class Instruction {
	private final String stringOpcode;
	private final int opcode;
	public abstract String getStringCode();
	public abstract String getBinaryCode();
	public abstract void execute();
	
	
	Instruction(String stringOpcode, int opcode) {
		this.stringOpcode = stringOpcode;
		this.opcode = opcode;
	}
	
		
	protected String getStringOpcode() {
		return stringOpcode;
	}
	

	protected String getOpcode() {
		return Helper.IntToBinary6(opcode);
	}
	
}
