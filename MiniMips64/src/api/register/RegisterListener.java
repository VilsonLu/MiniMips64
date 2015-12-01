package api.register;

public interface RegisterListener {
	public abstract void rChanged(int index, long value);
	public abstract void fChanged(int index, long value);
	public abstract void hiChanged(long value);
	public abstract void loChanged(long value);
}
