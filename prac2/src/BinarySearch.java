public class BinarySearch {
	public static void main(String[] args){
		int[] arr = new int[]{-10,-3,4,5,8,9};
		System.out.println(binSearchRec(arr,0,arr.length-1,-3));
	}
	public static int binSearch(int[] arr,int val){
		int s = 0, e = arr.length-1;
		int mid = (s+e)/2;
		while(s<=e && arr[mid] != val){
			if(val < arr[mid])
				e = mid-1;
			else
				s = mid+1;
			mid = (s+e)/2;
		}
		if(s<=e)
			return mid;
		return -1;
	}
	public static int binSearchRec(int[] arr,int s,int e,int val){
		if(s>e)
			return -1;
		int mid = (s+e)/2;
		if(arr[mid] == val)
			return mid;
		if(val < arr[mid])
			e = mid-1;
		else
			s = mid+1;
		return binSearchRec(arr,s,e,val);
	}
}
