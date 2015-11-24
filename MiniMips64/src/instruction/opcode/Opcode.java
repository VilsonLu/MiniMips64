package instruction.opcode;

public abstract class Opcode {
	private final String stringInstruction;
	private final int numCode;
	
	public abstract String getBinaryCode();
	
	protected Opcode(String stringCode, int numCode) {
		this.stringInstruction = stringCode;
		this.numCode = numCode;
	}
	
	protected String getStringInstruction() {
		return stringInstruction;
	}
	

	protected int getNumCode() {
		return numCode;
	}
	
}
