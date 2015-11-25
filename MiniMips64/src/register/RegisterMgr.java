package register;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class RegisterMgr {
<<<<<<< HEAD
=======
	private static RegisterMgr instance;
	
	static {
		instance = new RegisterMgr();
	}
	
	public static RegisterMgr getInstance() {
		return instance;
	}
	
>>>>>>> pipeline
	private final int TOTAL_REGS = 32;
	private List<RegisterCell> dataRegs;
	private RegisterCell hi;
	private RegisterCell lo;
	private Map<String, RegisterCell> internalRegs;
<<<<<<< HEAD
	
	
	public RegisterMgr() {
=======
	private boolean exMemCodeIsBranch = false;
	
	public static final String IF_ID_IR = "IF//ID.IR";
	public static final String IF_ID_NPC = "IF//ID.NPC";
	public static final String PC = "PC";
	
	public static final String ID_EX_IR = "ID//EX.IR";
	public static final String ID_EX_A = "ID//EX.A";
	public static final String ID_EX_B = "ID//EX.B";
	public static final String ID_EX_IMM = "ID//EX.IMM";
	public static final String ID_EX_NPC = "ID//EX.NPC";
	
	public static final String EX_MEM_IR = "EX//MEM.IR";
	public static final String EX_MEM_ALUOUTPUT = "EX//MEM.ALUOUTPUT";
	public static final String EX_MEM_B = "EX//MEM.B";
	public static final String EX_MEM_COND = "EX//MEM.COND";
	
	
	public static final String MEM_WB_ALUOUTPUT = "MEM//WB.ALUOUTPUT";
	public static final String MEM_WB_LMD = "MEM//WB.LMD";	
	public static final String MEM_WB_IR = "MEM//WB.IR";
	
	
	
	private RegisterMgr() {
>>>>>>> pipeline
		dataRegs = new ArrayList<>();
		for (int i = 0; i < TOTAL_REGS; i++) {
			dataRegs.add(new RegisterCell());
		}
		lo = new RegisterCell();
		hi = new RegisterCell();
		
		internalRegs = new LinkedHashMap<>();
		
<<<<<<< HEAD
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
=======
		internalRegs.put(IF_ID_IR, new RegisterCell());
		internalRegs.put(IF_ID_NPC, new RegisterCell());
		internalRegs.put(PC, new RegisterCell());
		
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
>>>>>>> pipeline
		if (key.equals("hi")) {
			value = hi.getValue();
		} else if (key.equals("lo")){
			value = lo.getValue();
		} else {
			 value = internalRegs.get(key).getValue();
		}
		return value;
	}
	
	
<<<<<<< HEAD
	public byte[] getValue(int index) {
=======
	public long getValue(int index) {
>>>>>>> pipeline
		return dataRegs.get(index).getValue();
	}
	
	
<<<<<<< HEAD
	public void setValue(int index, byte[] value) {
=======
	public void setValue(int index, long value) {
>>>>>>> pipeline
		if (index == 0) { 
			return; 
		}
		dataRegs.get(index).setValue(value);
	}
	
	
<<<<<<< HEAD
	public void setValue(String key, byte[] value) {
=======
	public void setValue(String key, long value) {
>>>>>>> pipeline
		if (key.equals("hi")) {
			hi.setValue(value);
		} else if (key.equals("lo")){
			lo.setValue(value);
		} else {
			internalRegs.get(key).setValue(value);
		}
	}
<<<<<<< HEAD
=======
	
	
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
>>>>>>> pipeline
}
