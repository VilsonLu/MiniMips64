package instruction;

import util.MipsException;

public class InstructionFactory {
	
	public static void main(String[] args) throws MipsException {
		String line = "Daddu r1, r2, r3";
		Instruction i = new InstructionFactory().getInstruction(line);
		System.out.println(i.getBinaryCode());
	}
	
	private static String formatRegisters(String registers) {
		String formatted = "";
		formatted = registers.replace("r", "");
		formatted = formatted.replace(" ", "");
		
		return formatted;
	}
	
	public static Instruction getInstruction(String line) throws MipsException {
		line = line.trim();
		String[] splitLine = line.split(" ", 2);
		String arguments = splitLine[1];
		arguments = InstructionFactory.formatRegisters(arguments);
		
		Instruction instruction = null;
		switch (splitLine[0].toLowerCase()) {
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
			throw new MipsException("Undefined instruction.");
		}
		
		return instruction;
	}
}
