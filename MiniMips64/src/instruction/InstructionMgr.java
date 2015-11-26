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
	private List<Instruction> lines;
	
	
	static {
		instance = new InstructionMgr();
	}

	
	public static InstructionMgr getInstance() {
		return instance;
	}
	
	
	InstructionMgr() {
		lines = new ArrayList<>();
	}
	
	/**
	 * 
	 * @param index  This is the index, not the address.
	 * 				 Typically, index = address / 4.	
	 * @return
	 */
	public Instruction getInstruction(int index) {
		return lines.get(index);
	}
	
	
}
