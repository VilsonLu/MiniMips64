package api.instruction.opcode;

import util.Helper;

public class Itype extends Opcode {
	private int rs;
	private int rt;
	private long imm;
	
	
	public Itype(String stringOpcode, int opcode) {
		super(stringOpcode, opcode);
	}
	
	
	public String getStringCode2regOffset() {
		StringBuilder code = new StringBuilder(this.getStringInstruction());
		code.append(" r");
		code.append(this.getRs());
		code.append(", r");
		code.append(this.getRt());
		code.append(", ");
		code.append(this.getImm());
		return code.toString();
	}
	
	
	public String getStringCodeRegOffsetReg() {
		StringBuilder code = new StringBuilder(this.getStringInstruction());
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
		return Helper.IntToBinary6(this.getNumCode()) + Helper.IntToBinary5(rs) 
			+ Helper.IntToBinary5(rt) + Helper.IntToBinary16(imm);
	}

	
	@Override
	public long getImm() { 
		return imm; 
	}
	
	
	public void setImm(String imm) {
		this.imm = Long.parseLong(imm);
	}
}
