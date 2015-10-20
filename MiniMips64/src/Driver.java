import instruction.Daddu;
import instruction.Dmult;
import instruction.Instruction;
import instruction.Or;
import instruction.Rtype;
import util.Helper;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			// System.out.println(Helper.BinaryToHex("11001111"));
			// System.out.println(Helper.BinaryToHex("11000110"));
			// System.out.println(Helper.IntToBinary5(18));
			new Driver().testOr();
			// new Driver().testMult();
			// new Driver().testHelper();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void testHelper() {
		System.out.println(Helper.IntToBinary5(0));
	}
	
	void testOr() {
		Rtype dmult = new Or("1,2,3");
		System.out.println(dmult.getStringCode());
		System.out.println(dmult.getBinaryCode());
		try {
			System.out.println(Helper.BinaryToHex(dmult.getBinaryCode()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
