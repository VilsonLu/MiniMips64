package instruction;

import util.Helper;

public abstract class Rtype extends Instruction {
	private int rs;
	private int rt;
	private int rd;
	private int func;
	
	
	Rtype(String stringOpcode, int func) {
		super(stringOpcode, 0);
		this.func = func;
	}
	
	
	public String getBinaryCode() {
		return this.getOpcode() + Helper.IntToBinary5(rs) + Helper.IntToBinary5(rt)
				+ Helper.IntToBinary5(rd) + Helper.IntToBinary5(0) + Helper.IntToBinary5(func);
	}

	
	protected String getStringCode3regs() {
		StringBuilder code = new StringBuilder(this.getStringOpcode());
		code.append(" r");
		code.append(this.getRd());
		code.append(", r");
		code.append(this.getRs());
		code.append(", r");
		code.append(this.getRt());
		return code.toString();
	}
	
	
	public int getRs() { 
		return rs; 
	}

	
	protected void setRs(String rs) { 
		this.rs = Integer.parseInt(rs); 
	}

	
	public int getRt() { 
		return rt;	
	}

	
	protected void setRt(String rt) { 
		this.rt = Integer.parseInt(rt); 
	}

	
	public int getRd() { 
		return rd; 
	}

	
	protected void setRd(String rd) { 
		this.rd = Integer.parseInt(rd); 
	}

	
	public int getFunc() { 
		return func; 
	}
}
