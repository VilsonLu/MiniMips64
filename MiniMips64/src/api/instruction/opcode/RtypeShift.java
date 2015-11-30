package api.instruction.opcode;

import java.util.ArrayList;
import java.util.List;

import util.Helper;

public class RtypeShift extends Opcode {
	private int rd;
	private int func;
	private int shf;
	
	public RtypeShift(String stringOpcode, int func) {
		super(stringOpcode, 0);
		this.func = func;
	}
	
	public int getRd() { 
		return rd; 
	}

	
	public void setRd(String rd) { 
		this.rd = Integer.parseInt(rd);
	}

	
	public void setShf(String shf) {
		this.shf = Integer.parseInt(shf);
	}
	
	
	public int getFunc() { 
		return func; 
	}
	
	
	@Override
	public String getBinaryCode() {
		return Helper.IntToBinary6(this.getNumCode()) + Helper.IntToBinary5(0) 
		+ Helper.IntToBinary5(this.getRs()) + Helper.IntToBinary5(this.getRd()) 
		+ Helper.IntToBinary5(shf) + Helper.IntToBinary6(func);
	}

	
	public String getStringCode3regs() {
		StringBuilder code = new StringBuilder(this.getStringInstruction());
		code.append(" r");
		code.append(this.getRd());
		code.append(", r");
		code.append(this.getRs());
		code.append(", ");
		code.append(shf);
		return code.toString();
	}
	
	
	@Override
	public List<String> getInputs() {
		ArrayList<String> inputs = new ArrayList<>();
		if (this.getRs() != 0) {
			inputs.add("r"+ this.getRs());	
		}
		
		return inputs;
	}

	@Override
	public boolean outputsTo(List<String> regs) {
		String destination = "r" + this.getRd();
		return regs.contains(destination);
	}

}
