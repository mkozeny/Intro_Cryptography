package main;


public class Field {

	private int[] moduloPolynom={1,1,0,1,1,0,0,0,1};
	
	private int [] identity={1,0,0,0,0,0,0,0};
	
	public int[] addPolynoms(int [] p1, int [] p2)
	{
		int length = p1.length>p2.length?p1.length:p2.length;
		int [] result = new int[length];
		for(int i = 0; i < length; i++)
		{
			if(i <= p1.length-1 && i <= p2.length-1 && p1[i]!=p2[i])
				result[i]=1;
			else if(i > p1.length-1)
				result[i]=p2[i];
			else if(i > p2.length-1)
				result[i]=p1[i];
		}
		return result;
	}
	public int[] multiplyPolynoms(int [] p1, int [] p2)
	{
		int [] result = new int[theMostSignificantOccupiedPosition(p1)+theMostSignificantOccupiedPosition(p2)+2];
		int power=0;
		for(int i = 0; i < p1.length; i++)
		{
			for(int j = 0; j < p2.length ; j++)
			{
				if(p1[i]==1 && p2[j]==1)
				{
					power = i+j;
					if(result[power]==1)
						result[power]=0;
					else if(result[power]==0)
						result[power]=1;
				}
			}
		}
		DividingResult dividingResult = dividePolynoms(result, this.moduloPolynom);
		result = dividingResult.getRemainder();
		return result;
	}
	public DividingResult dividePolynoms(int [] p1, int [] p2)
	{
		int [] p =copyPolynom(p1);
		int [] result = new int[theMostSignificantOccupiedPosition(p1)+theMostSignificantOccupiedPosition(p2)+2];
		int [] helpPol = null;
		int power=0;
		int i=0;
		int j=0;
		
		i=theMostSignificantOccupiedPosition(p);
		j=theMostSignificantOccupiedPosition(p2);
		while(i>=j && (!isPolynomZero(p)))
		{
			helpPol = new int[theMostSignificantOccupiedPosition(p1)+theMostSignificantOccupiedPosition(p2)+2];
			power = i-j;
			result[power]=1;
			for(int k = 0; k < p2.length; k++)
			{
				if(p2[k]==1)
					helpPol[k+power]=1;
			}
			p=addPolynoms(p, helpPol);
			i=theMostSignificantOccupiedPosition(p);
			j=theMostSignificantOccupiedPosition(p2);
		}
		return new DividingResult(result, p);
	}
	public Equation doExtendedEucleid(int [] p1, int [] p2)
	{
		int [] k=copyPolynom(identity);
		int [] leftOperand;
		int [] l=copyPolynom(identity);
		int [] rightOperand;
		int [] remainder;
		DividingResult dividingResult;
		rightOperand=p1;
		leftOperand=p2;
		dividingResult = dividePolynoms(p1, p2);
		k=dividingResult.getResult();
		remainder=dividingResult.getRemainder();
		
		Equation eq1 = new Equation(k,leftOperand,l,rightOperand,remainder,leftOperand);
		if(isPolynomZero(eq1.getRemainder()))
			return eq1;
		
		dividingResult = dividePolynoms(eq1.getLeftOperand(), eq1.getRemainder());
		remainder=dividingResult.getRemainder();
		leftOperand=eq1.getLeftOperand();
		rightOperand=eq1.getRemainder();
		dividingResult = dividePolynoms(leftOperand, rightOperand);
		l=dividingResult.getResult();
		k=copyPolynom(identity);
		k=addPolynoms(k, multiplyPolynoms(l, eq1.getK()));
		l=multiplyPolynoms(l, eq1.getL());
		
		Equation eq2 = new Equation(k,leftOperand,l,eq1.getRightOperand(),remainder, eq1.getRemainder());
		if(isPolynomZero(eq2.getRemainder()))
			return eq1;
		return doEuclideanRecursively(eq1, eq2);
	}
	private Equation doEuclideanRecursively(Equation eq1, Equation eq2)
	{
		DividingResult dividingResult = dividePolynoms(eq2.getRightOperandPrev(), eq2.getRemainder());
		int [] remainder=dividingResult.getRemainder();
		int [] leftOperand=eq2.getRightOperandPrev();
		int [] rightOperand=eq2.getRemainder();
		dividingResult = dividePolynoms(leftOperand, rightOperand);
		int [] l=dividingResult.getResult();
		int [] k=copyPolynom(identity);
		k=addPolynoms(multiplyPolynoms(k, eq1.getK()), multiplyPolynoms(l, eq2.getK()));
		int [] identifyPol =copyPolynom(identity);
		l=addPolynoms(multiplyPolynoms(identifyPol, eq1.getL()), multiplyPolynoms(l, eq2.getL()));
		leftOperand=eq1.getLeftOperand();
		rightOperand=eq1.getRightOperand();
		
		Equation actual = new Equation(k, leftOperand, l, rightOperand, remainder, eq2.getRemainder());
		eq1=eq2;
		eq2=actual;
		if(!isPolynomZero(remainder))
			return doEuclideanRecursively(eq1, eq2);
		return eq1;
	}
	public static boolean isPolynomZero(int [] p)
	{
		for(int i = 0; i < p.length; i++)
		{
			  if(p[i]!=0)
				  return false;
		}
		return true;
	}
	public static boolean isOne(int [] p)
	{
		if(p.length>0 && p[0]==1)
		{
			for(int i = 1; i < p.length; i++)
			{
				if(p[i]!=0)
					return false;
			}
			return true;
		}
		return false;
	}
	public static int theMostSignificantOccupiedPosition(int[] p)
	{
		int position=0;
		for(int i = p.length-1; i >= 0; i--)
		{
			if(p[i]==1)
			{
			  position=i;
			  break;
			}
		}
		return position;
	}
	public static int[] copyPolynom(int[] p) {
		  int polynom[] = new int[p.length];          
		  for(int i = 0; i < p.length; i++)
			  polynom[i]=p[i];
		  return polynom;
	}
	public static void reverse(int[] p) {
		  int left  = 0;          
		  int right = p.length-1; 
		  
		  while (left < right) {
		     int temp = p[left]; 
		     p[left]  = p[right]; 
		     p[right] = temp;
		     left++;
		     right--;
		  }
	}
	public static int[] hexToBinary(String hex)
	{
		int i = Integer.parseInt(hex,16);
	    String binary = Integer.toBinaryString(i);
	    int [] polynom = new int[binary.length()];
	    for(int j = 0; j < polynom.length; j++)
	    {
	    	polynom[j]=Integer.parseInt(String.valueOf(binary.charAt(j)));
	    }
	    reverse(polynom);
	    return polynom;
	}
	public static String binaryToHex(int [] p)
	{
		String binary = "";
		reverse(p);
		for(int j = 0; j < p.length; j++)
	    {
	    	binary+=p[j];
	    }
		reverse(p);
	    int i= Integer.parseInt(binary,2);
	    return Integer.toHexString(i);
	}
	public int[] getModuloPolynom() {
		return moduloPolynom;
	}
	
	
}
