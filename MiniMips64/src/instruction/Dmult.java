package instruction;

public class Dmult extends Rtype {

	public Dmult(String registerString) {
		super("DMULT", 28);
		String registers[] = registerString.split(","); // rs, rt
		this.setRs(registers[0]);
		this.setRt(registers[1]);
	}

	@Override
	public String getStringCode() {
		return this.getStringCode2regs();
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

}
