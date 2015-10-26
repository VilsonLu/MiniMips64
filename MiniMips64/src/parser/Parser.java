package parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import instruction.Instruction;
import instruction.InstructionFactory;
import instruction.Line;
import util.MipsException;
import util.MipsExceptionList;

public class Parser {
	
	public static void main(String[] args) throws MipsException {
		new Parser().testParser();
	}
	
	void testParser() {
		File file = new File("mips.s");
		try {
			List<Line> lines = parseFile(file);
			for (Line line : lines) {
				System.out.println(line);
			}
			
		} catch (MipsExceptionList e) {
			for (MipsException exception : e.getExceptionList()) {
				System.out.println(exception.getMessage());
			}
		}
	}
	
	public List<Line> parseFile(File file) throws MipsExceptionList {
		List<Line> lines = new ArrayList<>();
		MipsExceptionList exceptionList = new MipsExceptionList();
		try {
	        Scanner sc = new Scanner(file);
	        while (sc.hasNextLine()) {
	        	String line = sc.nextLine();
	        	if (line.trim().isEmpty()) {
	        		continue;
	        	}
	        	
	        	Line nextline;
				try {
					nextline = this.parseLine(line);
					if (nextline == null) {
						continue;
					}
					lines.add(nextline);
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
		
		return lines;
	}
	
	public Line parseLine(String line) throws MipsException, MipsExceptionList {
		String[] splitLine = line.split(";", 2);
		String comment = "";
		if (splitLine[0].trim().isEmpty()) {
			return null;
		}
		
		if (splitLine.length > 1) {
			comment = splitLine[1].trim();
		}
		
		Instruction instruction = new InstructionFactory().getInstruction(splitLine[0]);
		return new Line(instruction, comment); 
	}
}
