package register;

public class Register {
	private final int BITS = 64;
	private byte[] value;
	
	
	Register() {
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
