package instruction.itype;

import instruction.Instruction;
import util.Helper;

public abstract class Itype extends Instruction {
	private int rs;
	private int rt;
	private long imm;
	
	
	Itype(String stringOpcode, int opcode) {
		super(stringOpcode, opcode);
		
	}
	
	
	protected String getStringCode2regOffset() {
		StringBuilder code = new StringBuilder(this.getStringOpcode());
		code.append(" r");
		code.append(this.getRs());
		code.append(", r");
		code.append(this.getRt());
		code.append(", ");
		code.append(this.getImm());
		return code.toString();
	}
	
	
	protected String getStringCodeRegOffsetReg() {
		StringBuilder code = new StringBuilder(this.getStringOpcode());
		code.append(" r");
		code.append(this.getRt());
		code.append(", ");
		code.append(this.getImm());
		code.append("(");
		code.append(this.getRs());
		code.append(")");
		return code.toString();
	}
	
	
	public String getBinaryCode() {
		return Helper.IntToBinary6(this.getOpcode()) + Helper.IntToBinary5(rs) 
			+ Helper.IntToBinary5(rt) + Helper.IntToBinary16(imm);
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

	
	public long getImm() { 
		return imm; 
	}
	
	
	public void setImm(String imm) {
		this.imm = Long.parseLong(imm);
	}
}
