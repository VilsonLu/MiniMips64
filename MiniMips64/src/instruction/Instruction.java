package instruction;

import instruction.opcode.Opcode;

public abstract class Instruction {
	private Opcode opcode;
	
	private int order;
	
	
	protected Instruction() {
	}
	
	
	public abstract String getStringCode();

	public String getBinaryCode() {
		return opcode.getBinaryCode();
	}
	
	protected final void setOpcode(Opcode opcode) {
		this.opcode = opcode;
	}
	
	public void ife() {
		
	}
	
	public void id() {
		
	}
	
	
	public void ex() {
		
	}
	
	
	public void mem() {
		
	}
	
	
	public void wb() {
		
	}
	

	
	public int getOrder() {
		return order;
	}
	
	public void setOrder(int order) {
		this.order = order;
	}
	
	@Override
	public String toString() {
		return this.getStringCode();
	}
}
