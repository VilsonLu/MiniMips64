package instruction.itype;

public class Beq extends Itype {
	
	public Beq(String registerString) {
		super("BEQ", 4);
		String registers[] = registerString.split(","); // rs, rt, offset
		this.setRs(registers[0]);
		
	}
	
	
	@Override
	public String getStringCode() {
		return this.getStringCode2regOffset();
	}

	
	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

}
