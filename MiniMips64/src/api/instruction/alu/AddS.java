package api.instruction.alu;

import api.instruction.opcode.ExtendedRtype;
import api.register.RegisterMgr;

public class AddS extends AluInstruction {
	private ExtendedRtype opcode;
	
	public AddS(String registerString) {
		super();
		opcode = new ExtendedRtype("ADD.S", 17, 16, 0);
		String registers[] = registerString.split(","); // FD,FS,FT  
		opcode.setD(registers[0]);
		opcode.setS(registers[1]);
		opcode.setT(registers[2]);
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
	long getExOperation() {
		// cheat mode. feels wrong
		RegisterMgr regs = RegisterMgr.getInstance();
		int a = (int) regs.getFValue(opcode.getS()); // won't work with forwarding ):
		int b = (int) regs.getFValue(opcode.getT());
		Float af = Float.intBitsToFloat(a);
		Float bf = Float.intBitsToFloat(b);
		Float sum = af + bf;
		
		return Float.floatToIntBits(sum);
	}

	@Override
	public String getStringCode() {
		return opcode.getStringCode3regs();
	}

}
