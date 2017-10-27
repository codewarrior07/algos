package graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import LinkedLists.DLL;
import StacksnQueues.QueueEmptyException;
import StacksnQueues.QueueLL;
import StacksnQueues.StackEmptyException;
import StacksnQueues.StackLL;


public class UndirectedGraphList<T> {
	List<Vertex<T>> vList;
	Map<Vertex<T>,DLL<Vertex<T>>> adjList;
	Map<Vertex<T>,Set<Vertex<T>>> adjList1;
	public UndirectedGraphList(){
		vList = new ArrayList<Vertex<T>>();
		adjList = new HashMap<Vertex<T>,DLL<Vertex<T>>>();
		adjList1 = new HashMap<Vertex<T>,Set<Vertex<T>>>();
	}
	public boolean isEmpty(){	
		return adjList.size() == 0;
	}
	public void addVertex(T dat){
		Vertex<T> newVert = new Vertex<T>(dat);
		vList.add(newVert);
	}
	public void addEdge(Vertex<T> v1, Vertex<T> v2) {
		DLL<Vertex<T>> temp;
		if(adjList.containsKey(v1)) 
			temp = adjList.get(v1);
		else 
			temp = new DLL<Vertex<T>>();
		temp.insertFirst(v2);
		adjList.put(v1,temp);
		if(adjList.containsKey(v2))
			temp = adjList.get(v2);
		else
			temp = new DLL<Vertex<T>>();
		temp.insertFirst(v1);
		adjList.put(v2,temp);
		
		// adj list using set
		Set<Vertex<T>> temp1;
		if(adjList1.containsKey(v1)) 
			temp1 = adjList1.get(v1);
		else 
			temp1 = new HashSet<Vertex<T>>();
		temp1.add(v2);
		adjList1.put(v1,temp1);
		if(adjList1.containsKey(v2))
			temp1 = adjList1.get(v2);
		else
			temp1 = new HashSet<Vertex<T>>();
		temp1.add(v1);
		adjList1.put(v2,temp1);
		
		/*for(Map.Entry<Vertex<T>,DoubleLinkedDoubleEndedList<Vertex<T>>> entry:adjList.entrySet()) {
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
		}*/
	}
	public void DFS() throws StackEmptyException {
		StackLL<Vertex<T>> st = new StackLL<Vertex<T>>();
		vList.get(0).wasVisited = true;
		st.push(vList.get(0));
		while(!st.isEmpty()) {
			Vertex<T> nextVert = st.pop();
			System.out.println(nextVert.label);
			DLL<Vertex<T>> list = adjList.get(nextVert);
			while(list != null && !list.isEmpty()) {
				Vertex<T> vert = this.getVertex(list.deleteFirst().data);
				if(!vert.wasVisited) {
					vert.wasVisited = true;
					st.push(vert);
				}
			}
		}
		for(Vertex<T> v:vList)
			v.wasVisited = false;
	}
	public void BFS() throws QueueEmptyException{
		QueueLL<Vertex<T>> q = new QueueLL<Vertex<T>>();
		vList.get(0).wasVisited = true;
		q.enqueue(vList.get(0));
		while(!q.isEmpty()) {
			Vertex<T> nextVert = q.dequeue();
			System.out.println(nextVert.label);
			DLL<Vertex<T>> list = adjList.get(nextVert);
			while(!list.isEmpty()) {
				Vertex<T> vert = this.getVertex(list.deleteFirst().data);
				if(!vert.wasVisited) {
					vert.wasVisited = true;
					q.enqueue(vert);
				}
			}
		}
		for(Vertex<T> v:vList)
			v.wasVisited = false;
	}
	private Vertex<T> getVertex(Vertex<T> v) {
		for(Vertex<T> v1:vList) {
			if(v1.label == v.label)
				return v1;
		}
		return null;
	}
	/*public Map<Vertex<T>,DoubleLinkedDoubleEndedList<Vertex<T>>> mst() throws QueueEmptyException {
		Map<Vertex<T>,DoubleLinkedDoubleEndedList<Vertex<T>>> mst = new HashMap<Vertex<T>,DoubleLinkedDoubleEndedList<Vertex<T>>>();
		QueueLL<Vertex<T>> q = new QueueLL<Vertex<T>>();
		mst.put(vList.get(0), new DoubleLinkedDoubleEndedList<Vertex<T>>());
		vList.get(0).wasVisited = true;
		q.enqueue(vList.get(0));
		while(!q.isEmpty()) {
			Vertex<T> curr = q.dequeue();
			DoubleLinkedDoubleEndedList<Vertex<T>> list = adjList.get(curr);
			while(list != null && !list.isEmpty()) {
				Vertex<T> nextVert = list.deleteFirst().data;
				if(!nextVert.wasVisited) {
					nextVert.wasVisited = true;
					q.enqueue(nextVert);
					DoubleLinkedDoubleEndedList<Vertex<T>>  temp;
					if(mst.containsKey(curr))
						temp = mst.get(curr);
					else
						temp = new DoubleLinkedDoubleEndedList<Vertex<T>>();
					temp.insertFirst(nextVert);
					mst.put(curr, temp);
				}
			}
			for(Vertex<T> v:vList)
				v.wasVisited = false;
		}
		// print mst
		for(Map.Entry<Vertex<T>,DoubleLinkedDoubleEndedList<Vertex<T>>> entry:mst.entrySet()) {
			DoubleLinkedDoubleEndedList<Vertex<T>> list = entry.getValue();
			while(!list.isEmpty()) {
				T label = list.deleteFirst().data.label;
				System.out.println(""+entry.getKey()+label);
			}
		}
		return mst;
	}*/
	
