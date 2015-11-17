package register;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class RegisterMgr {
	private final int TOTAL_REGS = 32;
	private List<RegisterCell> dataRegs;
	private RegisterCell hi;
	private RegisterCell lo;
	private Map<String, RegisterCell> internalRegs;
	
	
	public RegisterMgr() {
		dataRegs = new ArrayList<>();
		for (int i = 0; i < TOTAL_REGS; i++) {
			dataRegs.add(new RegisterCell());
		}
		lo = new RegisterCell();
		hi = new RegisterCell();
		
		internalRegs = new LinkedHashMap<>();
		
		internalRegs.put("IF//ID.IR", new RegisterCell());
		internalRegs.put("IF//ID.NPC", new RegisterCell());
		internalRegs.put("PC", new RegisterCell());
		
		internalRegs.put("ID//EX.IR", new RegisterCell());
		internalRegs.put("ID//EX.A", new RegisterCell());
		internalRegs.put("ID//EX.B", new RegisterCell());
		internalRegs.put("ID//EX.IMM", new RegisterCell());
		
		internalRegs.put("EX//MEM.IR", new RegisterCell());
		internalRegs.put("EX//MEM.ALUOUTPUT", new RegisterCell());
		internalRegs.put("EX//MEM.B", new RegisterCell());
		internalRegs.put("EX//MEM.COND", new RegisterCell());
		
		internalRegs.put("MEM//WB.IR", new RegisterCell());
		internalRegs.put("MEM//WB.ALUOUTPUT", new RegisterCell());
		internalRegs.put("MEM//WB.LMD", new RegisterCell());
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
