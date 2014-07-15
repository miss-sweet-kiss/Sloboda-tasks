import java.math.BigInteger;
/*Find the sum of the digits in the number 100! (i.e. 100 factorial)*/

public class Factorial {
	
	//counting factorial
	public static BigInteger factorial(int n) {
        BigInteger ret = BigInteger.ONE;
        for (int i = 1; i <= n; ++i) 
        	ret = ret.multiply(BigInteger.valueOf(i));
        return ret;
    }
	
	public static void main(String[] args) {
		BigInteger fact = factorial(100);
		int sum = 0;
		/*System.out.println((fact + "").length());*/
		for(int i = 0;i < 158;i++) { //158 - the number of digits in factorial
			sum += fact.remainder(BigInteger.valueOf(10)).intValue(); //last digit - the reminder of the division by 10
			fact = fact.divide(BigInteger.valueOf(10)); 
		}
		System.out.println(sum);
		
	}
}
