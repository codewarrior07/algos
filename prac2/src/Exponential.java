public class Exponential {
	public static void main(String[] args) {
		//System.out.println(power(2.5,0));
		//System.out.println(powerLogn(0,3));
		
		Exponential e = new Exponential();
		System.out.println(e.powBitItr(6.0,5));
	}
	
	public static double pow(double num, int pow) {
		if(pow == 0)
			return 1;
		if(pow == 1)
			return num;
		return num * pow(num,--pow);
	}
	
	public static double power(double num, int pow) {
		if(pow == 0)
			return 1;
		double res = 1.0;
		while(pow >= 1) {
			res *= num;
			--pow;
		}
		return res;
	}
	
	public static double powerLogn(double n,int pow) {
		if(pow == 0)
			return 1;
		if(pow == 1)
			return n;
		if(pow < 0) 
			return 1/powerLogn(n,Math.abs(pow));
		if(pow%2 == 0)
			return powerLogn(n*n,(pow/2));
		else
			return n*powerLogn(n*n,((pow-1)/2));
	}
	
	// bitwise recursive
	public double powBitRec(double num, int exp) {
	    if(exp == 0)
	        return 1;
	    int e = Math.abs(exp);
	    double half = powBitRec(num,(e>>1));
	    double res = half*half;
	    if((e& 1) == 1)
	        res *= num;
	    return (exp>0)?res:1.0/res;
	}
	
	// OPTIMAL, USE THIS
	//bitwise itr
	public double powBitItr(double num, int exp) {
	    if(exp == 0)
	        return 1;
	    double res = 1;
	    for(int e=Math.abs(exp);e>0;num*=num,e>>=1) {
	        if( (e&1) == 1)
	            res *= num;
	    }
	    return (exp>0)?res:1.0/res;
	}
}
