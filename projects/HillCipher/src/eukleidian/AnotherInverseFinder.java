package eukleidian;


public class AnotherInverseFinder {
	
	
	
	
	/*this function returns object Equation which is: remainder = k*leftOperand + l*rightOperand
	 * as left operand is there used a smaller number*/
	public static Equation findInverse(int number, int n)
	{
		if(number<0)
			number+=n;
		
		int k=1;
		int leftOperand=0;
		int l=1;
		int rightOperand=0;
		int remainder=0;
		if(number==0 || n==0)
			return new Equation(0, number==0?number:n, 0, number==0?n:number, number==0?n:number, 0);
		/*firstly you have to decide which operand will be left and which will be right*/
		if(number>n)
		{
			rightOperand=number;
			leftOperand=n;
			k=-number/n;
			remainder=number % n;
		}
		else
		{
			rightOperand=n;
			leftOperand=number;
			k=-n/number;
			remainder=n % number;
		}
		
		
		Equation eq1 = new Equation(k,leftOperand,l,rightOperand,remainder,leftOperand);
		/*if remainder of first equation is zero, you have to return this equation and GCD is then leftOperand*/
		if(eq1.getRemainder()==0)
			return eq1;
		
		remainder=eq1.getLeftOperand() % eq1.getRemainder();
		leftOperand=eq1.getLeftOperand();
		rightOperand=eq1.getRemainder();
		l=-leftOperand/rightOperand;
		k=1;
		
		k+=l*eq1.getK();
		l*=eq1.getL();
		
		
		Equation eq2 = new Equation(k,leftOperand,l,eq1.getRightOperand(),remainder, eq1.getRemainder());
		/*else if remainder of second equation is zero, you have to return first equation and GCD is then remainder*/
		if(eq2.getRemainder()==0)
			return eq1;
		
		
		return doEuclideanRecursively(eq1, eq2);
	}
	/*to count next recursion step, you have to remember from previous equation remainder and leftOperand (which is here described as rightOperand1)
	 * and firstly is equation count in classic pattern k*leftOp = l*rightOp * remainder 
	 * then is equation count in pattern remainder = k*p + l*q
	 * and then recursion repeat until remainder is zero
	 * this recursion is tail recursion*/
	public static Equation doEuclideanRecursively(Equation eq1, Equation eq2)
	{
		
		int remainder=eq2.getRightOperand1() % eq2.getRemainder();
		int leftOperand=eq2.getRightOperand1();
		int rightOperand=eq2.getRemainder();
		int l=-leftOperand/rightOperand;
		int k=1;
		k=k*eq1.getK()+l*eq2.getK();
		l=1*eq1.getL()+l*eq2.getL();
		leftOperand=eq1.getLeftOperand();
		rightOperand=eq1.getRightOperand();
		
		Equation actual = new Equation(k, leftOperand, l, rightOperand, remainder, eq2.getRemainder());
		eq1=eq2;
		eq2=actual;
		if(remainder!=0)
			return doEuclideanRecursively(eq1, eq2);
		return eq1;
	}

}
