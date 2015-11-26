package ui;


public class UiFacade {
	MainPanel panel;
	
	public UiFacade() {
		MainFrame frame = new MainFrame();
		panel = new MainPanel();
		frame.setPanel(panel);
	}
	
	
	public void setRegister(int reg, long value) {
		panel.setRegister(reg, value);
	}
}
