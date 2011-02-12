package eukleidian;

public class MatrixOperator {

	private int n;
	
	
	
	public MatrixOperator(int n) {
		super();
		this.n = n;
	}
	/* multiply encrypted matrix with block of input string*/
	public int[] multiplyMatrixes(int[][] m1, int[] m2)
	{
		int m1r=m1.length;
		int m1s=m1[0].length;
		int m2s=m2.length;
		int [] d = new int[m1r];
		for (int i = 0; i < m1r; i++) {
			for (int j = 0; j < m2s; j++) {
				d[i] = 0;
				for (int k = 0; k < m1s; k++) {
					d[i] = (d[i] + m1[i][k] * m2[k]) % n;
				}
			}
		}
		return d;
	}

	public int[][] inverseMatrix(int [][] m)
	{
		if(m.length!=m[0].length)
		{
			System.err.println("Matrix has to be squared matrix");
			return null;
		}
		if(m.length<2)
		{
			System.err.println("Matrix has to has at least 2 rows and 2 columns");
			return null;
		}
		/*creating matrix with identity matrix, which has two more columns*/
		int[][] m1 = new int[m.length][2*m[0].length];
		int[] helpRow = new int [2*m[0].length];
		for(int i=0; i<m.length;i++)
		{
			for(int j=0; j< m[0].length; j++)
			{
				m1[i][j]=m[i][j];
				if(i==j)
					m1[i][j+m[0].length]=1;
			}
		}
		/*Gauss elimination under main diagonal*/
		for(int i=0; i<m1.length; i++)
		{
			int firstMul = m1[i][i];
			for(int j=i+1; j<m1.length; j++)
			{
				
				int secondMul = m1[j][i];
				for(int k=0; k<m1[0].length; k++)
				{
					m1[j][k] = (m1[j][k]*firstMul) % n;
					//m1[i][k] = (m1[i][k]*secondMul) % n;
					helpRow[k] = (m1[i][k]*secondMul) % n;
					//m1[j][k] = (m1[j][k]-m1[i][k]) % n;
					m1[j][k] = (m1[j][k]-helpRow[k]) % n;
				}
			}
		} 
		/*Gauss elimination over main diagonal*/
		for(int i=m1.length-2; i>=0; i--)
		{
			
			//int secondMul = m1[i][i];
			for(int j=m1.length-1; j>i; j--)
			{
				//System.out.println("i and j: "+i+", "+j);
				//int firstMul = m1[j][m1.length-1];
				int firstMul = m1[j][j];
				int secondMul = m1[i][j];
				for(int k=0; k<m1[0].length; k++)
				{
					m1[i][k] = (m1[i][k]*firstMul) % n;
					//m1[j][k] = (m1[j][k]*secondMul) % n;
					helpRow[k] = (m1[j][k]*secondMul) % n;
					//m1[i][k] = (m1[i][k]-m1[j][k]) % n;
					m1[i][k] = (m1[i][k]-helpRow[k]) % n;
				}
			}
		}
		/*if is some row multiplied by some constant, I try to find it and divide by this constant a row (because this constant can has gcd(constant,n)!=1)*/
		int gcd=1;
		for(int i = 0; i < m1.length; i++)
		{
			gcd = InverseFinder.doExtendedEucleid(m1[i][0],m1[i][1])[0];
			for(int j = 2; j < m1[0].length; j++)
			{
				//m1[i][j]=m1[i][j]<0?m1[i][j]+n:m1[i][j];
				gcd = InverseFinder.doExtendedEucleid(gcd,m1[i][j])[0];
			}
			if(gcd>1)
			{
				for(int j = 0; j < m1[0].length; j++)
				{
					m1[i][j]=m1[i][j]/gcd;
				}
			}
			gcd=1;
		}
		int [] values;
		/*finding inverses for elements on main diagonale*/
		for(int i=0; i<m1.length; i++)
		{
			if(m1[i][i]<0)
				m1[i][i]+=n;
			int p=m1[i][i]>n?m1[i][i]:n;
			int q=m1[i][i]<=n?m1[i][i]:n;
			values = InverseFinder.doExtendedEucleid(p, q);
			if(values[0]!=1)
				return null;
			int multiplicative;
			if(m1[i][i]>n)
				multiplicative=values[1];
			else
				multiplicative=values[2];
			
			for(int j=0; j<m1[0].length; j++)
			{
				m1[i][j]=(m1[i][j]*multiplicative) % n;
			}
		}
		int[][] m2 = new int[m.length][m[0].length];
		/*getting inversion matrix*/
		for(int i = 0; i < m.length; i++)
		{
			for(int j = m.length; j < 2*m.length; j++)
			{
				m2[i][j-m.length]=m1[i][j]<0?m1[i][j]+n:m1[i][j];
			}
		}
		return m2;
	}
	
	

