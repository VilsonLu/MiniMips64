package api.instruction.loadstore;

import api.instruction.opcode.Itype;
import api.memory.MemoryMgr;
import api.register.RegisterMgr;

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
		opcode.setDestination("r" + registers[0]);
		System.out.println(opcode.getBinaryCode());
	}
	
	
	@Override
	public String getStringCode() {
		return opcode.getStringCodeRegOffsetReg();
	}

	
	@Override
	public void mem() {
		MemoryMgr mems = MemoryMgr.getInstance();
		RegisterMgr regs = RegisterMgr.getInstance();
		long location = regs.getOldValue(RegisterMgr.EX_MEM_ALUOUTPUT);
		long lmd = 0;
		for (int i = 0; i < 4; i++) {
			byte value = mems.get(location + i);
			long converted = value << (i * 8);
			lmd = lmd + converted;
		}
		regs.setValue(RegisterMgr.MEM_WB_LMD, lmd);

	}

	
	@Override
	public void wb() {
		RegisterMgr regs = RegisterMgr.getInstance();
		long value = regs.getOldValue(RegisterMgr.MEM_WB_LMD);
		regs.setValue(opcode.getDestination(), value);
	}
}
