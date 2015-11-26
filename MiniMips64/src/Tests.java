import java.util.ArrayList;
import java.util.Scanner;

import api.instruction.Instruction;
import api.instruction.InstructionMgr;
import api.instruction.alu.AluInstruction;
import api.instruction.alu.Daddu;
import api.instruction.alu.Or;
import api.memory.MemoryMgr;
import api.pipeline.Pipeline;
import api.register.RegisterMgr;
import ui.UiFacade;
import util.Helper;

public class Tests {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
//			new Tests().testOr();
//			new Tests().testMult();
//			new Tests().testHelper();
//			new Tests().testImm();
//			new Tests().testSignExtend();
//			new Tests().testMemory();
			new Tests().testPipeline();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	void testPipeline() {
		MemoryMgr mems = MemoryMgr.getInstance();
		for (int i = 0; i < 20; i++) {
			mems.set(i, (byte) i);
		}
		
		RegisterMgr regs = RegisterMgr.getInstance();
		regs.setValue(1, 3);
		regs.setValue(2, 5);
		regs.setValue(3, 7);
		
		UiFacade ui = new UiFacade();
		ui.setRegister(1, 3);
		ui.setRegister(2, 5);
		ui.setRegister(3, 7);
		
		Instruction a = new Daddu("4,1,2");
		Instruction b = new Or("5,2,3");
		ArrayList<Instruction> list = new ArrayList<>();
		list.add(a);
		list.add(b);
		
		InstructionMgr instructions = InstructionMgr.getInstance();
		instructions.setInstructions(list);
		
		System.out.println(regs.getValue(4));
		
		Pipeline pipeline = new Pipeline();
		Scanner scan = new Scanner(System.in);
		for (int i = 0; i < 10; i++) {
			pipeline.performCycle();
			pipeline.printContents();
			long value = regs.getValue(4);
			ui.setRegister(4, value);
			scan.nextLine();
			System.out.println("-----\n");
			
		}
	}
	
	
	void testHelper() {
		System.out.println(Helper.IntToBinary5(0));
	}
	
	
	void testOr() {
		AluInstruction dmult = new Or("1,2,3");
		System.out.println(dmult.getStringCode());
		Long val = Long.parseLong(dmult.getBinaryCode(), 2);
		System.out.println(Long.toHexString(val));
		//System.out.println(Long.parseLong(dmult.getBinaryCode(), 2));
		try {
			System.out.println(Helper.BinaryToHex(dmult.getBinaryCode()));
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	
	void testImm() {
		long binary = 0x12345678;
		binary = binary % 0x10000;
		
		System.out.println(Long.toHexString(binary));
	}
	
	
	void testSignExtend() {
		long binary = 0x80;
		System.out.println(Long.toBinaryString(binary));
	}
	
	
	void testMemory() {
		byte[] mem = new byte[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
		long value = 0;
		for (int i = 0; i < 8; i++) {
			byte ret = mem[i];
			
			long converted = ret << (i*4) & 0xFFFF_FFFFL;
			System.out.println(Long.toHexString(converted));
			value = value + converted;
		}
		System.out.println(Long.toHexString(value));
	}
}
