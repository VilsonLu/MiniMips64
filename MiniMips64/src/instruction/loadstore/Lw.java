package instruction.loadstore;

import instruction.opcode.Itype;
import register.RegisterMgr;

public class Lw extends LoadStoreInstruction {
	private Itype opcode; 
	public Lw(String registerString) {
		super();
		opcode = new Itype("LW", 35);
		this.setOpcode(opcode);
		String registers[] = registerString.split(","); // rd, offset, rs
		opcode.setRt(registers[0]);
		opcode.setImm(registers[1]);
		opcode.setRs(registers[2]);
		opcode.setDestination(registers[0]);
	}

	
	
	@Override
	public String getStringCode() {
		return opcode.getStringCodeRegOffsetReg();
	}



	@Override
	public void mem() {
		// TODO get value from Memory
	}
	
	@Override
	public void wb() {
		RegisterMgr regs = RegisterMgr.getInstance();
		long value = regs.getValue(RegisterMgr.MEM_WB_LMD);
		regs.setValue(opcode.getDestination(), value);
	}
}
