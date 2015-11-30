package api.instruction.opcode;

import java.util.List;

public abstract class Opcode {
	private final String stringInstruction;
	private final int numCode;
	
	private int rs;
	private int rt;
	private String destination;
	
	public abstract String getBinaryCode();
	public abstract List<String> getInputs();
	public abstract boolean outputsTo(List<String> regs);
	
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
	
	
	public String getDestination() {
		return destination;
	}
	
	
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	
	public long getImm() {
		// TODO sign extend
		long binary = Long.parseLong(this.getBinaryCode(), 2);
		return binary % 0x10000;
	}
}
