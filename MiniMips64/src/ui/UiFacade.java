package ui;

import java.awt.event.ActionListener;
import java.util.Map;

import api.instruction.Instruction;

public class UiFacade {
	MainPanel panel;
	
	public static void main(String[] args) {
		UiFacade facade = new UiFacade();
		
	}
	
	public UiFacade() {
		MainFrame frame = new MainFrame();
		panel = new MainPanel();
		frame.setPanel(panel);
	}
	
	
	public void setR(int reg, long value) {
		panel.setR(reg, value);
	}

	
	public void setF(int reg, long value) {
		panel.setF(reg, value);
	}


	public void setHi(long value) {
		panel.setHi(value);
	}

	
	public void setLo(long value) {
		panel.setLo(value);
	}

	
	public void setInternalRegisters(Map<String, Long> registers) {
		panel.setInternalRegisters(registers);
	}
	
	
	public void setMem(int index, long value) {
		panel.setMem(index, value);
	}
	
	
	public void addOneCycleButtonListener(ActionListener listener) {
		panel.addOneCycleButtonListener(listener);
	}
	
	
	public void updatePipelineMap(Map<Instruction, String> pipeline) {
		panel.updatePipelineMap(pipeline);
	}
}
