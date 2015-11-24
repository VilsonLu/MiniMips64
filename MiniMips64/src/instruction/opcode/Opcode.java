package instruction.opcode;

public abstract class Opcode {
	private final String stringInstruction;
	private final int numCode;
	
	private int rs;
	private int rt;
	
	
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
	
	public int getRs() { 
		return rs; 
	}

	
	public void setRs(String rs) { 
		this.rs = Integer.parseInt(rs); 
	}

	
	public int getRt() { 
		return rt;	
	}

	
	public void setRt(String rt) { 
		this.rt = Integer.parseInt(rt); 
	}
	
	
	public long getImm() {
		// TODO sign extend
		long binary = Long.parseLong(this.getBinaryCode(), 2);
		return binary % 0x10000;
	}
}
