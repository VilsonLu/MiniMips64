package instruction;

import util.MipsException;
import instruction.itype.Beq;
import instruction.rtype.Daddu;
import instruction.rtype.Dmult;
import instruction.rtype.Or;
import util.MipsExceptionList;

public class InstructionFactory {
	
	public Instruction getInstruction(String line) throws MipsException, MipsExceptionList {
		line = line.trim();
		String[] splitLine = line.split(" ", 2);
		
		String arguments = splitLine[1];
		arguments = InstructionUtil.formatRegisters(arguments);
		
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
			
		// I-type
		case "beq":
			instruction = new Beq(arguments);
			break;
			
		default: 
			throw new MipsException("Undefined instruction: " + splitLine[0]);
		}
		
		return instruction;
	}
}
