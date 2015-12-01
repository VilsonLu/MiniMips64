package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import api.instruction.Instruction;
import api.instruction.InstructionMgr;
import api.instruction.alu.Daddu;
import api.instruction.alu.Or;
import api.instruction.branch.Beq;
import api.memory.MemoryListener;
import api.memory.MemoryMgr;
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
		
		MemoryMgr mems = MemoryMgr.getInstance();
		mems.set(0x2009L, (byte) 5);
	}
	
	
	private List<Instruction> getInstructions() {
		//Instruction a = new Daddu("3,1,2");
		Instruction b = new Beq("1,2,3");
		Instruction c = new Daddu("3,1,2");
		Instruction d = new Or("3,1,2");
		Instruction e = new Daddu("5,6,7");
		Instruction f = new Or("8,9,10");
		ArrayList<Instruction> list = new ArrayList<>();
		//list.add(a);
		list.add(b);
		list.add(c);
		list.add(d);
		list.add(e);
		list.add(f);
		
		return list;
	}
	
	
	public Controller() {
		ui.addOneCycleButtonListener(new OneCycleListener());
		RegisterMgr regs = RegisterMgr.getInstance();
		InstructionMgr instructions = InstructionMgr.getInstance();
		MemoryMgr mems = MemoryMgr.getInstance();
		instructions.setInstructions(this.getInstructions());
		
		ui.setInternalRegisters(regs.getInternalRegs());
		mems.setListener(new MemListener());
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
	
	private class MemListener implements MemoryListener {

		@Override
		public void memChanged(int index, long value) {
			ui.setDataMem(index, value);
		}
	}
	
	
	private void runOneCycle() {
		pipeline.performCycle();
		RegisterMgr regs = RegisterMgr.getInstance();
		ui.setInternalRegisters( regs.getInternalRegs() );
		ui.updatePipelineMap( pipeline.getInstructionPipelines() );
	}
	
	
	
	private class OneCycleListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Controller.this.runOneCycle();
		}
	}
}
