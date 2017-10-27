import java.util.HashMap;
import java.util.Map;


public class TwoSumProb {
	public static void main(String[] args){
		
	}
	
	public int[] twoSum(int[] arr,int sum){
		Map<Integer, Integer> valMap = new HashMap<Integer, Integer>();
		int[] out = new int[2];
		for(int i=0;i<arr.length;i++)
			valMap.put(arr[i], i);
		
		for(int i=0;i<arr.length;i++) {
			if(valMap.containsKey(sum - arr[i]) && valMap.get(sum - arr[i]) != i) {
					out[0] = sum-arr[i];
					out[1] = arr[i];
			}
		}
		return out;
	}
}
