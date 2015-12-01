package api.instruction.loadstore;

import java.util.ArrayList;
import java.util.List;

import api.instruction.opcode.Itype;
import api.memory.MemoryMgr;
import api.register.RegisterMgr;

public class Sw extends LoadStoreInstruction {
	private Itype opcode;
	
	public Sw(String registerString) {
		super();
		opcode = new Itype("LWU", 37);
		this.setOpcode(opcode);
		String registers[] = registerString.split(","); // rd, offset, rs
		opcode.setRt(registers[0]);
		opcode.setImm(registers[1]);
		opcode.setRs(registers[2]);
		System.out.println(opcode.getBinaryCode());
	}
	
	
	@Override
	public void id() {
		super.id();
		RegisterMgr regs = RegisterMgr.getInstance();
		long a = regs.getFValue(opcode.getRs());
		long b = regs.getFValue(opcode.getRt());
		regs.setValue(RegisterMgr.ID_EX_A, a);
		regs.setValue(RegisterMgr.ID_EX_B, b);
	}

	
	@Override
	public boolean outputsTo(List<String> regs) {
		return false;
	}


	@Override
	public List<String> getInputs() {
		ArrayList<String> inputs = new ArrayList<>();
		if (opcode.getRs() != 0) {
			inputs.add("r"+ opcode.getRs());	
		}
		return null;
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
		long b = regs.getValue(RegisterMgr.EX_MEM_B);
		for (int i = 0; i < 4; i++) {
			byte value = (byte) ((byte) b % 0x100);
			b = b / 0x100;
			mems.set(location + 1, value);
		}
	}

	
	@Override
	public void wb() {
	}
}
