package main;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Complex c1 = new Complex(0, 1/Math.sqrt(2.0));
		Complex c2 = new Complex(1/Math.sqrt(2.0), 0);
		Vector s = new Vector(c1, c2);
		Vector m1 = new Vector(new Complex(0, 0), new Complex(1, 0));
		Vector m2 = new Vector(new Complex(0, 0), new Complex(1, 0));
		System.out.println("ASSIGNMENT:");
		System.out.println("|s>=(1/sqrt{2})(i,1)");
		System.out.println("|m^(1)> = (0,1); |m^(2)> = (0,1)");
		measurement(s, m1, m2);
		
		
		c1 = new Complex(1/Math.sqrt(2.0), 0);
		c2 = new Complex(0, 1/Math.sqrt(2.0));
		s = new Vector(c1, c2);
		m1 = new Vector(new Complex(Math.cos(Math.toDegrees(2)), 0), new Complex(Math.sin(Math.toDegrees(2)), 0));
		m2 = new Vector(new Complex(-Math.sin(Math.toDegrees(2)), 0), new Complex(Math.cos(Math.toDegrees(2)), 0));
		System.out.println("ASSIGNMENT:");
		System.out.println("|s>=(1/sqrt{2})(1,-i)");
		System.out.println("|m^(1)> = (cos 2,sin 2); |m^(2)> = (-sin 2,cos 2)");
		measurement(s, m1, m2);
		
		
		
		c1 = new Complex(1,0);
		c2 = new Complex(0,-1);
		s = new Vector(c1, c2);
		m1 = new Vector(new Complex(Math.cos(Math.toDegrees(2)), 0), new Complex(Math.sin(Math.toDegrees(2)), 0));
		m2 = new Vector(new Complex(-Math.sin(Math.toDegrees(2)), 0), new Complex(Math.cos(Math.toDegrees(2)), 0));
		System.out.println("ASSIGNMENT:");
		System.out.println("|s>=(1/sqrt{2})(1,-i)");
		System.out.println("|m^{1}> = (cos 2,sin 2); |m^(2)> = (-sin 2,cos 2)");
		measurement(s, m1, m2);
		
		
		c1 = new Complex(1/Math.sqrt(2.0), 1/Math.sqrt(2.0));
		c2 = new Complex(0,0);
		s = new Vector(c1, c2);
		m1 = new Vector(new Complex(1/Math.sqrt(2.0), 0), new Complex(0, 1/Math.sqrt(2.0)));
		m2 = new Vector(new Complex(1/Math.sqrt(2.0), 0), new Complex(0, -1/Math.sqrt(2.0)));
		System.out.println("ASSIGNMENT:");
		System.out.println("|s>=(1/sqrt{2})(1+i,0)");
		System.out.println("|m^{1}> = (1/sqrt{2})(1,i); |m^(2)> = (1/sqrt{2})(1,-i)");
		measurement(s, m1, m2);
		
		
		c1 = new Complex(1/Math.sqrt(2.0), 0);
		c2 = new Complex(0, 1/Math.sqrt(2.0));
		s = new Vector(c1, c2);
		m1 = new Vector(new Complex(1/Math.sqrt(2.0), 0), new Complex(0, 1/Math.sqrt(2.0)));
		m2 = new Vector(new Complex(1/Math.sqrt(2.0), 0), new Complex(0, -1/Math.sqrt(2.0)));
		System.out.println("ASSIGNMENT:");
		System.out.println("|s>=(1/sqrt{2})(1,i)");
		System.out.println("|m^{1}> = (1/sqrt{2})(1,i); |m^(2)> = (1/sqrt{2})(1,-i)");
		measurement(s, m1, m2);
		
		
		c1 = new Complex(Math.sqrt(3.0)/2.0, 0);
		c2 = new Complex(0, 1.0/2.0);
		s = new Vector(c1, c2);
		m1 = new Vector(new Complex(1/Math.sqrt(2.0), 0), new Complex(0, 1/Math.sqrt(2.0)));
		m2 = new Vector(new Complex(1/Math.sqrt(2.0), 0), new Complex(0, -1/Math.sqrt(2.0)));
		System.out.println("ASSIGNMENT:");
		System.out.println("|s>=(sqrt{3}/2,i/2)");
		System.out.println("|m^{1}> = (1/sqrt{2})(1,i); |m^(2)> = (1/sqrt{2})(1,-i)");
		measurement(s, m1, m2);
		
		
		c1 = new Complex(Math.sqrt(3.0)/2.0, 0);
		c2 = new Complex(0, 1.0/2.0);
		s = new Vector(c1, c2);
		m1 = new Vector(new Complex(Math.sqrt(3.0)/2.0, 0), new Complex(1.0/2.0, 0));
		m2 = new Vector(new Complex(-1.0/2.0, 0), new Complex(Math.sqrt(3.0)/2.0, 0));
		System.out.println("ASSIGNMENT:");
		System.out.println("|s>=(sqrt{3}/2,i/2)");
		System.out.println("|m^{1}> = (sqrt{3}/2,1/2); |m^(2)> = (-1/2,sqrt{3}/2)");
		measurement(s, m1, m2);
	}
	
	public static void measurement(Vector s, Vector m1, Vector m2)
	{
		System.out.println();
		System.out.println("RESULTS:");
		boolean isSUnitVector = s.isUnitVector();
		System.out.println("|s> is unit vector: "+isSUnitVector);
		boolean isOrthonormal = m1.isOrthonormal(m2);
		System.out.println("Are vectors |m^(1)>; |m^(2)> orthonormal: "+isOrthonormal);
			
		if(isOrthonormal && isSUnitVector)
		{
			System.out.println("Probability of |m^(1)> outcome: "+m1.probabilityOfOutcome(s));
			System.out.println("Probability of |m^(2)> outcome: "+m2.probabilityOfOutcome(s));
		}
		else
		{
			System.out.println("We do not calculate probabilities.");
		}
		System.out.println("-------------------------------------------------------------");
		System.out.println();
	}

}
