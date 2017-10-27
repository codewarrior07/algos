import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//given an array of integers, find three that sum to a given value. 
public class ThreeSum {
	public static void main(String[] args){
		ThreeSum t = new ThreeSum();
		System.out.println(t.threeSumNumList(new int[]{0,0,0},0));
		/*for(Integer i:t.threeSumNum(new int[]{-12,3,4,1,6,9},0))
			System.out.println(i);*/
	}
	
	// sum = given number
	public int[] threeSumNum(int[] arr,int val){
		if(arr==null || arr.length==0)
			return null;
		Arrays.sort(arr); // code and use quick sort
		// Fix the first element as A[i] where i is from 0 to array size – 2
		// find the other two elements, start two index variables
        // from two corners of the array and move them toward each other
		for(int i=0;i<arr.length-2;++i){
			int l=i+1;
			int r=arr.length-1;
			while(l<r){
				if(arr[i]+arr[l]+arr[r] == val)
					return new int[]{arr[i],arr[l],arr[r]};
				else if(arr[i]+arr[l]+arr[r] < val)
					++l;
				else
					--r;
			}
		}
		return new int[0];
	}
	
	//return all possible combinations
	public Set<List<Integer>> threeSumNumList(int[] arr,int val){
		Set<List<Integer>> list = new HashSet<List<Integer>>();
		if(arr==null || arr.length==0)
			return null;
		Arrays.sort(arr); // code and use quick sort
		// Fix the first element as A[i] where i is from 0 to array size – 2
		// find the other two elements, start two index variables
        // from two corners of the array and move them toward each other
		for(int i=0;i<arr.length-2;++i){
			int l=i+1;
			int r=arr.length-1;
			while(l<r){
				if(arr[i]+arr[l]+arr[r] == val){
					list.add(new ArrayList<Integer>(Arrays.asList(arr[i],arr[l],arr[r])));
					++l;
				}
				else if(arr[i]+arr[l]+arr[r] < val)
					++l;
				else
					--r;
			}
		}
		return list;
	}
}
