package util;

public class MipsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1605676999692622356L;
	private String instruction;
	
	public MipsException(String message) {
		super(message);
	}

	public String getInstructionString() {
		return instruction;
	}

	public void setInstructionString(String instruction) {
		this.instruction = instruction;
	}
}
