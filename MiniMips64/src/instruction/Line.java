package instruction;

public class Line {
	private Instruction instruction;
	private String comment;
	
	public Line(Instruction instruction, String comment) {
		this.instruction = instruction;
		this.comment = comment;
	}
	
	public Instruction getInstruction() {
		return instruction;
	}
	
	public String getComment() {
		return comment;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(this.getInstruction().toString());
		if (!this.getComment().isEmpty()) {
			sb.append(" ;");
			sb.append(this.getComment());
		}
		return sb.toString();
	}
}
