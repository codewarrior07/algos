public class Triangle implements ITriangle{
	public static void main(String[] args) {
		int[] segments = new int[]{3,1,21,12,8,15,6,4};
		Triangle t = new Triangle();
		for(Integer i:t.getTriangleSides(segments))
			System.out.println(i);
	}
	public int[] getTriangleSides(int[] segments) {
		mergeSort(segments,new int[segments.length],0,segments.length-1);
		for(int i=0;i<segments.length;++i){
			if(isTriangle(segments[i],segments[i+1],segments[i+2]))
				return new int[]{segments[i],segments[i+1],segments[i+2]};
		}
		return new int[3];
	}
	private boolean isTriangle(int a, int b, int c){
		if((a+b)>c)
			return true;
		return false;
	}
	private void mergeSort(int[] inp,int[] temp,int start,int end) {
		if(start == end)
			return;
		int mid = (start+end)/2;
		mergeSort(inp,temp,start,mid);
		mergeSort(inp,temp,mid+1,end);
		merge(inp,temp,start,mid+1,end);
	}
	private void merge(int[] inp,int[] temp,int leftItr,int rightItr,int end){
		int tempItr = 0;
		int mid = rightItr - 1;
		int nItems = end-leftItr +1;
		int lower = leftItr;
		while(leftItr<=mid && rightItr<=end){
			if(inp[leftItr] < inp[rightItr])
				temp[tempItr++] = inp[leftItr++];
			else
				temp[tempItr++] = inp[rightItr++];
		}
		while(leftItr <= mid) 
			temp[tempItr++] = inp[leftItr++];
		while(rightItr <= end)
			temp[tempItr++] = inp[rightItr++];
		for(tempItr = 0;tempItr<nItems;++tempItr)
			inp[tempItr+lower] = temp[tempItr];
	}
}
interface ITriangle {
	 
    /**
     * Three segments of lengths A, B, C form a triangle iff
     *
     *      A + B > C
     *      B + C > A
     *      A + C > B
     *
     * e.g.
     *  6, 4, 5 can form a triangle
     * 10, 2, 7 can't
     *
     * Given a list of segments lengths algorithm should find at least one triplet of segments that form a triangle (if any).
     *
     * Method should return an array of either:
     * - 3 elements: segments that form a triangle (i.e. satisfy the condition above)
     * - empty array if there are no such segments
     */
	 int[] getTriangleSides(int[] segments);
}
