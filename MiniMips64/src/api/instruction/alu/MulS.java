package api.instruction.alu;

import api.instruction.opcode.ExtendedRtype;
import api.register.RegisterMgr;

public class MulS extends AluInstruction {
	private ExtendedRtype opcode;
	
	public MulS(String registerString) {
		super();
		opcode = new ExtendedRtype("MUL.S", 17, 22, 2);
		String registers[] = registerString.split(","); // FD,FS,FT  
		opcode.setD(registers[0]);
		opcode.setS(registers[1]);
		opcode.setT(registers[2]);
	}
	
	@Override
	long getExOperation() {
		// cheat mode. feels wrong
		RegisterMgr regs = RegisterMgr.getInstance();
		int a = (int) regs.getFValue(opcode.getS());
		int b = (int) regs.getFValue(opcode.getT());
		Float af = Float.intBitsToFloat(a);
		Float ab = Float.intBitsToFloat(b);
		Float sum = af * ab;
		
		return Float.floatToIntBits(sum);
	}

	@Override
	public String getStringCode() {
		return opcode.getStringCode3regs();
	}

}
