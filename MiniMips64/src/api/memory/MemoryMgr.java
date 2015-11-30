package api.memory;

import java.util.ArrayList;
import java.util.List;

public class MemoryMgr {
	private static MemoryMgr instance = new MemoryMgr();
	private MemoryListener listener;
	
	public static MemoryMgr getInstance() {
		return instance;
	}
	
	private final long DATA_SEGMENT_START = 0x2000;
	private final long DATA_SEGMENT_END = 0x4000;
	private List<MemoryCell> codeSegment;
	private List<MemoryCell> dataSegment;
	
	
	public long getDataSegmentStart() {
		return DATA_SEGMENT_START;
	}
	
	
	public void setListener(MemoryListener listener) {
		this.listener = listener;
	}
	
	public int totalDataSegments() {
		return (int) (DATA_SEGMENT_END - DATA_SEGMENT_START) / MemoryCell.BYTES;
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
			dataSegment.add(new MemoryCell());
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
		List<MemoryCell> segment = codeSegment;
		
		if (location >= DATA_SEGMENT_START) {
			segment = dataSegment;
			location -= DATA_SEGMENT_START;
		}
		
		int index = (int) location / MemoryCell.BYTES;
		MemoryCell cell = segment.get(index);
		cell.setByte(value, (int) location % MemoryCell.BYTES);
		
		System.out.println(index);
		
		if (listener != null) {
			listener.memChanged(index, cell.getLong());
		}
	}
}
