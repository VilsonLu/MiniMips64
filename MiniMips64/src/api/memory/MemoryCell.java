package api.memory;

public class MemoryCell {
	public static final int BYTES = 4;
	private byte[] values;
	
	
	MemoryCell() {
		this.clear();
	}
	
	
	void clear() {
		values = new byte[BYTES];
	}
	
	
	byte getByte(int index) {
		return values[index];
	}
	
	
	void setByte(byte value, int index) {
		values[index] = value;
	}
	
	byte[] getValues() {
		return values;
	}
	
	
	void setValue(byte[] values) {
		this.values = values;
	}
}
