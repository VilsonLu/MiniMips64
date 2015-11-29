package ui;

import java.util.Map;

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
	
	
	public void setRegister(int reg, long value) {
		panel.setRegister(reg, value);
	}
	
	public void setInternalRegisters(Map<String, Long> registers) {
		panel.setInternalRegisters(registers);
	}
	
	
	public void setMem(int index, long value) {
		panel.setMem(index, value);
	}
}
