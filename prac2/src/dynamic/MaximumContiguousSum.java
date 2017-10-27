/* Find the contiguous subarray within an array (containing at least one number) which has the 
largest sum.
For example, given the array [-2,1,-3,4,-1,2,1,−5,4],
the contiguous subarray [4,−1,2,1] has the largest sum = 6.
*/

package dynamic;

public class MaximumContiguousSum {
	public static void main(String[] args) {
		maxSubArray3(new int[]{-2,1,-3,4,-1,2,1,-5,4});
		//maxSubArray2(new int[]{-100,-5,-12,-4,-100});
	}
	public static int maxSubArray(int[] A) {
	    int maxSumSoFar = A[0], runningSum = A[0];
	    for(int i=1;i<A.length;++i) {
	    	runningSum = Math.max(A[i], runningSum+A[i]);
	    	maxSumSoFar = Math.max(maxSumSoFar, runningSum);
	    }
	    return maxSumSoFar;
	}
	
	//use this
	public static int maxSubArray2(int[] arr) {
		int[] prev = new int[arr.length];
		int tail = 0, end = 0;
		int runningSum = arr[0], maxSum = arr[0];
		for(int i=1;i<arr.length;++i) {
			if(arr[i] > runningSum+arr[i])
				tail = i;
			runningSum = Math.max(arr[i], runningSum+arr[i]);
			if(runningSum > maxSum) {
				prev[i] = tail;
				end=i;
			} else
				prev[i] = -1;
			maxSum = Math.max(runningSum, maxSum);
		}
		System.out.println("Max sum "+maxSum);
		System.out.print("Max subarray is [");
		for(int i=prev[end];i<=end;++i)
			System.out.print(arr[i]+" ");
		System.out.print("]");
		return maxSum;
	}
	
	public static int maxSubArray3(int[] arr) {
		int start = 0, end = 0, finalStart=0;
		int runningSum = arr[0], maxSum = arr[0];
		for(int i=1;i<arr.length;++i) {
			if(arr[i] > runningSum+arr[i])
				start = i;
			runningSum = Math.max(arr[i], runningSum+arr[i]);
			if(runningSum > maxSum) {
				end=i;
				finalStart=start;
			}
			maxSum = Math.max(runningSum, maxSum);
		}
		System.out.println("Max sum "+maxSum);
		System.out.print("Max subarray is [");
		for(int i=finalStart;i<=end;++i)
			System.out.print(arr[i]+" ");
		System.out.print("]");
		return maxSum;
	}
}
