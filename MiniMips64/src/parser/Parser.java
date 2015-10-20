package parser;

import instruction.Instruction;
import instruction.InstructionFactory;
import instruction.Line;
import util.MipsException;

public class Parser {
	
	public Line parseLine(String line) throws MipsException {
		String[] splitLine = line.split(";", 2);
		String comment = "";
		if (splitLine.length > 1) {
			comment = splitLine[1];
		}
		
		Instruction instruction = InstructionFactory.getInstruction(line);
		return new Line(instruction, comment); 
	}
}
