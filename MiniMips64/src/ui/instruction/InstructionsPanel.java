package ui.instruction;

import java.util.ArrayList;
import java.util.List;

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
import util.Helper;

public class InstructionsPanel extends JPanel {
	/**/ private static final long serialVersionUID = 7488239579864242356L;
	private DefaultTableModel model;
	private final int INSTRUCTION_WIDTH = 100;
	
	
	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
		InstructionsPanel panel = new InstructionsPanel();	
		frame.setPanel(panel);
		
		Instruction a = new Daddu("1,2,3");
		Instruction b = new Or("4,5,6");
		Instruction c = new Dmult("2,3");
		
		List<Instruction> is = new ArrayList<>();
		is.add(a);
		is.add(b);
		is.add(c);
		
		panel.setInstructions(is);
	}
	
	
	public InstructionsPanel() {
		this.initComponents();
	}
	
	
	private void initComponents() {
		this.setLayout(new MigLayout("wrap 1"));
		model = new DefaultTableModel(0, 5);
		
		JTable regsTable = new JTable(model);
		regsTable.getColumnModel().getColumn(3).setPreferredWidth(INSTRUCTION_WIDTH);
		
		this.add(new JScrollPane(regsTable));
	}
	
	
	public void setInstructions(List<Instruction> instructions) {
		for (int i = 0; i < instructions.size(); i++) {
			Instruction instruction = instructions.get(i);
			String address = Helper.prettifyHex(i * 4, 4);
			long representationL = instruction.getLongCode();
			String representation = Helper.prettifyHex(representationL, 8);
			String label = instruction.getLabel();
			String instructionStr = instruction.getStringCode();
			String comment = instruction.getComment();
			String[] row = new String[] {address, representation, label, instructionStr, comment};
			model.addRow(row);
		}
	}
}
