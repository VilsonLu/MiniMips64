package api.instruction;

import api.instruction.alu.Daddu;
import api.instruction.alu.Dmult;
import api.instruction.alu.Or;
import api.instruction.branch.Beq;
import util.MipsException;
import util.MipsExceptionList;

public class InstructionFactory {
	
	public Instruction getInstruction(String line) throws MipsException, MipsExceptionList {
		line = line.trim();
		String[] splitLine = line.split(" ", 2);
		
		String arguments = splitLine[1];
		
		
		Instruction instruction = null;
		switch (splitLine[0].toLowerCase()) {
		// R-type
		case "daddu":
			arguments = InstructionUtil.formatRegisters(arguments);
			instruction = new Daddu(arguments);
			break;
		case "dmult":
			arguments = InstructionUtil.formatRegisters(arguments);
			instruction = new Dmult(arguments);
			break;
		case "or":
			arguments = InstructionUtil.formatRegisters(arguments);
			instruction = new Or(arguments);
			break;
			
		// I-type
		case "beq":
			arguments = InstructionUtil.formatRegisterOffset(arguments);
			instruction = new Beq(arguments);
			break;
			
		default: 
			throw new MipsException("Undefined instruction: " + splitLine[0]);
		}
		
		return instruction;
	}
}
