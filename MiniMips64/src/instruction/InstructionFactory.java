package instruction;

import util.MipsException;

public class InstructionFactory {
	
	
	private String formatRegisters(String registers) {
		String formatted = "";
		formatted = registers.replace("r", "");
		formatted = formatted.replace(" ", "");
		
		return formatted;
	}
	
	public Instruction getInstruction(String line) throws MipsException {
		line = line.trim();
		String[] splitLine = line.split(" ", 2);
		
		String arguments = splitLine[1];
		arguments = this.formatRegisters(arguments);
		
		Instruction instruction = null;
		switch (splitLine[0].toLowerCase()) {
		
		// R-type
		case "daddu":
			instruction = new Daddu(arguments);
			break;
		case "dmult":
			instruction = new Dmult(arguments);
			break;
		case "or":
			instruction = new Or(arguments);
			break;
		
		default: 
			throw new MipsException("Undefined instruction: " + splitLine[0]);
		}
		
		return instruction;
	}
}
