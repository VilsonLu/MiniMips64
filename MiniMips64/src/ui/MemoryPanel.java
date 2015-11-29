package ui;

import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import api.memory.MemoryCell;
import api.memory.MemoryMgr;
import net.miginfocom.swing.MigLayout;
import util.Helper;

public class MemoryPanel extends JPanel {
	/**/ private static final long serialVersionUID = 7996411922209799402L;
	private DefaultTableModel model;
	
	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
		MemoryPanel panel = new MemoryPanel();
		frame.setPanel(panel);
		panel.setCell(2, 0x030a);
	}
	
	public MemoryPanel() {
		this.initComponents();
	}
	
	
	private void initComponents() {
		this.setLayout(new MigLayout("wrap 1"));
		MemoryMgr mems = new MemoryMgr();
		long dataSegmentStart = mems.getDataSegmentStart();
		int totalDataSegments = mems.totalDataSegments();
		int memIntervals = MemoryCell.BYTES;
		String representationStr = Helper.prettifyHex(0, 8);
		
		model = new DefaultTableModel(0, 5);
		for (int i = 0; i < totalDataSegments; i++) {
			long address = dataSegmentStart + (i * memIntervals);
			String addressStr = Helper.prettifyHex(address, 4);
			model.addRow(new String[]{addressStr, representationStr});
		}
		
		JTable memsTable = new JTable(model);
		this.add(new JScrollPane(memsTable));
	}
	
	
	public void setCell(int index, long value) {
		String prettyValue = Helper.prettifyHex(value, 8);
		model.setValueAt(prettyValue, index, 1);
	}
}
