package register;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class RegisterMgr {
	private final int TOTAL_REGS = 32;
	private List<Register> dataRegs;
	private Register hi;
	private Register lo;
	private Map<String, Register> internalRegs;
	
	
	public RegisterMgr() {
		dataRegs = new ArrayList<>();
		for (int i = 0; i < TOTAL_REGS; i++) {
			dataRegs.add(new Register());
		}
		lo = new Register();
		hi = new Register();
		
		internalRegs = new LinkedHashMap<>();
		
		internalRegs.put("IF//ID.IR", new Register());
		internalRegs.put("IF//ID.NPC", new Register());
		internalRegs.put("PC", new Register());
		
		internalRegs.put("ID//EX.IR", new Register());
		internalRegs.put("ID//EX.A", new Register());
		internalRegs.put("ID//EX.B", new Register());
		internalRegs.put("ID//EX.IMM", new Register());
		
		internalRegs.put("EX//MEM.IR", new Register());
		internalRegs.put("EX//MEM.ALUOUTPUT", new Register());
		internalRegs.put("EX//MEM.B", new Register());
		internalRegs.put("EX//MEM.COND", new Register());
		
		internalRegs.put("MEM//WB.IR", new Register());
		internalRegs.put("MEM//WB.ALUOUTPUT", new Register());
		internalRegs.put("MEM//WB.LMD", new Register());
	}
	
	
	public byte[] getValue(String key) {
		byte[] value; 
		if (key.equals("hi")) {
			value = hi.getValue();
		} else if (key.equals("lo")){
			value = lo.getValue();
		} else {
			 value = internalRegs.get(key).getValue();
		}
		return value;
	}
	
	
	public byte[] getValue(int index) {
		return dataRegs.get(index).getValue();
	}
	
	
	public void setValue(int index, byte[] value) {
		if (index == 0) { 
			return; 
		}
		dataRegs.get(index).setValue(value);
	}
	
	
	public void setValue(String key, byte[] value) {
		if (key.equals("hi")) {
			hi.setValue(value);
		} else if (key.equals("lo")){
			lo.setValue(value);
		} else {
			internalRegs.get(key).setValue(value);
		}
	}
}
