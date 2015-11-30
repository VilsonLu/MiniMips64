package ui;

import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;

import api.instruction.Instruction;
import net.miginfocom.swing.MigLayout;
import ui.pipeline.PipelineMapPanel;
import ui.register.GptRegsPanel;
import ui.register.InternalRegsPanel;

public class MainPanel extends JPanel {
	/**/ private static final long serialVersionUID = 4977973746144888612L;
	private JButton oneCycleBtn;
	private GptRegsPanel gptRegs;
	private InternalRegsPanel internalRegs;
	private MemoryPanel mems;
	private PipelineMapPanel pipelineMap;
	
	
	MainPanel() {
		this.setLayout(new MigLayout("wrap 3"));
		gptRegs = new GptRegsPanel();
		internalRegs = new InternalRegsPanel();
		mems = new MemoryPanel();
		pipelineMap = new PipelineMapPanel();
		
		oneCycleBtn = new JButton("One Cycle");
		this.add(oneCycleBtn, "span");
		
		this.add(gptRegs);
		this.add(internalRegs);
		this.add(mems);
		this.add(pipelineMap);
	}
	
	
	void setR(int reg, long value) {
		gptRegs.setR(reg, value);
	}
	
	
	void setF(int reg, long value) {
		gptRegs.setF(reg, value);
	}
	
	
	void setHi(long value) {
		gptRegs.setHi(value);
	}
	
	
	void setLo(long value) {
		gptRegs.setLo(value);
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
	
	
	void updatePipelineMap(Map<Instruction, String> pipeline) {
		pipelineMap.insertCycle(pipeline);
	}
}
