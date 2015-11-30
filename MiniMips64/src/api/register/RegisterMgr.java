package api.register;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class RegisterMgr {
	private static RegisterMgr instance = new RegisterMgr();
	
	
	public static RegisterMgr getInstance() {
		return instance;
	}
	
	
	private final int TOTAL_REGS = 32;
	private List<RegisterCell> rRegs;
	private List<RegisterCell> fRegs;
	private RegisterCell hi;
	private RegisterCell lo;
	private Map<String, RegisterCell> internalRegs;
	private boolean exMemCodeIsBranch = false;
	
	public static final String PC = "PC";
	public static final String IF_ID_IR = "IF/ID.IR";
	public static final String IF_ID_NPC = "IF/ID.NPC";
	
	public static final String ID_EX_IR = "ID/EX.IR";
	public static final String ID_EX_A = "ID/EX.A";
	public static final String ID_EX_B = "ID/EX.B";
	public static final String ID_EX_IMM = "ID/EX.IMM";
	public static final String ID_EX_NPC = "ID/EX.NPC";
	
	public static final String EX_MEM_IR = "EX/MEM.IR";
	public static final String EX_MEM_ALUOUTPUT = "EX/MEM.ALUOUTPUT";
	public static final String EX_MEM_B = "EX/MEM.B";
	public static final String EX_MEM_COND = "EX/MEM.COND";
	
	public static final String MEM_WB_ALUOUTPUT = "MEM/WB.ALUOUTPUT";
	public static final String MEM_WB_LMD = "MEM/WB.LMD";	
	public static final String MEM_WB_IR = "MEM/WB.IR";
	
	
	private RegisterMgr() {
		rRegs = new ArrayList<>();
		for (int i = 0; i < TOTAL_REGS; i++) {
			rRegs.add(new RegisterCell());
		}
		
		fRegs = new ArrayList<>();
		for (int i = 0; i < TOTAL_REGS; i++) {
			fRegs.add(new RegisterCell());
		}
		
		lo = new RegisterCell();
		hi = new RegisterCell();
		
		internalRegs = new LinkedHashMap<>();
		
		internalRegs.put(PC, new RegisterCell());
		internalRegs.put(IF_ID_IR, new RegisterCell());
		internalRegs.put(IF_ID_NPC, new RegisterCell());
		
		internalRegs.put(ID_EX_IR, new RegisterCell());
		internalRegs.put(ID_EX_A, new RegisterCell());
		internalRegs.put(ID_EX_B, new RegisterCell());
		internalRegs.put(ID_EX_IMM, new RegisterCell());
		internalRegs.put(ID_EX_NPC, new RegisterCell());
		
		internalRegs.put(EX_MEM_IR, new RegisterCell());
		internalRegs.put(EX_MEM_ALUOUTPUT, new RegisterCell());
		internalRegs.put(EX_MEM_B, new RegisterCell());
		internalRegs.put(EX_MEM_COND, new RegisterCell());
		
		internalRegs.put(MEM_WB_IR, new RegisterCell());
		internalRegs.put(MEM_WB_ALUOUTPUT, new RegisterCell());
		internalRegs.put(MEM_WB_LMD, new RegisterCell());
	}
	
	
	public long getValue(String key) {
		long value; 
		if (key.equals("hi")) {
			value = hi.getValue();
		} else if (key.equals("lo")){
			value = lo.getValue();
		} else {
			 value = internalRegs.get(key).getValue();
		}
		return value;
	}
	
	
	public long getRValue(int index) {
		return rRegs.get(index).getValue();
	}
	
	
	public void setRValue(int index, long value) {
		if (index == 0) { 
			return; 
		}
		rRegs.get(index).setValue(value);
	}
	
	
	public long getFValue(int index) {
		return fRegs.get(index).getValue();
	}
	
	
	public void setFValue(int index, long value) {
		fRegs.get(index).setValue(value);
	}
	
	
	public void setValue(String key, long value) {
		if (key.startsWith("r")) {
			int index = Integer.valueOf(key.substring(0, 1));
			this.setRValue(index, value);
		
		} else if (key.startsWith("f")) {
			int index = Integer.valueOf(key.substring(0, 1));
			this.setFValue(index, value);
		
		} else if (key.equals("hi")) {
			hi.setValue(value);
		
		} else if (key.equals("lo")){
			lo.setValue(value);
		
		} else {
			internalRegs.get(key).setValue(value);
		}
	}
	
	
	public Map<String, Long> getInternalRegs() {
		Map<String, Long> result = new HashMap<>();
		for (String key : internalRegs.keySet()) {
			result.put(key, internalRegs.get(key).getValue());
		}
		
		return result;
	}
	
	
	public void incrementPc() {
		RegisterCell pc = internalRegs.get(PC);
		pc.setValue(pc.getValue() + 4);
	}
	
	
	public long getPc() {
		return internalRegs.get(PC).getValue();
	}
	
	
	public long getPCindex() {
		return this.getPc() / 4;
	}
	
	
	public boolean exMemCodeIsBranch() {
		return exMemCodeIsBranch;
	}
	
	
	public void setExMemCodeIsBranch(boolean value) {
		exMemCodeIsBranch = value;
	}
}
