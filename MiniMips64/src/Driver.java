import instruction.Daddu;
import instruction.Instruction;
import util.Helper;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			// System.out.println(Helper.BinaryToHex("11001111"));
			// System.out.println(Helper.BinaryToHex("11000110"));
			// System.out.println(Helper.IntToBinary5(18));
			new Driver().testInstruction();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void testInstruction() {
		Instruction daddu = new Daddu("1,2,3");
		System.out.println(daddu.getBinaryCode());
		try {
			System.out.println(Helper.BinaryToHex(daddu.getBinaryCode()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
