package util;
import java.math.BigInteger;


public class Helper {
	private static final int HEXNUM =4;
	
	
	public static String IntToBinary(int number){
		return Integer.toBinaryString(number);
	}
	
	
	public static String IntToBinary(long number){
		return Long.toBinaryString(number);
	}
	
	
	public static String IntToBinary5(int number) {
		return String.format("%05d", Integer.parseInt(IntToBinary(number)));
	}
	
	
	public static String IntToBinary16(long number) {
		return String.format("%016d", Long.parseLong(IntToBinary(number)));
	}
	
	
	public static String IntToBinary6(int number) {
		return String.format("%06d", Integer.parseInt(IntToBinary(number)));
	}
	
	
	/**
	 * Pads the value with zeros and adds spaces every 4 digits.
	 * @param value
	 * @return
	 */
	public static String prettifyHex(long value, int zeros) {
		String str = String.format("%0" + Integer.toString(zeros) + "x", value);
		StringBuilder result = new StringBuilder();
		
		for(int i = 0 ; i < str.length(); i++) {
		   if (i % 4 == 0 && i != 0) {
			   result = result.append(' ');
		   }
		   result = result.append(str.charAt(i));   
		}

		return result.toString();
	}
	
	
	public static String BinaryToHex(String binary) throws Exception {
		int binLength = binary.length();
		if (binLength % 4 == 0){
			String hex = "";
			int counter = 1;
			String temp = "";
			
			for(int i = 0; i < binLength; i++){
				if (counter < HEXNUM){
					temp += binary.charAt(i);
					
				} else {
					temp += binary.charAt(i);
					hex += (new BigInteger(temp,2)).toString(16);
					counter = 0;
					temp = "";
				}
				counter++;
			}
			
			return hex;
		} else {
			throw new Exception("Cannot convert to hex");
		}
	}
}
