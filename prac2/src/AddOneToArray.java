import java.util.Arrays;


//Given an array a contains all digits 0-9 a = [1, 4, 2, 1] # which represents 1421 
//Add one to the number and return the array return a = [1, 4, 2, 2] # which represents 1422  
public class AddOneToArray {
	public static void main(String[] args){
		AddOneToArray a = new AddOneToArray();
		for(Integer i:a.addOne(new int[]{9,9,9}))
			System.out.println(i);
	}

	public int[] addOne(int[] arr){
		if(arr==null)
			return null;
		if(arr.length==0)
			return new int[]{1};
		int carry=1;
		int[] sum = new int[arr.length];
		for(int i=arr.length-1;i>=0;--i) {
			int temp = arr[i]+carry;
			sum[i] = temp%10;
			carry = temp/10;
		}
		if(carry>0) {
			sum = new int[arr.length+1];
			sum[0] = 1;
		}
		return sum;
	}

}
