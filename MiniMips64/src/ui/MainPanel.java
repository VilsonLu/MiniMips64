package ui;

import javax.swing.JPanel;

import ui.register.GptRegsPanel;

public class MainPanel extends JPanel {
	private GptRegsPanel gptRegs;
	
	MainPanel() {
		gptRegs = new GptRegsPanel();
		this.add(gptRegs);
	}
	
	
	void setRegister(int reg, long value) {
		gptRegs.setRegister(reg, value);
	}
}
