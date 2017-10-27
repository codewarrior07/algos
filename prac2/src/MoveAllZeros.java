//Given an array of random numbers, Push all the zero’s of a given array to the end (or) beginning of the array. 
//For example, if the given arrays is {1, 9, 8, 4, 0, 0, 2, 7, 0, 6, 0}, it should be changed to {1, 9, 8, 4, 2, 7, 6, 0, 0, 0, 0}. 
//The order of all other elements should be same. Expected time complexity is O(n) and extra space is O(1).
public class MoveAllZeros {
	public static void main(String[] args){
		MoveAllZeros m = new MoveAllZeros();
		for(Integer i:m.moveToFront(new int[]{4,1,2,0,1,0,0,0,4}))
			System.out.println(i);
	}
	
	//move to end
	public int[] moveToEnd(int[] arr){
		int i=0;
		for(int j=0;j<arr.length;++j){
			if(arr[j]!=0)
				arr[i++]=arr[j];
		}
		for(int j=i;j<arr.length;++j)
			arr[j]=0;
		return arr;
	}
	
	//move to front
	public int[] moveToFront(int[] arr){
		int i=arr.length-1;
		for(int j=arr.length-1;j>=0;--j){
			if(arr[j] !=0)
				arr[i--] = arr[j];
		}
		for(int j=i;j>=0;--j)
			arr[j]=0;
		return arr;
	}
}
