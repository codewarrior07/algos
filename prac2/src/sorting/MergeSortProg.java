package sorting;

public class MergeSortProg {
	static int[] arr = {5,1,2,3,0,6,46,34};
	public static void main(String[] args) {
		//int[] arr = {5,1,2,3,0,6,46,34};
		mergeSort(new int[arr.length],0,arr.length-1);
		for(Integer i:arr)
			System.out.println(i);
	}
	
	public static void mergeSort(int[] temp, int start, int end) {
		if(start == end)
			return;
		int mid = (start+end)/2;
		mergeSort(temp,start,mid);
		mergeSort(temp,mid+1,end);
		merge(temp,start,mid+1,end);
	}
	
	public static void merge(int[] temp, int leftItr, int rightItr, int end) {
		int tempItr = 0;
		int mid = rightItr -1;
		int nItems = end-leftItr + 1;
		int lower = leftItr;
		while(leftItr <= mid && rightItr <= end) {
			if(arr[leftItr] < arr[rightItr])
				temp[tempItr++] = arr[leftItr++];
			else
				temp[tempItr++] = arr[rightItr++];
		}
		while(leftItr <= mid)
			temp[tempItr++] = arr[leftItr++];
		while(rightItr <= end)
			temp[tempItr++] = arr[rightItr++];
		for(tempItr=0;tempItr<nItems;++tempItr) 
			arr[lower+tempItr] = temp[tempItr];
	}
}

