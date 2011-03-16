package main;

import gui.Window;

import javax.swing.UIManager;


public class Main {

	public static void main(String[] args) {
		
		/*String hex1="";
		String hex2="";
	    
		Field field = new Field();
		
		hex1="57";
		hex2="83";
		int [] result = field.addPolynoms(Field.hexToBinary(hex1), Field.hexToBinary(hex2));
		System.out.println("Result of adding p1: "+hex1+", and p2: "+hex2+", is: "+Field.binaryToHex(result));
		result = field.multiplyPolynoms(Field.hexToBinary(hex1), Field.hexToBinary(hex2));
		System.out.println("Result of multiplying p1: "+hex1+", and p2: "+hex2+", is: "+Field.binaryToHex(result));
		
		hex1="A1";
		hex2="D7";
		result = field.addPolynoms(Field.hexToBinary(hex1), Field.hexToBinary(hex2));
		System.out.println("Result of adding p1: "+hex1+", and p2: "+hex2+", is: "+Field.binaryToHex(result));
		result = field.multiplyPolynoms(Field.hexToBinary(hex1), Field.hexToBinary(hex2));
		System.out.println("Result of multiplying p1: "+hex1+", and p2: "+hex2+", is: "+Field.binaryToHex(result));
		
		
		
		hex1="B2";
		hex2="E5";
		result = field.addPolynoms(Field.hexToBinary(hex1), Field.hexToBinary(hex2));
		System.out.println("Result of adding p1: "+hex1+", and p2: "+hex2+", is: "+Field.binaryToHex(result));
		result = field.multiplyPolynoms(Field.hexToBinary(hex1), Field.hexToBinary(hex2));
		System.out.println("Result of multiplying p1: "+hex1+", and p2: "+hex2+", is: "+Field.binaryToHex(result));
		
		hex1="F1";
		hex2="10";
		result = field.addPolynoms(Field.hexToBinary(hex1), Field.hexToBinary(hex2));
		System.out.println("Result of adding p1: "+hex1+", and p2: "+hex2+", is: "+Field.binaryToHex(result));
		result = field.multiplyPolynoms(Field.hexToBinary(hex1), Field.hexToBinary(hex2));
		System.out.println("Result of multiplying p1: "+hex1+", and p2: "+hex2+", is: "+Field.binaryToHex(result));
		
		hex1="D7";
		hex2="12";
		result = field.addPolynoms(Field.hexToBinary(hex1), Field.hexToBinary(hex2));
		System.out.println("Result of adding p1: "+hex1+", and p2: "+hex2+", is: "+Field.binaryToHex(result));
		result = field.multiplyPolynoms(Field.hexToBinary(hex1), Field.hexToBinary(hex2));
		System.out.println("Result of multiplying p1: "+hex1+", and p2: "+hex2+", is: "+Field.binaryToHex(result));
		
		hex1="07";
		hex2="B9";
		result = field.addPolynoms(Field.hexToBinary(hex1), Field.hexToBinary(hex2));
		System.out.println("Result of adding p1: "+hex1+", and p2: "+hex2+", is: "+Field.binaryToHex(result));
		result = field.multiplyPolynoms(Field.hexToBinary(hex1), Field.hexToBinary(hex2));
		System.out.println("Result of multiplying p1: "+hex1+", and p2: "+hex2+", is: "+Field.binaryToHex(result));
		
		hex1="4B";
		hex2="C8";
		result = field.addPolynoms(Field.hexToBinary(hex1), Field.hexToBinary(hex2));
		System.out.println("Result of adding p1: "+hex1+", and p2: "+hex2+", is: "+Field.binaryToHex(result));
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
		
		hex1 = "F1";
		equation = field.doExtendedEucleid(field.getModuloPolynom(),Field.hexToBinary(hex1));
		inverse=Field.isPolynomZero(equation.getRemainder())?equation.getLeftOperand():equation.getK();
		System.out.println("Inverse of p1: "+hex1+", is: "+Field.binaryToHex(inverse));
		
		hex1 = "D7";
		equation = field.doExtendedEucleid(field.getModuloPolynom(),Field.hexToBinary(hex1));
		inverse=Field.isPolynomZero(equation.getRemainder())?equation.getLeftOperand():equation.getK();
		System.out.println("Inverse of p1: "+hex1+", is: "+Field.binaryToHex(inverse));
		
		hex1 = "07";
		equation = field.doExtendedEucleid(field.getModuloPolynom(),Field.hexToBinary(hex1));
		inverse=Field.isPolynomZero(equation.getRemainder())?equation.getLeftOperand():equation.getK();
		System.out.println("Inverse of p1: "+hex1+", is: "+Field.binaryToHex(inverse));
		
		hex1 = "4B";
		equation = field.doExtendedEucleid(field.getModuloPolynom(),Field.hexToBinary(hex1));
		inverse=Field.isPolynomZero(equation.getRemainder())?equation.getLeftOperand():equation.getK();
		System.out.println("Inverse of p1: "+hex1+", is: "+Field.binaryToHex(inverse));*/
		
		java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(
                                  "javax.swing.plaf.metal.MetalLookAndFeel");
                                //  "com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                                //UIManager.getCrossPlatformLookAndFeelClassName());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                new Window().setVisible(true);
            }
        });
	}
	
}
	