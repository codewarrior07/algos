
public class SortedRotatedArray {

	public static void main(String[] args) {
		SortedRotatedArray o = new SortedRotatedArray();
		//System.out.println(o.findMinHasDups(new int[]{5,5,6,6,7,7,0,0,1,1,2}));
		//System.out.println(o.findInSortedRotated(new int[]{4,5,6,7,0,1,2},3));
		System.out.println(o.findInSortedRotated(new int[]{12,13,6,7,8,9,10},12));
	}
	
	// A sorted array is rotated at some unknown point, find the minimum element in it.
	// Following solution assumes that all elements are distinct.
	public int findMin(int[] arr){
		if(arr==null || arr.length==0)
			return Integer.MIN_VALUE;
		return findMinHelper(arr,0,arr.length-1);
	}
	public int findMinHelper(int[] arr,int s,int e){
		while(arr[s]>arr[e]){
			int mid = (s+e)/2;
			if(arr[mid]>arr[e])
				s = mid+1;
			else
				e=mid;
		}
		return arr[s];
	}
	
	// A sorted array is rotated at some unknown point, find the minimum element in it.
	// Following solution assumes that  elements have dups.
	public int findMinHasDups(int[] arr){
		if(arr==null || arr.length==0)
			return Integer.MIN_VALUE;
		return findMinHasDupsHelper(arr,0,arr.length-1);
	}
	public int findMinHasDupsHelper(int[] arr,int s,int e){
		while(arr[s]==arr[e] && e>0)
			--e;
		while(s<e){
			int mid=(s+e)/2;
			if(mid==s)
				break;
			if(arr[mid]>arr[e])
				s=mid+1;
			else
				e=mid;
		}
		return Math.min(arr[s],arr[e]);
	}
	
	//find element in sorted rotated array with no dups
	public int findInSortedRotated(int[] arr,int val){
		if(arr==null)
			return -1;
		int s=0,e=arr.length-1;
		while(s<=e){
			int mid=(s+e)/2;
			if(arr[mid]==val)
				return mid;
			if(arr[s]<=arr[mid]){
				if(arr[s]<=val && val<arr[mid])
					e= mid-1;
				else 
					s=mid+1;
			}
			else {
				if(arr[mid]<val && val<=arr[e])
					s=mid+1;
				else
					e=mid-1;
			}
		}
		return -1;
	}
}
