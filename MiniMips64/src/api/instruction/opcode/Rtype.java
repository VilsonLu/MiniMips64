package api.instruction.opcode;

import java.util.ArrayList;
import java.util.List;

import util.Helper;

public class Rtype extends Opcode {
	private int rd;
	private int func;
	
	
	public Rtype(String stringOpcode, int func) {
		super(stringOpcode, 0);
		this.func = func;
	}
	
	
	@Override
	public String getBinaryCode() {
		return Helper.IntToBinary6(this.getNumCode()) + Helper.IntToBinary5(this.getRs()) 
		+ Helper.IntToBinary5(this.getRt()) + Helper.IntToBinary5(rd) 
		+ Helper.IntToBinary5(0) + Helper.IntToBinary6(func);
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

	
	@Override
	public List<String> getInputs() {
		ArrayList<String> inputs = new ArrayList<>();
		if (this.getRs() != 0) {
			inputs.add("r"+ this.getRs());	
		}
		if (this.getRt() != 0) {
			inputs.add("r"+ this.getRt());	
		}
		
		return inputs;
	}


	@Override
	public boolean outputsTo(List<String> regs) {
		String destination = "r" + this.getRd();
		return regs.contains(destination);
	}
}
