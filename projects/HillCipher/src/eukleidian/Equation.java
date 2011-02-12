package eukleidian;

public class Equation {

	private Integer k;
	
	private Integer leftOperand;
	
	private Integer l;
	
	private Integer rightOperand;
	
	private Integer remainder;
	
	private Integer rightOperand1;
	
	
	
	

	
	

	public Equation(Integer k, Integer leftOperand, Integer l,
			Integer rightOperand, Integer remainder, Integer rightOperand1) {
		super();
		this.k = k;
		this.leftOperand = leftOperand;
		this.l = l;
		this.rightOperand = rightOperand;
		this.remainder = remainder;
		this.rightOperand1 = rightOperand1;
	}

	public Integer getK() {
		return k;
	}

	public void setK(Integer k) {
		this.k = k;
	}

	public Integer getLeftOperand() {
		return leftOperand;
	}

	public void setLeftOperand(Integer leftOperand) {
		this.leftOperand = leftOperand;
	}
	
	

	public Integer getL() {
		return l;
	}

	public void setL(Integer l) {
		this.l = l;
	}

	public Integer getRightOperand() {
		return rightOperand;
	}

	public void setRightOperand(Integer rightOperand) {
		this.rightOperand = rightOperand;
	}

	public Integer getRemainder() {
		return remainder;
	}

	public void setRemainder(Integer remainder) {
		this.remainder = remainder;
	}

	
	



	public Integer getRightOperand1() {
		return rightOperand1;
	}

	public void setRightOperand1(Integer rightOperand1) {
		this.rightOperand1 = rightOperand1;
	}

	
	
	
}
