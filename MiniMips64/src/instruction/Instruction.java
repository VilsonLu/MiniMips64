package instruction;


public abstract class Instruction {
	private final String stringOpcode;
	private final int opcode;
	public abstract String getStringCode();
	public abstract String getBinaryCode();
	public abstract void execute();
	
	
	protected Instruction(String stringOpcode, int opcode) {
		this.stringOpcode = stringOpcode;
		this.opcode = opcode;
	}
	
	
	protected String getStringOpcode() {
		return stringOpcode;
	}
	

	protected int getOpcode() {
		return opcode;
	}
	
	
	@Override
	public String toString() {
		return this.getStringCode();
	}
}
