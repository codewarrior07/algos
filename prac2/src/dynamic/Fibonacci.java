package dynamic;


public class Fibonacci {
	public static void main(String[] args) {
		System.out.println(fibDynNoArray(7));
	}
	public static long fib(int num) {
		if(num==0)
			return 0;
		if(num==1)
			return 1;
		else 
			return fib(num-1) + fib(num-2);
	}
	public static long fibDyn(int num) {
		long[] arr = new long[num+1];
		arr[0] = 0;
		arr[1] = 1;
		for(int i=2;i<=num;++i) {
			arr[i] = arr[i-1] + arr[i-2];
		}
		return arr[num];
	}
	public static long fibDynNoArray(int num) {
		long a=0,b=1;
		for(int i=2;i<=num;++i){
			long temp=b;
			b += a;
			a = temp;
		}
		return b;
	}
}
