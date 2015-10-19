package util;
import java.math.BigInteger;
import java.util.ArrayList;


public class Helper {
	private static final int HEXNUM =4;
	
	public static String IntToBinary(int number){
		return Integer.toBinaryString(number);
	}
	
	
	public static String IntToBinary5(int number) {
		return String.format("%05d", Integer.parseInt(IntToBinary(number)));
	}
	
	
	public static String IntToBinary6(int number) {
		return String.format("%06d", Integer.parseInt(IntToBinary(number)));
	}
	
	
	public static String BinaryToHex(String binary) throws Exception{
		int binLength = binary.length();
		if(binLength % 4 == 0){
			String hex = "";
			int counter = 1;
			String temp = "";
			
			for(int i = 0; i<binLength; i++){
				if(counter < HEXNUM){
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
			// we can throw exception here
			throw new Exception("Cannot convert to hex");
		}
	}
	
}
