package register;

public class RegisterCell {
	private final int BITS = 64;
	private long value;
	
	
	RegisterCell() {
	}
	
	
	void clear() {
		value = 0;
	}
	
	
	long getValue() {
		return value;
	}
	
	
	void setValue(long value) {
		this.value = value;
	}
}
