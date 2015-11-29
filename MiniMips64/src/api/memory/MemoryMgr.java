package api.memory;

import java.util.ArrayList;
import java.util.List;

public class MemoryMgr {
	private static MemoryMgr instance = new MemoryMgr();
	
	
	public static MemoryMgr getInstance() {
		return instance;
	}
	
	
	private final long DATA_SEGMENT_START = 0x2000;
	private final long DATA_SEGMENT_END = 0x4000;
	private List<MemoryCell> codeSegment;
	private List<MemoryCell> dataSegment;
	
	public static void main(String[] args) {
		new MemoryMgr().test();
	}
	
	
	public long getDataSegmentStart() {
		return DATA_SEGMENT_START;
	}
	
	
	public int totalDataSegments() {
		return (int) (DATA_SEGMENT_END - DATA_SEGMENT_START) / MemoryCell.BYTES;
	}
	
	
	private void test() {
		
	}
	
	
	public MemoryMgr() {
		codeSegment = new ArrayList<>();
		long codeSegmentCells = (DATA_SEGMENT_START - 1) / MemoryCell.BYTES;
		for (int i = 0; i < codeSegmentCells; i++) {
			codeSegment.add(new MemoryCell());
		}
		
		
		dataSegment = new ArrayList<>();
		long dataSegmentCells = this.totalDataSegments();
		for (int i = 0; i < dataSegmentCells; i++) {
			codeSegment.add(new MemoryCell());
		}
	}
	
	
	public byte get(long location) {
		List<MemoryCell> segment = dataSegment;
		if (location < DATA_SEGMENT_START) {
			segment = codeSegment;
		}
		MemoryCell cell = segment.get((int) location / MemoryCell.BYTES);
		return cell.getByte((int) location % MemoryCell.BYTES);
	}
	
	
	public void set(long location, byte value) {
		List<MemoryCell> segment = dataSegment;
		
		if (location < DATA_SEGMENT_START) {
			segment = codeSegment;
		}
		
		MemoryCell cell = segment.get((int) location / MemoryCell.BYTES);
		cell.setByte(value, (int) location % MemoryCell.BYTES);
	}
}
