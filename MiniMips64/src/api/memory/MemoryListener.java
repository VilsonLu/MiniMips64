package api.memory;

public interface MemoryListener {
	public abstract void memChanged(int index, long value);
}
