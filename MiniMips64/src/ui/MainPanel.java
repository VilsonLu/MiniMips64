package ui;

import java.util.Map;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import ui.register.GptRegsPanel;
import ui.register.InternalRegsPanel;

public class MainPanel extends JPanel {
	/**/ private static final long serialVersionUID = 4977973746144888612L;
	private GptRegsPanel gptRegs;
	private InternalRegsPanel internalRegs;
	
	MainPanel() {
		this.setLayout(new MigLayout("wrap 1"));
		gptRegs = new GptRegsPanel();
		internalRegs = new InternalRegsPanel();
		
		this.add(gptRegs);
		this.add(internalRegs);
	}
	
	
	void setRegister(int reg, long value) {
		gptRegs.setRegister(reg, value);
	}
	
	
	void setInternalRegisters(Map<String, Long> regs) {
		internalRegs.setRegisters(regs);
	}
}
