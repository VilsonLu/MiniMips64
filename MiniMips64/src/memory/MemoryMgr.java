package memory;

import java.util.ArrayList;
import java.util.List;

public class MemoryMgr {
	private final int DATA_SEGMENT_START = 0x2000;
	private final int DATA_SEGMENT_END = 0x3fff;
	private List<MemoryCell> codeSegment;
	private List<MemoryCell> dataSegment;
	
	public static void main(String[] args) {
		new MemoryMgr().test();
	}
	
	
	private void test() {
		
	}
	
	
	public MemoryMgr() {
		codeSegment = new ArrayList<>();
		int codeSegmentCells = (DATA_SEGMENT_START - 1) / MemoryCell.BYTES;
		for (int i = 0; i < codeSegmentCells; i++) {
			codeSegment.add(new MemoryCell());
		}
		
		
		dataSegment = new ArrayList<>();
		int dataSegmentCells = (DATA_SEGMENT_END - DATA_SEGMENT_START) / MemoryCell.BYTES;
		for (int i = 0; i < dataSegmentCells; i++) {
			codeSegment.add(new MemoryCell());
		}
	}
	
	
	public byte get(int location) {
		List<MemoryCell> segment = dataSegment;
		if (location < DATA_SEGMENT_START) {
			segment = codeSegment;
		}
		MemoryCell cell = segment.get(location / MemoryCell.BYTES);
		return cell.getByte(location % MemoryCell.BYTES);
	}
	
	
	public void set(int location, byte value) {
		List<MemoryCell> segment = dataSegment;
		
		if (location < DATA_SEGMENT_START) {
			segment = codeSegment;
		}
		MemoryCell cell = segment.get(location / MemoryCell.BYTES);
		cell.setByte(value, location % MemoryCell.BYTES);
	}
}
