package util;

import java.util.ArrayList;
import java.util.List;

public class MipsExceptionList extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5656824845378004741L;
	private ArrayList<MipsException> exceptions = new ArrayList<>();
	
	public void add(MipsException exception) {
		exceptions.add(exception);
	}
	
	public List<MipsException> getExceptionList() {
		return exceptions;
	}
	
	public boolean isEmpty() {
		return exceptions.isEmpty();
	}
}
