package api.instruction.opcode;

import util.Helper;

public class Rtype extends Opcode {
	private int rs;
	private int rt;
	private int rd;
	private int func;
	
	
	public Rtype(String stringOpcode, int func) {
		super(stringOpcode, 0);
		this.func = func;
	}
	
	
	public String getBinaryCode() {
		return Helper.IntToBinary6(this.getNumCode()) + Helper.IntToBinary5(rs) + Helper.IntToBinary5(rt)
				+ Helper.IntToBinary5(rd) + Helper.IntToBinary5(0) + Helper.IntToBinary6(func);
	}

	public String getStringCode2regs() {
		StringBuilder code = new StringBuilder(this.getStringInstruction());
		code.append(" r");
		code.append(this.getRs());
		code.append(", r");
		code.append(this.getRt());
		return code.toString();
	}
	
	
	public String getStringCode3regs() {
		StringBuilder code = new StringBuilder(this.getStringInstruction());
		code.append(" r");
		code.append(this.getRd());
		code.append(", r");
		code.append(this.getRs());
		code.append(", r");
		code.append(this.getRt());
		return code.toString();
	}
	
	
	public int getRd() { 
		return rd; 
	}

	
	public void setRd(String rd) { 
		this.rd = Integer.parseInt(rd);
	}

	
	public int getFunc() { 
		return func; 
	}
}
