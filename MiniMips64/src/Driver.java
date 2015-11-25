import instruction.Instruction;
<<<<<<< HEAD
import instruction.rtype.Daddu;
import instruction.rtype.Dmult;
import instruction.rtype.Or;
import instruction.rtype.Rtype;
=======
import instruction.alu.AluInstruction;
import instruction.alu.Daddu;
import instruction.alu.Dmult;
import instruction.alu.Or;
import instruction.opcode.Rtype;
>>>>>>> pipeline
import util.Helper;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			// System.out.println(Helper.BinaryToHex("11001111"));
			// System.out.println(Helper.BinaryToHex("11000110"));
			// System.out.println(Helper.IntToBinary5(18));
<<<<<<< HEAD
			new Driver().testOr();
			// new Driver().testMult();
			// new Driver().testHelper();
=======
			// new Driver().testOr();
			// new Driver().testMult();
			// new Driver().testHelper();
			new Driver().testImm();
//			new Driver().testSignExtend();
>>>>>>> pipeline
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void testHelper() {
		System.out.println(Helper.IntToBinary5(0));
	}
	
	void testOr() {
<<<<<<< HEAD
		Rtype dmult = new Or("1,2,3");
		System.out.println(dmult.getStringCode());
		System.out.println(dmult.getBinaryCode());
=======
		AluInstruction dmult = new Or("1,2,3");
		System.out.println(dmult.getStringCode());
		Long val = Long.parseLong(dmult.getBinaryCode(), 2);
		System.out.println(Long.toHexString(val));
		//System.out.println(Long.parseLong(dmult.getBinaryCode(), 2));
>>>>>>> pipeline
		try {
			System.out.println(Helper.BinaryToHex(dmult.getBinaryCode()));
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
<<<<<<< HEAD
=======
	
	void testImm() {
		long binary = 0x12345678;
		binary = binary % 0x10000;
		
		System.out.println(Long.toHexString(binary));
	}
	
	void testSignExtend() {
		long binary = 0x80;
		System.out.println(Long.toBinaryString(binary));
	}
>>>>>>> pipeline
}