	public int[][] concurentInverseMatrix(int [][] m)
	{
		if(m.length!=m[0].length)
		{
			System.err.println("Matrix has to be squared matrix");
			return null;
		}
		if(m.length<2)
		{
			System.err.println("Matrix has to has at least 2 rows and 2 columns");
			return null;
		}
		int[][] m1 = new int[m.length][2*m[0].length];
		int[] helpRow = new int [2*m[0].length];
		/*creating matrix with identity matrix, which has two more columns*/
		for(int i=0; i<m.length;i++)
		{
			for(int j=0; j< m[0].length; j++)
			{
				m1[i][j]=m[i][j];
				if(i==j)
					m1[i][j+m[0].length]=1;
			}
		}
		/*Gauss elimination under main diagonal*/
		for(int i=0; i<m1.length; i++)
		{
			int firstMul = m1[i][i];
			for(int j=i+1; j<m1.length; j++)
			{
				
				int secondMul = m1[j][i];
				for(int k=0; k<m1[0].length; k++)
				{
					m1[j][k] = (m1[j][k]*firstMul) % n;
					//m1[i][k] = (m1[i][k]*secondMul) % n;
					helpRow[k] = (m1[i][k]*secondMul) % n;
					//m1[j][k] = (m1[j][k]-m1[i][k]) % n;
					m1[j][k] = (m1[j][k]-helpRow[k]) % n;
				}
			}
		}
		/*Gauss elimination over main diagonal*/
		for(int i=m1.length-2; i>=0; i--)
		{
			
			//int secondMul = m1[i][i];
			for(int j=m1.length-1; j>i; j--)
			{
				//System.out.println("i and j: "+i+", "+j);
				//int firstMul = m1[j][m1.length-1];
				int firstMul = m1[j][j];
				int secondMul = m1[i][j];
				for(int k=0; k<m1[0].length; k++)
				{
					m1[i][k] = (m1[i][k]*firstMul) % n;
					//m1[j][k] = (m1[j][k]*secondMul) % n;
					helpRow[k] = (m1[j][k]*secondMul) % n;
					//m1[i][k] = (m1[i][k]-m1[j][k]) % n;
					m1[i][k] = (m1[i][k]-helpRow[k]) % n;
				}
			}
		}
		int gcd=1;
		for(int i = 0; i < m1.length; i++)
		{
			for(int j = 0; j < m1[0].length; j++)
			{
				m1[i][j]=m1[i][j]<0?m1[i][j]+n:m1[i][j];
			}
		}
		/*if is some row multiplied by some constant, I try to find it and divide by this constant a row (because this constant can has gcd(constant,n)!=1)*/
		for(int i = 0; i < m1.length; i++)
		{
			Equation eq = AnotherInverseFinder.findInverse(m1[i][0],m1[i][1]);
			gcd = eq.getRemainder()==0?eq.getLeftOperand():eq.getRemainder();
			for(int j = 2; j < m1[0].length; j++)
			{
				//m1[i][j]=m1[i][j]<0?m1[i][j]+n:m1[i][j];
				eq = AnotherInverseFinder.findInverse(gcd,m1[i][j]);
				gcd = eq.getRemainder()==0?eq.getLeftOperand():eq.getRemainder();
			}
			if(gcd>1)
			{
				for(int j = 0; j < m1[0].length; j++)
				{
					m1[i][j]=m1[i][j]/gcd;
				}
			}
			gcd=1;
		}
		//int [] values;
		/*finding inverses for elements on main diagonale*/
		for(int i=0; i<m1.length; i++)
		{
			if(m1[i][i]<0)
				m1[i][i]+=n;
			int p=m1[i][i]>n?m1[i][i]:n;
			int q=m1[i][i]<=n?m1[i][i]:n;
			Equation eq = AnotherInverseFinder.findInverse(p,q);
			//values = InverseFinder.doExtendedEucleid(p, q);
			if(eq.getRemainder()!=1 && eq.getRemainder()!=0)
				return null;
			int multiplicative;
			multiplicative=eq.getRemainder()==0?eq.getLeftOperand():eq.getK();
			
			
			for(int j=0; j<m1[0].length; j++)
			{
				m1[i][j]=(m1[i][j]*multiplicative) % n;
			}
		}
		int[][] m2 = new int[m.length][m[0].length];
		/*getting inversion matrix*/
		for(int i = 0; i < m.length; i++)
		{
			for(int j = m.length; j < 2*m.length; j++)
			{
				m2[i][j-m.length]=m1[i][j]<0?m1[i][j]+n:m1[i][j];
			}
		}
		return m2;
	}
	
	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}
	
	
}
