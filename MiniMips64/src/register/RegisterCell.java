package register;

public class RegisterCell {
	private final int BITS = 64;
	private byte[] value;
	
	
	RegisterCell() {
		value = new byte[BITS];
	}
	
	
	void clear() {
		value = new byte[BITS];
	}
	
	
	byte[] getValue() {
		return value;
	}
	
	
	void setValue(byte[] value) {
		this.value = value;
	}
}
