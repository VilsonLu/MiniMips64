package instruction;

import instruction.opcode.Opcode;
import register.RegisterMgr;

public abstract class Instruction {
	private String comment;
	private Opcode opcode;
	private int order;
	
	public abstract String getStringCode();

	
	public String getBinaryCode() {
		return opcode.getBinaryCode();
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
		long a = regs.getValue(opcode.getRs());
		long b = regs.getValue(opcode.getRt());
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
	
	
	public int getOrder() {
		return order;
	}
	
	
	public void setOrder(int order) {
		this.order = order;
	}
	
	@Override
	public String toString() {
		return this.getStringCode();
	}
}
