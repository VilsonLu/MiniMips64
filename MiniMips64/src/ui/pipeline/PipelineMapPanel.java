package ui.pipeline;

import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import api.instruction.Instruction;
import api.instruction.alu.Daddu;
import api.instruction.alu.Dmult;
import api.instruction.alu.Or;
import net.miginfocom.swing.MigLayout;
import ui.MainFrame;

public class PipelineMapPanel extends JPanel {
	/**/ private static final long serialVersionUID = -8747032341270175861L;
	private DefaultTableModel model;
	private final int INSTRUCTION_WIDTH = 100;
	private final int PIPELINE_WIDTH = 20;
	private int lastRow = 0;
	private int cycle = 0;
	private Map<Integer, Integer> instructionRowCache = new HashMap<>();
	private JTable table;
	private JScrollPane scrollPane;
	private boolean newRowAdded = false;
	
	public PipelineMapPanel() {
		this.initComponents();
	}
	
	
	public static void main(String[] args) {
		
		MainFrame frame = new MainFrame();
		PipelineMapPanel panel = new PipelineMapPanel();
		frame.setPanel(panel);
		
		Instruction a = new Daddu("1,2,3");
		Instruction b = new Or("4,5,6");
		Instruction c = new Dmult("2,3");
		
		a.setId(0);
		b.setId(1);
		c.setId(2);
		
		Map<Instruction, String> p1 = new HashMap<>(); 
		p1.put(a, "IF");
		panel.insertCycle(p1);
		
		Map<Instruction, String> p2 = new HashMap<>(); 
		p2.put(a, "ID");
		p2.put(b, "IF");
		panel.insertCycle(p2);
		
		Map<Instruction, String> p3 = new HashMap<>(); 
		p3.put(a, "EX");
		p3.put(b, "ID");
		p3.put(c, "IF");
		panel.insertCycle(p3);
	}
	
	private void initComponents() {
		this.setLayout(new MigLayout("wrap 1"));
		model = new DefaultTableModel(0, 1);
		
		table = new JTable(model);
		
		
		table.getColumnModel().getColumn(0).setMinWidth(INSTRUCTION_WIDTH);
		scrollPane = new JScrollPane(table);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		this.add(scrollPane);
		
	}
	
	
	public void insertCycle(Map<Instruction, String> pipeline) {
		cycle++;
		model.addColumn(cycle);
		for (Instruction instruction : pipeline.keySet()) {
			int row = this.getRow(instruction);
			if (newRowAdded) {
				model.addRow(new String[] {instruction.getStringCode()});
			}
			model.setValueAt(pipeline.get(instruction), row - 1, cycle);
		}		
		table.getColumnModel().getColumn(0).setMinWidth(INSTRUCTION_WIDTH);
		// table.getSelectionModel().setSelectionInterval(model.getRowCount() - 1, model.getRowCount() - 1);
		table.scrollRectToVisible(new Rectangle(table.getCellRect(model.getRowCount() - 1, cycle, true)));
	}
	
	
	private int getRow(Instruction instruction) {
		Integer row = instructionRowCache.get(instruction.getId());
		newRowAdded = false;
		if (row == null) {
			row = ++lastRow;
			instructionRowCache.put(instruction.getId(), lastRow);
			newRowAdded = true;
		}
		return row.intValue();
	}
}
