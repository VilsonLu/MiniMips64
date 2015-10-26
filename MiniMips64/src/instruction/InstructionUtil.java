package instruction;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import util.MipsException;
import util.MipsExceptionList;

public final class InstructionUtil {
	
	private InstructionUtil() {
	}
	
	
	public static String formatRegisters(String registerString) throws MipsExceptionList {
		registerString = registerString.replace(" ", "");
		String[] registers = registerString.split(","); 
		
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
		
		return String.join(",", registers);
		
	}
}
