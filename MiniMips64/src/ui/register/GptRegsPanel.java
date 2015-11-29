package ui.register;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;
import ui.MainFrame;
import util.Helper;

public class GptRegsPanel extends JPanel {
	
	/**/ private static final long serialVersionUID = 4384089892956276908L;
	private final int REG_LABEL_WIDTH = 30;
	private final int REG_VALUES_WIDTH = 130;

	private DefaultTableModel model;
	
	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
		GptRegsPanel panel = new GptRegsPanel();	
		frame.setPanel(panel);
	}
	
	
	public GptRegsPanel() {
		this.initComponents();
	}
	
	
	private void initComponents() {
		this.setLayout(new MigLayout("wrap 1"));
		model = new DefaultTableModel(17, 4);
		
		int col = 0;
		for (int i = 0; i < 32; i++) {
			int row = i % 16;
			if (i == 16) {
				col = 2;
			}
			model.setValueAt("r"+ i, row, col);
			model.setValueAt("0000 0000 0000 0000", row, col + 1);
		}
		
		JTable regsTable = new JTable(model);
		regsTable.getColumnModel().getColumn(0).setPreferredWidth(REG_LABEL_WIDTH);
		regsTable.getColumnModel().getColumn(2).setPreferredWidth(REG_LABEL_WIDTH);
		regsTable.getColumnModel().getColumn(1).setPreferredWidth(REG_VALUES_WIDTH);
		regsTable.getColumnModel().getColumn(3).setPreferredWidth(REG_VALUES_WIDTH);
		
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment( JLabel.RIGHT );
		regsTable.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
		regsTable.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
		regsTable.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
		regsTable.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		this.add(new JScrollPane(regsTable));
	}
	
	
	public void setRegister(int reg, long value) {
		int row = reg % 16;
		int col = 1;
		if (reg >= 16) {
			col = 3;
		}
		model.setValueAt(Helper.prettifyHex(value, 16), row, col);		
	}
}
