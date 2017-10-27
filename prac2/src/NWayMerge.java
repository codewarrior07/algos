import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;


public class NWayMerge {

	public static void main(String[] args) {
		NWayMerge o = new NWayMerge();
		/*for(int i:o.nwayMergeArrays(new int[][]{{1,6},{5,9,11,12,15},{8,9,15}}))
			System.out.print(i+" ");*/
		Node n1 = o.new Node(2);
		Node n2 = o.new Node(4);
		Node n3 = o.new Node(6);
		n1.next = n2; n2.next=n3;
		
		Node n4 = o.new Node(1);
		Node n5 = o.new Node(3);
		Node n6 = o.new Node(5);
		n4.next = n5; n5.next=n6;
		
		List<Node> list = new ArrayList<Node>();
		list.add(n1);
		list.add(n4);
		
		Node h = o.nwayMergeLists(list);
		while(h!=null){
			System.out.println(h.data);
			h=h.next;
		}
	}
	
	// merge n arrays
	class ArrayContainer {
		int[] arr;
		int index;
		ArrayContainer(int[] arr,int ind){
			this.arr = arr;
			this.index = ind;
		}
	}
	public int[] nwayMergeArrays(int[][] arr){
		if(arr == null)
			return null;
		int size=0;
		PriorityQueue<ArrayContainer> pq = new PriorityQueue<>(new Comparator<ArrayContainer>(){
												@Override
												public int compare(ArrayContainer a1, ArrayContainer a2){
													return a1.arr[a1.index]-a2.arr[a2.index];
												}
											});
		for(int[] ar:arr){
			pq.add(new ArrayContainer(ar,0));
			size += ar.length;
		}
		int[] fin = new int[size];
		int itr=0;
		while(!pq.isEmpty()){
			ArrayContainer curr = pq.remove();
			fin[itr++] = curr.arr[curr.index];
			if(curr.index<curr.arr.length-1){
				++curr.index;
				pq.add(curr);
			}
		}
		return fin;
	}
	
	// merge n lists
	private class Node {
		int data;
		Node next;
		public Node(int data){
			this.data = data;
		}
	}
	public Node nwayMergeLists(List<Node> list){
		if(list==null)
			return null;
		Node finHead=null;
		PriorityQueue<Node> pq = new PriorityQueue<Node>(new Comparator<Node>(){
									@Override
									public int compare(Node n1,Node n2){
										return n1.data-n2.data;
									}
								});
		for(Node curr:list)
			pq.add(curr);
		
		Node prev=null;
		while(!pq.isEmpty()){
			Node curr = pq.remove();
			if(finHead==null){
				finHead=curr;
				prev=finHead;
			}
			else {
				prev.next = curr;
				prev=curr;
			}
			if(curr.next != null)
				pq.add(curr.next);
		}
		return finHead;
	}
	
}
