package api.instruction.opcode;

import java.util.ArrayList;
import java.util.List;

import util.Helper;

public class Itype extends Opcode {
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
		return Helper.IntToBinary6(this.getNumCode()) + Helper.IntToBinary5(this.getRs()) 
			+ Helper.IntToBinary5(this.getRt()) + Helper.IntToBinary16(imm);
	}

	
	@Override
	public long getImm() { 
		return imm; 
	}
	
	
	public void setImm(String imm) {
		this.imm = Long.parseLong(imm);
	}
	
	
	@Override
	public boolean outputsTo(List<String> regs) {
		String destination = "r" + this.getRt();
		return regs.contains(destination);
	}


	@Override
	public List<String> getInputs() {
		ArrayList<String> inputs = new ArrayList<>();
		if (this.getRs() != 0) {
			inputs.add("r"+ this.getRs());	
		}
		return null;
	}
}
