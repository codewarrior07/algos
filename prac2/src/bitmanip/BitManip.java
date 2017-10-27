package bitmanip;

public class BitManip {
	public static void main(String[] args) {
		/*int c1 =2, p=6;
		System.out.println(1<<3);
		System.out.println((1<<(p-1))|(c1+1)<<(p-c1-1));*/
		System.out.println(getPrev(13948));
	}
	/*
	Given a positive integer, print the next smallest and the next largest number that
	have the same number of 7 bits in their binary representation
	 */
	public static int getNext(int n){
		int c = n;
		int c0=0,c1=0;
		// count zeroes
		while((c&1)==0 && c > 0){
			c0++;
			c = c>>1;
		}
		//count ones
		while((c&1)==1){
			c1++;
			c =c >>1;
		}
		if(c0 + c1 > 31 || c0+c1 ==0)
			return -1;
		// find bit to be flipped
		int p = c0+c1;
		// flip pth bit
		n = n | (1<<p);
		// mask all bits to right of pth bit
		n = n & ~((1<<p)-1);
		// fill c1-1 ones to right of pth bit
		n = n | (1<<(c1-1))-1;
		return n;
	}
	public static int getPrev(int n){
		int c = n;
		int c1=0,c0=0;
		//
		while((c&1)==1) {
			c1++;
			c = c>>1;
		}
		while((c&1)==0 && c>0){
			c0++;
			c = c>>1;
		}
		int p = c0+c1;
		n = n & ((~0)<<(p+1));
		int mask = (1<<(c1+1))-1;
		n =  n | mask << (c0-1);
		return n;
			
	}

}
