package api.instruction.alu;

import java.math.BigInteger;

import api.instruction.opcode.Rtype;
import api.register.RegisterMgr;

public class Dmult extends AluInstruction {
	private Rtype opcode;
	private long hi;
	private long lo;
	
	public Dmult(String registerString) {
		super();
		opcode = new Rtype("DMULT", 28);
		this.setOpcode(opcode);
		
		String registers[] = registerString.split(","); // rs, rt
		opcode.setRs(registers[0]);
		opcode.setRt(registers[1]);
		opcode.setDestination("lo");
	}

	
	@Override
	public String getStringCode() {
		return opcode.getStringCode2regs();
	}

	
	@Override
	long getExOperation() {
		RegisterMgr regs = RegisterMgr.getInstance();
		long a = regs.getValue(RegisterMgr.ID_EX_A);
		long b = regs.getValue(RegisterMgr.ID_EX_B);
		BigInteger bigA = new BigInteger(Long.toString(a));
		BigInteger bigB = new BigInteger(Long.toString(b));
		BigInteger product = bigA.multiply(bigB);
		
		BigInteger divisor = new BigInteger("100000000", 16);
		BigInteger bigHi = product.divide(divisor);
		BigInteger bigLo = product.mod(divisor);
		
		hi = bigHi.longValue();
		lo = bigLo.longValue();
		return lo;
	}
	
	
	public void wb() {
		super.wb();
		RegisterMgr regs = RegisterMgr.getInstance();
		regs.setValue("hi", hi);
	}
}
