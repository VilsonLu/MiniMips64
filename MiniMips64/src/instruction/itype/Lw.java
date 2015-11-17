package instruction.itype;

public class Lw extends Itype {
	
	public Lw(String registerString) {
		super("LW", 35);
		String registers[] = registerString.split(","); // rd, offset, rs
		this.setRt(registers[0]);
		this.setImm(registers[1]);
		this.setRs(registers[2]);
	}

	
	@Override
	public String getStringCode() {
		return this.getStringCodeRegOffsetReg();
	}


	@Override
	public void execute() {
	}

	
}
