package eukleidian;

public class InverseFinder {

	   /* return array [d, a, b] such that d = gcd(p, q), ap + bq = d
	    * here are coeficients a and b counted when recursion comes back to beginning*/
	   public static int[] doExtendedEucleid(int p, int q) {
	      if (q == 0)
	         return new int[] { p, 1, 0 };

	      int[] values = doExtendedEucleid(q, p % q);
	      int d = values[0];
	      int a = values[2];
	      int b = values[1] - (p / q) * values[2];
	      return new int[] { d, a, b };
	   }
}
