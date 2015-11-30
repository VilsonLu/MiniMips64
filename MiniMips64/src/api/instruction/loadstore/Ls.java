package api.instruction.loadstore;

import api.instruction.opcode.Itype;
import api.memory.MemoryMgr;
import api.register.RegisterMgr;

// test if we can just extend Lwu
public class Ls extends LoadStoreInstruction {
	private Itype opcode;
	
	public Ls(String registerString) {
		super();
		opcode = new Itype("L.S", 49);
		this.setOpcode(opcode);
		String registers[] = registerString.split(","); // rd, offset, rs
		opcode.setRt(registers[0]);
		opcode.setImm(registers[1]);
		opcode.setRs(registers[2]);
		opcode.setDestination("f" + registers[0]);
	}
	
	
	@Override
	public String getStringCode() {
		return opcode.getStringCodeRegOffsetReg();
	}

	
	@Override
	public void mem() {
		MemoryMgr mems = MemoryMgr.getInstance();
		RegisterMgr regs = RegisterMgr.getInstance();
		long location = regs.getValue(RegisterMgr.EX_MEM_ALUOUTPUT);
		long lmd = 0;
		for (int i = 0; i < 4; i++) {
			byte value = mems.get(location + i);
			long converted = value << (i * 4) & 0xFFFF_FFFFL;
			lmd = lmd + converted;
		}
		regs.setValue(RegisterMgr.MEM_WB_LMD, lmd);
	}

	@Override
	public void wb() {
		RegisterMgr regs = RegisterMgr.getInstance();
		long value = regs.getValue(RegisterMgr.MEM_WB_LMD);
		regs.setValue(opcode.getDestination(), value);
	}
}
