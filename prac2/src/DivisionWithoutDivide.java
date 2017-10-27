// Given two unsigned integer values, write a function that returns the first divided by the second. You cannot (of course) use div or mod operators

public class DivisionWithoutDivide {
	public static void main(String[] args){
		DivisionWithoutDivide d = new DivisionWithoutDivide();
		System.out.println(d.divide(-22,-7));
	}
	
	public int divide(int dividend,int divisor){
		if(divisor==0)
			return Integer.MAX_VALUE;
		if(divisor==-1 && dividend == Integer.MIN_VALUE)
	        return Integer.MAX_VALUE;
		
		int pDividend = Math.abs(dividend);
		int pDivisor = Math.abs(divisor);
		
		int result=0;
		while(pDividend>=pDivisor){
			int numShift=0;
			while(pDividend>=(pDivisor<<numShift))
				++numShift;
			
			result += 1<<(numShift-1);
			pDividend -= (pDivisor<<(numShift-1));
		}
		
		if((dividend>0 && divisor>0) || (dividend<0 && divisor<0))
			return result;
		else
			return -result;
	}
}
