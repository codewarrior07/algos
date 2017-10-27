import java.util.LinkedList;

/*
 A long array A[] is given to you. There is a sliding window of size w which is moving from the very left of the array to the very right. 
 You can only see the w numbers in the window. Each time the sliding window moves rightwards by one position. Following is an example:
The array is [1 3 -1 -3 5 3 6 7], and w is 3.

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Input: A long array A[], and a window width w
Output: An array B[], B[i] is the maximum value of from A[i] to A[i+w-1]
Requirement: Find a good optimal way to get B[i] in O(n)
 */

public class SlidingWindow {
	public static void main(String[] args) {
		SlidingWindow s = new SlidingWindow();
		/*for(int i:s.getMaximals(new int[]{1,3,-1,-3,5,3,6,7},3))
			System.out.println(i);*/
		int[] arr = new int[]{1,3,-1,-3,5,3,6,7};
		for(int i:arr)
			System.out.println(s.getMaximalAtEveryPoint(i));
	}
	
	// sliding window
	public int[] getMaximals(int[] arr,int k){
		int w = Math.min(arr.length,k);
		int[] out = new int[arr.length-w+1];
		LinkedList<Integer> deque = new LinkedList<Integer>();
		for(int i=0;i<w;++i){
			while(!deque.isEmpty() && arr[i]> arr[deque.peekLast()])
				deque.pollLast();
			deque.offer(i);
		}
		int tail=0;
		int itr=0;
		for(int i=w;i<arr.length;++i){
			out[itr++]=arr[deque.peek()];
			while(!deque.isEmpty() && deque.peek()<=tail)
				deque.poll();
			while(!deque.isEmpty() && arr[i] > arr[deque.peekLast()])
				deque.pollLast();
			++tail;
			deque.offer(i);
		}
		out[itr] = arr[deque.peek()];
		return out;
	}
	//get maximum of the sliding window size after every time a number is passed
	// init these as class variables in class constructor
	LinkedList<Integer> q = new LinkedList<Integer>();
	int k=3;
	public int getMaximalAtEveryPoint(int num) {
		
		while(!q.isEmpty() && num > q.peekLast())
			q.pollLast();
		q.offer(num);
		while(q.size()>k)
			q.poll();
		return q.peek();
	}
}
