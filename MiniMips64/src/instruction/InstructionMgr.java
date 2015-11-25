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
	private static InstructionFactory instance;
	private List<Line> lines;
	
	
	static {
		instance = new InstructionFactory();
	}

	
	public static InstructionFactory getInstance() {
		return instance;
	}
	
	
	InstructionMgr() {
		lines = new ArrayList<>();
	}
	
	
	public Instruction getInstruction(int index) {
		return lines.get(index).getInstruction();
	}
}
