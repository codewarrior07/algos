package dynamic;

public class SumOfDigits {
	public static void main(String[] args){
		System.out.println(calcSum(328));
	}
	public static int calcSum(int num){
		if(num<0)
			return -1;
		if(num<10)
			return (num*(num+1))/2;
		int d = 0,temp=num;
		while(temp>0){
			temp = temp/10;
			++d;
		}
		int[] arr = new int[d];
		arr[0] = 0;
		arr[1] = 45;
		for(int i=2;i<d;++i)
			arr[i] = (int) (arr[i-1]*10 + 45*(Math.pow(10,i-1)));
		int p = (int)(Math.pow(10,d-1));
		int msd = num/p;
		return msd*arr[d-1] + (msd*(msd-1)/2)*p + msd*(1+(num%p)) + calcSum(num%p);
	}
}
