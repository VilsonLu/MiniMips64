package api.instruction;

import java.util.List;

import api.instruction.opcode.Opcode;
import api.register.RegisterMgr;

public abstract class Instruction {
	private String comment;
	private Opcode opcode;
	private int id;
	private String label = "";
	private boolean stall = false;
	
	public abstract String getStringCode();

	public String getBinaryCode() {
		return opcode.getBinaryCode();
	}
	
	
	public long getLongCode() {
		return Long.parseLong(this.getBinaryCode(), 2);
	}
	
	
	protected final void setOpcode(Opcode opcode) {
		this.opcode = opcode;
	}
	
	
	protected Opcode getOpcode() {
		return opcode;
	}
	
	
	public String getComment() {
		return comment;
	}
	
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	public List<String> getInputs() {
		return opcode.getInputs();
	}

	
	public boolean outputsTo(List<String> regs) {
		if (regs == null) {
			return false;
		}
		return opcode.outputsTo(regs);
	}
	
	public String getLabel() {
		return label;
	}
	
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public void ife() {
		RegisterMgr regs = RegisterMgr.getInstance();
		long ir = Long.parseLong(opcode.getBinaryCode(), 2);
		regs.setValue(RegisterMgr.IF_ID_IR, ir);
		
		long npcValue;
		if (regs.exMemCodeIsBranch() && regs.getValue(RegisterMgr.EX_MEM_COND) == 0) {
			npcValue = regs.getValue(RegisterMgr.EX_MEM_ALUOUTPUT);
		} else {
			npcValue = regs.getPc() + 4;
		}
		regs.setValue(RegisterMgr.IF_ID_NPC, npcValue);
		regs.setValue(RegisterMgr.PC, npcValue);
	}
	
	
	public void id() {
		RegisterMgr regs = RegisterMgr.getInstance();
		long a = regs.getRValue(opcode.getRs());
		long b = regs.getRValue(opcode.getRt());
		long imm = opcode.getImm();
		long ir = regs.getValue(RegisterMgr.IF_ID_IR);
		long npc = regs.getValue(RegisterMgr.IF_ID_NPC);

		regs.setValue(RegisterMgr.ID_EX_A, a);
		regs.setValue(RegisterMgr.ID_EX_B, b);
		regs.setValue(RegisterMgr.ID_EX_IMM, imm);
		regs.setValue(RegisterMgr.ID_EX_IR, ir);
		regs.setValue(RegisterMgr.ID_EX_NPC, npc);
	}
	
	public abstract void ex();
	public abstract void mem();
	public abstract void wb();
	
	
	public int getId() {
		return id;
	}
	
	
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return this.getStringCode();
	}
	
	
	@Override
	public boolean equals(Object other){
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof Instruction)) return false;
	    
	    Instruction otherInstruction = (Instruction) other;
	    
	    return this.id == otherInstruction.id;
	}
}
