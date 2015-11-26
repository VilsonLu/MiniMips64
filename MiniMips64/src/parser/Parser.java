package parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import instruction.Instruction;
import instruction.InstructionFactory;
import util.MipsException;
import util.MipsExceptionList;

public class Parser {
	
	public static void main(String[] args) throws MipsException {
		new Parser().testParser();
	}
	
	void testParser() {
		File file = new File("mips.s");
		try {
			List<Instruction> instructions = parseFile(file);
			for (Instruction line : instructions) {
				System.out.println(line);
			}
			
		} catch (MipsExceptionList e) {
			for (MipsException exception : e.getExceptionList()) {
				System.out.println(exception.getMessage());
			}
		}
	}
	
	public List<Instruction> parseFile(File file) throws MipsExceptionList {
		List<Instruction> instructions = new ArrayList<>();
		MipsExceptionList exceptionList = new MipsExceptionList();
		try {
	        Scanner sc = new Scanner(file);
	        while (sc.hasNextLine()) {
	        	String line = sc.nextLine();
	        	if (line.trim().isEmpty()) {
	        		continue;
	        	}
	        	
	        	Instruction nextInstruction;
	        	
				try {
					nextInstruction = this.parseLine(line);
					if (nextInstruction == null) {
						continue;
					}
					instructions.add(nextInstruction);
				} catch (MipsException e) {
					e.setInstructionString(line);
					exceptionList.add(e);
				}
	        	
	        }
	        sc.close();
	    }
		catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
		
		if (!exceptionList.isEmpty()) {
			throw exceptionList;
		}
		
		return instructions;
	}
	
	public Instruction parseLine(String line) throws MipsException, MipsExceptionList {
		String[] splitLine = line.split(";", 2);
		String comment = "";
		if (splitLine[0].trim().isEmpty()) {
			return null;
		}
		
		if (splitLine.length > 1) {
			comment = splitLine[1].trim();
		}
		
		Instruction instruction = new InstructionFactory().getInstruction(splitLine[0]);
		instruction.setComment(comment);
		return instruction; 
	}
}
