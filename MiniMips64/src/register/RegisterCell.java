package register;

public class RegisterCell {
	private final int BITS = 64;
<<<<<<< HEAD
	private byte[] value;
	
	
	RegisterCell() {
		value = new byte[BITS];
=======
	private long value;
	
	
	RegisterCell() {
>>>>>>> pipeline
	}
	
	
	void clear() {
<<<<<<< HEAD
		value = new byte[BITS];
	}
	
	
	byte[] getValue() {
=======
		value = 0;
	}
	
	
	long getValue() {
>>>>>>> pipeline
		return value;
	}
	
	
<<<<<<< HEAD
	void setValue(byte[] value) {
=======
	void setValue(long value) {
>>>>>>> pipeline
		this.value = value;
	}
}
