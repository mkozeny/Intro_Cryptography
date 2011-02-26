package main;


public class Main {

	public static void main(String[] args) {
		
		String hex1="";
		String hex2="";
	    
		Field field = new Field();
		
		hex1="57";
		hex2="83";
		int [] result = field.addPolynoms(Field.hexToBinary(hex1), Field.hexToBinary(hex2));
		System.out.println("Result of adding p1: "+hex1+", and p2: "+hex2+", is: "+Field.binaryToHex(result));
		result = field.multiplyPolynoms(Field.hexToBinary(hex1), Field.hexToBinary(hex2));
		System.out.println("Result of multiplying p1: "+hex1+", and p2: "+hex2+", is: "+Field.binaryToHex(result));
		
		hex1="4B";
		hex2="C8";
		result = field.multiplyPolynoms(Field.hexToBinary(hex1), Field.hexToBinary(hex2));
		System.out.println("Result of multiplying p1: "+hex1+", and p2: "+hex2+", is: "+Field.binaryToHex(result));

		hex1="0B";
		hex2="05";
		DividingResult dividingResult = field.dividePolynoms(Field.hexToBinary(hex1), Field.hexToBinary(hex2));
		System.out.println("Result of dividing p1: "+hex1+", with p2: "+hex2+", is: "+Field.binaryToHex(dividingResult.getResult())
				+", remainder: "+Field.binaryToHex(dividingResult.getRemainder()));
		
		hex1 = "7E";
		Equation equation = field.doExtendedEucleid(field.getModuloPolynom(),Field.hexToBinary(hex1));
		int [] inverse=Field.isPolynomZero(equation.getRemainder())?equation.getLeftOperand():equation.getK();
		System.out.println("Inverse of p1: "+hex1+", is: "+Field.binaryToHex(inverse));
		
		hex1 = "B2";
		equation = field.doExtendedEucleid(field.getModuloPolynom(),Field.hexToBinary(hex1));
		inverse=Field.isPolynomZero(equation.getRemainder())?equation.getLeftOperand():equation.getK();
		System.out.println("Inverse of p1: "+hex1+", is: "+Field.binaryToHex(inverse));
		
		hex1 = "C6";
		equation = field.doExtendedEucleid(field.getModuloPolynom(),Field.hexToBinary(hex1));
		inverse=Field.isPolynomZero(equation.getRemainder())?equation.getLeftOperand():equation.getK();
		System.out.println("Inverse of p1: "+hex1+", is: "+Field.binaryToHex(inverse));
	}
	
}
	