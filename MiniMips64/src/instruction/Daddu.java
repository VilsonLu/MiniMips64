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
	public void execute() {}

	@Override
	public String getStringCode() {
		StringBuilder code = new StringBuilder(this.getStringOpcode());
		code.append(" r");
		code.append(this.getRd());
		code.append(", r");
		code.append(this.getRs());
		code.append(", r");
		code.append(this.getRt());
		return code.toString();
	}
}
