package instruction;

public class Daddu extends Rtype {
	
	public Daddu(String registerString) {
		super("DADDU", 45);
		String registers[] = registerString.split(","); // rd, rs, rt
		this.setRd(registers[0]);
		this.setRs(registers[1]);
		this.setRt(registers[2]);
	}

	
	@Override
	public String getStringCode() {
		return this.getStringCode3regs();
	}
	

	@Override
	public void execute() {}
}
