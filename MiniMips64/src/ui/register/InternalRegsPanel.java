package ui.register;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import api.register.RegisterCell;
import api.register.RegisterMgr;
import net.miginfocom.swing.MigLayout;
import ui.MainFrame;

public class InternalRegsPanel extends JPanel {
	/**/ private static final long serialVersionUID = 8096556191250917667L;
	private final int REG_LABEL_WIDTH = 110;
	private final int REG_VALUES_WIDTH = 130;
	
	private DefaultTableModel model;
	private Map<String, Integer> order = new HashMap<>();
	
	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
		InternalRegsPanel panel = new InternalRegsPanel();
		frame.setPanel(panel);
	}
	
	
	public InternalRegsPanel() {
		this.setOrders();
		this.initComponents();
	}
	
	private void setOrders() {
		order.put(RegisterMgr.PC, 0);
		order.put(RegisterMgr.IF_ID_IR, 1);
		order.put(RegisterMgr.IF_ID_NPC, 2);
		// 3
		order.put(RegisterMgr.ID_EX_IR, 4);
		order.put(RegisterMgr.ID_EX_A, 5);
		order.put(RegisterMgr.ID_EX_B, 6);
		order.put(RegisterMgr.ID_EX_IMM, 7);
		order.put(RegisterMgr.ID_EX_NPC, 8);
		// 9
		order.put(RegisterMgr.EX_MEM_IR, 10);
		order.put(RegisterMgr.EX_MEM_ALUOUTPUT, 11);
		order.put(RegisterMgr.EX_MEM_B, 12);
		order.put(RegisterMgr.EX_MEM_COND, 13);
		// 14
		order.put(RegisterMgr.MEM_WB_IR, 15);
		order.put(RegisterMgr.MEM_WB_ALUOUTPUT, 16);
		order.put(RegisterMgr.MEM_WB_LMD, 17);
	}
	
	
	private void initComponents() {
		this.setLayout(new MigLayout("wrap 1"));
		model = new DefaultTableModel(18, 2);
		RegisterMgr regs = RegisterMgr.getInstance();
		Map<String, RegisterCell> internalRegs = regs.getInternalRegs(); 
		
		for (String regName : internalRegs.keySet()) {
			int row = order.get(regName);
			model.setValueAt(regName, row, 0);
		}
		
		JTable regsTable = new JTable(model);
		regsTable.getColumnModel().getColumn(0).setPreferredWidth(REG_LABEL_WIDTH);
		regsTable.getColumnModel().getColumn(1).setPreferredWidth(REG_VALUES_WIDTH);
		this.add(regsTable);
	}
}
