package ui;

import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import ui.register.GptRegsPanel;
import ui.register.InternalRegsPanel;

public class MainPanel extends JPanel {
	/**/ private static final long serialVersionUID = 4977973746144888612L;
	private GptRegsPanel gptRegs;
	private InternalRegsPanel internalRegs;
	private MemoryPanel mems;
	private JButton oneCycleBtn;
	
	MainPanel() {
		this.setLayout(new MigLayout("wrap 3"));
		gptRegs = new GptRegsPanel();
		internalRegs = new InternalRegsPanel();
		mems = new MemoryPanel();
		
		oneCycleBtn = new JButton("One Cycle");
		this.add(oneCycleBtn, "span");
		
		this.add(gptRegs);
		this.add(internalRegs);
		this.add(mems);
	}
	
	
	void setRegister(int reg, long value) {
		gptRegs.setRegister(reg, value);
	}
	
	
	void setInternalRegisters(Map<String, Long> regs) {
		internalRegs.setRegisters(regs);
	}
	
	
	void setMem(int index, long value) {
		mems.setCell(index, value);
	}
	
	
	void addOneCycleButtonListener(ActionListener listener) {
		oneCycleBtn.addActionListener(listener);
	}
}
