package sorting;

public class CountingSort {
	public static void main(String[] args){
		int[] arr = new int[10];
		for(int i=0;i<arr.length;++i){
			arr[i] = (int)(Math.random()*100) + 1;
			System.out.println(arr[i]);
		}
		//arr = new int[]{4,1,2,1,3,4,2};
		arr = countingSortInPlace(arr);
		System.out.println("--------");
		for(Integer i:arr)
			System.out.println(i);
	}
	public static int[] countingSort(int[] arr) {
		//determine range
		int max = arr[0];
		for(Integer i:arr) {
			if(max<i)
				max = i;
		}
		int[] histogram = new int[max+1];
		for(Integer i:arr)
			histogram[i]++;
		for(int i=1;i<histogram.length;++i)
			histogram[i] += histogram[i-1];
		int out[] = new int[arr.length];
		for(int i=arr.length-1;i>=0;i--) {
			out[histogram[arr[i]]-1] = arr[i];
			histogram[arr[i]]--;
		}
		return out;
	}
	
	public static int[] countingSortInPlace(int[] arr){
		//determine range
		int max = arr[0];
		for(Integer i:arr) {
			if(max<i)
				max = i;
		}
		int[] histogram = new int[max+1];
		for(Integer i:arr)
			histogram[i] = ++histogram[i];
		int i=0,j=0;
		while(i<arr.length){
			int count = histogram[j];
			while(count>0){
				arr[i++] = j;
				--count;
			}
			++j;
		}
		return arr;
	}
}
