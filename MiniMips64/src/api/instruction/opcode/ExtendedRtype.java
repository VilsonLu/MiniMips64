package api.instruction.opcode;

import java.util.ArrayList;
import java.util.List;

import util.Helper;

public class ExtendedRtype extends Opcode {
	private int t;
	private int s;
	private int d;
	private int func;
	private int extOpcode;
	
	public ExtendedRtype(String stringOpcode, int numOpcode, int extOpcode, int func) {
		super(stringOpcode, numOpcode);
		this.func = func;
		this.extOpcode = extOpcode;
	}
	
	
	public int getT() {
		return t;
	}


	public void setT(String t) {
		this.t = Integer.parseInt(t);
	}


	public int getS() {
		return s;
	}


	public void setS(String s) {
		this.s = Integer.parseInt(s);
	}


	public int getD() {
		return d;
	}


	public void setD(String d) {
		this.d = Integer.parseInt(d);
	}


	public int getFunc() { 
		return func; 
	}
	
	
	public String getStringCode3regs() {
		StringBuilder code = new StringBuilder(this.getStringInstruction());
		code.append(" f");
		code.append(this.getD());
		code.append(", f");
		code.append(this.getS());
		code.append(", f");
		code.append(this.getT());
		return code.toString();
	}
	
	@Override
	public String getBinaryCode() {
		return Helper.IntToBinary6(this.getNumCode()) + Helper.IntToBinary5(extOpcode) 
			+ Helper.IntToBinary5(t) + Helper.IntToBinary5(s)
			+ Helper.IntToBinary5(d) + Helper.IntToBinary6(func);
	}

	@Override
	public List<String> getInputs() {
		ArrayList<String> inputs = new ArrayList<>();
		inputs.add("f"+ t);
		inputs.add("f"+ d);
		return inputs;
	}

	@Override
	public boolean outputsTo(List<String> regs) {
		String destination = "f" + d;
		return regs.contains(destination);
	}

}