	// MST using BFS
	public Map<Vertex<T>,Set<Vertex<T>>> mst() {
		Queue<Vertex<T>> q = new LinkedList<Vertex<T>>();
		Set<Vertex<T>> visited = new HashSet<Vertex<T>>();
		Map<Vertex<T>, Set<Vertex<T>>> mst = new HashMap<Vertex<T>, Set<Vertex<T>>>();
		q.add(vList.get(0));
		visited.add(vList.get(0));
		while(!q.isEmpty()){
			Vertex<T> curr = q.remove();
			Set<Vertex<T>> temp;
			if(mst.containsKey(curr))
				temp = mst.get(curr);
			else
				temp = new HashSet<Vertex<T>>();
			for(Vertex<T> v:adjList1.get(curr)){
				if(!visited.contains(v)){
					visited.add(v);
					temp.add(v);
					q.add(v);
					Set<Vertex<T>> temp1 = new HashSet<Vertex<T>>();
					temp1.add(curr);
					mst.put(v,temp1);
				}
			}
			mst.put(curr,temp);
		}
		return mst;
	}
	
	//check if graph has cycles
	public boolean hasCycles(){
		if(vList.size()==0 || adjList1.size()==0)
			return false;
		Vertex<T> parent=vList.get(0);
		Set<Vertex<T>> seen = new HashSet<Vertex<T>>();
		seen.add(vList.get(0));
		return hasCycles(vList.get(0),parent,seen);
	}
	public boolean hasCycles(Vertex<T> curr,Vertex<T> parent,Set<Vertex<T>> seen){ 
		for(Vertex<T> v:adjList1.containsKey(curr)?adjList1.get(curr):Collections.<Vertex<T>>emptySet()){
			if(seen.contains(v) && !v.equals(parent))
				return true;
			if(!seen.contains(v)){
				seen.add(v);
				return hasCycles(v,curr,seen);
			}
		}
		return false;
	}
	
	/*
	 * You are given a tree (a simple connected undirected graph with no cycles).
	   Find the maximum number of edges you can remove from the tree to get a forest 
	   such that each connected component of the forest contains an even number of vertices.
	 */
	/*
	 * Solution - use DFS to calculate if a node has odd or even nodes connected to it. If 
	 * even, along with itself there'll be odd number of nodes, so we cant make a cut and vice-versa
	 */
	class WrapCount {
		int count=0;
	}
	public int getCuts() {
		Set<Vertex<T>> seen = new HashSet<>();
		seen.add(vList.get(0)); // connected graph
		WrapCount total = new WrapCount();
		getCuts(vList.get(0),seen,total);
		return total.count;
	}
	private int getCuts(Vertex<T> curr, Set<Vertex<T>> seen, WrapCount total) {
		int numNodes=0;
		for(Vertex<T> v:adjList1.containsKey(curr)?adjList.get(curr):Collections.<Vertex<T>>emptySet()) {
			if(!seen.contains(v)) {
				seen.add(v);
				int nextNumNodes=getCuts(v,seen,total);
				if(nextNumNodes%2==0)
					++total.count;
				else
					numNodes += nextNumNodes;
			}
		}
		return numNodes+1; // add current node to count
	}
	
	public static void main(String[] args) throws StackEmptyException, QueueEmptyException{
		/*UndirectedGraphList<Character> g = new UndirectedGraphList<Character>();
		g.addVertex('A');
		g.addVertex('B');
		g.addVertex('C');
		g.addVertex('D');
		g.addVertex('E');
		g.addEdge(new Vertex<Character>('A'), new Vertex<Character>('A'));
		g.addEdge(new Vertex<Character>('A'), new Vertex<Character>('B'));
		g.addEdge(new Vertex<Character>('A'), new Vertex<Character>('C'));
		g.addEdge(new Vertex<Character>('B'), new Vertex<Character>('D'));
		g.addEdge(new Vertex<Character>('C'), new Vertex<Character>('E'));
		g.BFS();*/
		
		UndirectedGraphList<Character> g = new UndirectedGraphList<Character>();
		g.addVertex('A');
		g.addVertex('B');
		g.addVertex('C');
		g.addVertex('D');
		g.addVertex('E');
		g.addEdge(new Vertex<Character>('A'), new Vertex<Character>('B'));
		g.addEdge(new Vertex<Character>('A'), new Vertex<Character>('C'));
		g.addEdge(new Vertex<Character>('B'), new Vertex<Character>('D'));
		//g.addEdge(new Vertex<Character>('B'), new Vertex<Character>('C'));
		//g.addEdge(new Vertex<Character>('C'), new Vertex<Character>('E'));
		g.addEdge(new Vertex<Character>('D'), new Vertex<Character>('E'));
		/*for(Map.Entry<Vertex<Character>,Set<Vertex<Character>>> entr:g.mst().entrySet())
			System.out.println(entr.getKey()+ " "+entr.getValue());*/
		//System.out.println(g.hasCycles());	
		
		g = new UndirectedGraphList<Character>();
		g.addVertex('A');
		g.addVertex('B');
		g.addVertex('C');
		g.addVertex('D');
		g.addVertex('E');
		g.addVertex('F');
		g.addVertex('G');
		g.addVertex('H');
		g.addEdge(new Vertex<Character>('A'), new Vertex<Character>('B'));
		g.addEdge(new Vertex<Character>('A'), new Vertex<Character>('H'));
		g.addEdge(new Vertex<Character>('B'), new Vertex<Character>('G'));
		g.addEdge(new Vertex<Character>('B'), new Vertex<Character>('C'));
		g.addEdge(new Vertex<Character>('C'), new Vertex<Character>('D'));
		g.addEdge(new Vertex<Character>('C'), new Vertex<Character>('F'));
		g.addEdge(new Vertex<Character>('F'), new Vertex<Character>('E'));
		System.out.println(g.getCuts());
	}
}
