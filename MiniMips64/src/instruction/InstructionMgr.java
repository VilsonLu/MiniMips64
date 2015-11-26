package instruction;

import java.util.ArrayList;
import java.util.List;

/**
 * Serves as the actual storage of lines.
 * RegisterMgr stores the binary codes for easy referencing. 
 * @author Ivan Paner
 *
 */

public class InstructionMgr {
	private static InstructionMgr instance;
	private List<Instruction> instructions;
	
	
	static {
		instance = new InstructionMgr();
	}

	
	public static InstructionMgr getInstance() {
		return instance;
	}
	
	
	InstructionMgr() {
		instructions = new ArrayList<>();
	}
	
	/**
	 * 
	 * @param index  This is the index, not the address.
	 * 				 Typically, index = address / 4.	
	 * @return
	 */
	public Instruction getInstruction(int index) {
		try {
			return instructions.get(index);	
		} catch (IndexOutOfBoundsException ex){
			return null;
		}
		
	}
	
	public void setInstructions(List<Instruction> instructions) {
		for (int i = 0; i < instructions.size(); i++) {
			instructions.get(i).setOrder(i);
		}
		this.instructions = instructions;
	}
}
