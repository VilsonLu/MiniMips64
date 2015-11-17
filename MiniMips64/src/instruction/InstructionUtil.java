package instruction;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import util.MipsException;
import util.MipsExceptionList;

public final class InstructionUtil {
	
	private InstructionUtil() {
	}
	
	
	private static String[] checkRegisters(String[] registers, int length) throws MipsExceptionList {
		MipsExceptionList exceptionList = new MipsExceptionList();
		for (int i = 0; i < registers.length; i++) {
			String register = registers[i]; 
			Pattern p = Pattern.compile("r[0-9]{1,2}");
			Matcher m = p.matcher(register);
			
			if (m.matches()) {
				register = register.replace("r", "");
				int number = Integer.parseInt(register);
				if (number >= 0 && number < 32) {
					registers[i] = Integer.toString(number);
				} else {
					MipsException ex = new MipsException("Register value should be between 0 and 31: " + register);
					exceptionList.add(ex);
				}
				
			} else {
				MipsException ex = new MipsException("Invalid register declaration: " + register);
				exceptionList.add(ex);
			}
		}
		
		if (!exceptionList.isEmpty()) {
			throw exceptionList;
		}
		
		return registers;
	}
	
	
	private static void checkOffset(String offset) throws MipsException {
		Pattern p = Pattern.compile("[A-F,0-9]{4}");
		Matcher m = p.matcher(offset);
		if (m.matches()) {
			
		}
		
		p = Pattern.compile("[0,1][A-F,0-9]{3}");
		m = p.matcher(offset);
		if (m.matches()) {
			
		}
	}
	
	
	public static String formatRegisters(String registerString) throws MipsExceptionList {
		registerString = registerString.replace(" ", "");
		String[] registers = registerString.split(","); 
		
		MipsExceptionList exceptionList = new MipsExceptionList();
		try {
			registers = InstructionUtil.checkRegisters(registers, registers.length);		
		} catch (MipsExceptionList e) {
			exceptionList = e;
		}
		
		if (!exceptionList.isEmpty()) {
			throw exceptionList;
		}
		
		return String.join(",", registers);
	}
	
	
	public static String formatRegisterOffset(String registerString) throws MipsExceptionList {
		registerString = registerString.replace(" ", "");
		String[] registers = registerString.split(","); 
		
		MipsExceptionList exceptionList = new MipsExceptionList();
				
		try {
			InstructionUtil.checkRegisters(registers, registers.length);
		} catch (MipsExceptionList e) {
			exceptionList = e;
		}
		
		
		
		if (!exceptionList.isEmpty()) {
			throw exceptionList;
		}
		
		return String.join(",", registers);		
	}
}
