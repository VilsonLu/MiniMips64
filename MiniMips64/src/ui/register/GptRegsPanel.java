package ui.register;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;

public class GptRegsPanel extends JPanel {
	/**/
	private static final long serialVersionUID = 1L;
	
	private final int REG_LABEL_WIDTH = 30;
	private final int REG_VALUES_WIDTH = 130;

	private DefaultTableModel model;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Regs test");
		GptRegsPanel panel = new GptRegsPanel();
		
		frame.getContentPane().add(panel);
		frame.setSize(640, 480);
		frame.setVisible(true);
		panel.setRegister(4, 0x123f);
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
		this.add(regsTable);
	}
	
	
	public void setRegister(int reg, long value) {
		int row = reg % 16;
		int col = 1;
		if (reg >= 16) {
			col = 3;
		}
		model.setValueAt(this.prettifyHex(value), row, col);		
	}
	
	
	/**
	 * Pads the value with zeros and adds spaces every 4 digits.
	 * @param value
	 * @return
	 */
	private String prettifyHex(long value) {
		String str = String.format("%016x", value);
		StringBuilder result = new StringBuilder();
		
		for(int i = 0 ; i < str.length(); i++) {
		   if (i % 4 == 0 && i != 0) {
			   result = result.append(' ');
		   }
		   result = result.append(str.charAt(i));   
		}

		return result.toString();
	}
}
