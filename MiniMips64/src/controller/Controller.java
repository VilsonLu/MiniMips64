package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import api.instruction.Instruction;
import api.instruction.InstructionMgr;
import api.instruction.alu.Daddu;
import api.instruction.alu.Or;
import api.pipeline.Pipeline;
import api.register.RegisterListener;
import api.register.RegisterMgr;
import ui.UiFacade;

public class Controller {
	private Pipeline pipeline = new Pipeline();
	private UiFacade ui = new UiFacade();
	
	
	public static void main(String[] args) {
		new Controller();
		RegisterMgr regs = RegisterMgr.getInstance();
		regs.setRValue(1, 3);
		regs.setRValue(2, 5);
		regs.setRValue(3, 6);
	}
	
	private List<Instruction> getInstructions() {
		Instruction a = new Daddu("4,1,2");
		Instruction b = new Or("5,2,3");
		ArrayList<Instruction> list = new ArrayList<>();
		list.add(a);
		list.add(b);
		
		return list;
	}
	
	public Controller() {
		ui.addOneCycleButtonListener(new OneCycleListener());
		RegisterMgr regs = RegisterMgr.getInstance();
		InstructionMgr instructions = InstructionMgr.getInstance();
		instructions.setInstructions(this.getInstructions());
		
		ui.setInternalRegisters(regs.getInternalRegs());
		regs.setListener(new RegListener());
	}
	
	
	private class RegListener implements RegisterListener {

		@Override
		public void rChanged(int index, long value) {
			ui.setR(index, value);
		}

		@Override
		public void fChanged(int index, long value) {
			ui.setF(index, value);
		}

		@Override
		public void hiChanged(long value) {
			ui.setHi(value);
		}

		@Override
		public void loChanged(long value) {
			ui.setLo(value);
		}
	}
	
	private class OneCycleListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			pipeline.performCycle();
			RegisterMgr regs = RegisterMgr.getInstance();
			ui.setInternalRegisters( regs.getInternalRegs() );
		}
	}
	
	
}
