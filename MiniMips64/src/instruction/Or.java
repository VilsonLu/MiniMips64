package instruction;

public class Or extends Rtype {
	
	public Or(String registerString) {
		super("OR", 37);
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
	public void execute() {
		// TODO Auto-generated method stub

	}

}
