package main;

public class DividingResult {

	private int[] result;
	
	private int[] remainder;

	public DividingResult(int[] result, int[] remainder) {
		super();
		this.result = result;
		this.remainder = remainder;
	}

	public int[] getResult() {
		return result;
	}

	public void setResult(int[] result) {
		this.result = result;
	}

	public int[] getRemainder() {
		return remainder;
	}

	public void setRemainder(int[] remainder) {
		this.remainder = remainder;
	}
	
	
}
