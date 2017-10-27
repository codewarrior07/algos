package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import StacksnQueues.QueueEmptyException;
import StacksnQueues.QueueLL;
import StacksnQueues.StackEmptyException;
import StacksnQueues.StackLL;

public class DAGMatrix<T> {
	List<Vertex<T>> vList;
	int[][] adjMatrix;
	int maxSize;
	public DAGMatrix(int maxSize) {
		vList = new ArrayList<Vertex<T>>();
		adjMatrix = new int[maxSize][maxSize];
		this.maxSize = maxSize;
	}
	public void addVertex(T dat) {
		if(vList.size() < maxSize){
			Vertex<T> newVert = new Vertex<T>(dat);
			vList.add(newVert);
		}
	}
	public void addEdge(Vertex<T> v1, Vertex<T> v2) {
		int v1Index = -1, v2Index = -1;
		for(int i=0;i<vList.size();++i){
			if(vList.get(i).label == v1.label )
				v1Index = i;
			if(vList.get(i).label == v2.label)
				v2Index = i;
		}
		if(v1Index != -1 && v2Index != -1) 
			adjMatrix[v1Index][v2Index] = 1;
	}
	public void DFS() throws StackEmptyException{
		StackLL<Integer> st = new StackLL<Integer>();
		vList.get(0).wasVisited = true;
		System.out.println(vList.get(0));
		st.push(0);
		while(!st.isEmpty()) {
			int nextVert = this.getUnvisitedVertex(st.peek());
			if(nextVert == -1)
				st.pop();
			else {
				vList.get(nextVert).wasVisited = true;
				System.out.println(vList.get(nextVert));
				st.push(nextVert);
			}
		}
		for(Vertex<T> v:vList)
			v.wasVisited = false;
	}
	public void BFS() throws QueueEmptyException {
		QueueLL<Integer> q = new QueueLL<Integer>();
		vList.get(0).wasVisited = true;
		System.out.println(vList.get(0));
		q.enqueue(0);
		while(!q.isEmpty()) {
			int nextVert = this.getUnvisitedVertex(q.peek());
			if(nextVert == -1)
				q.dequeue();
			else {
				vList.get(nextVert).wasVisited = true;
				System.out.println(vList.get(nextVert));
				q.enqueue(nextVert);
			}
		}
		for(Vertex<T> v:vList)
			v.wasVisited = false;
	}
	private int getUnvisitedVertex(int v) {
		for(int i=0;i<adjMatrix[v].length;++i) {
			if(adjMatrix[v][i] == 1 && !(vList.get(i).wasVisited))
				return i;
		}
		return -1;
	}
	public int[][] mst() throws StackEmptyException {
		int[][] mstMatrix = new int[vList.size()][vList.size()];
		StackLL<Integer> st = new StackLL<Integer>();
		vList.get(0).wasVisited = true;
		st.push(0);
		while(!st.isEmpty()) {
			int curr = st.peek();
			int nextVert = this.getUnvisitedVertex(curr);
			if(nextVert == -1) 
				st.pop();
			else {
				mstMatrix[curr][nextVert] = 1;
				mstMatrix[nextVert][curr] = 1;
				vList.get(nextVert).wasVisited = true;
				st.push(nextVert);
			}
		}
		for(Vertex<T> v:vList)
			v.wasVisited = false;
		// print MST
		for(int i=0;i<mstMatrix.length;++i) {
			for(int j=0;j<mstMatrix[i].length;++j) {
				if(mstMatrix[i][j] == 1)
					System.out.println(""+vList.get(i) + vList.get(j));
				
			}
		}
		return mstMatrix;
	}
	public List<Vertex<T>> topo() throws StackEmptyException {
		List<Vertex<T>> topo = new ArrayList<Vertex<T>>();
		StackLL<Integer> st = new StackLL<Integer>();
		vList.get(0).wasVisited = true;
		st.push(0);
		while(!st.isEmpty()) {
			int curr = st.peek();
			int next = -1;
			for(int i=0;i<adjMatrix[curr].length;++i) {
				if(adjMatrix[curr][i] == 1) {
					if(vList.get(i).wasVisited && st.contains(i)) {
						System.out.println("Graph contains cycle");
						return null;
					}
					else if(!vList.get(i).wasVisited) {
						next = i;
						break;
					}
				}
			}
			if(next == -1) {
				st.pop();
				topo.add(vList.get(curr));
			}
			else {
				vList.get(next).wasVisited = true;
				st.push(next);
			}
		}
		for(Vertex<T> v:vList)
			v.wasVisited = false;
		Collections.reverse(topo);
		for(Vertex<T> v:topo)
			System.out.println(v.label);
		return topo;
	}
	/*
	 Given a directed graph, check whether the graph contains a cycle or not. 
	 Your function should return true if the given graph contains at least one cycle, else return false.
	 */
	public boolean containsCycle(){
		Stack<Vertex<T>> st = new Stack<Vertex<T>>();
		st.push(vList.get(0));
		vList.get(0).wasVisited = true;
		boolean flag = false;
		loops:while(!st.isEmpty()) {
			Vertex<T> curr= st.peek();
			int currIndex = vList.indexOf(curr);
			int nextIndex = -1;
			for(int i=0;i<adjMatrix[currIndex].length;++i){
				if(adjMatrix[currIndex][i]>0) {
					if(st.contains(vList.get(i))){
						flag= true;
						break loops;
					}
					if(!vList.get(i).wasVisited){
						vList.get(i).wasVisited = true;
						nextIndex = i;
						st.push(vList.get(i));
						break;
					}
				}
			}
			if(nextIndex == -1)
				st.pop();
		}
		for(Vertex<T> v:vList)
			v.wasVisited = false;
		return flag;
		
	}
	
	public static void main(String[] args) throws StackEmptyException, QueueEmptyException{
		DAGMatrix<Character> g = new DAGMatrix<Character>(10);
		g.addVertex('A');
		g.addVertex('B');
		g.addVertex('C');
		g.addVertex('D');
		g.addVertex('E');
		g.addEdge(new Vertex<Character>('A'), new Vertex<Character>('B'));
		g.addEdge(new Vertex<Character>('B'), new Vertex<Character>('C'));
		g.addEdge(new Vertex<Character>('B'), new Vertex<Character>('D'));
		g.addEdge(new Vertex<Character>('C'), new Vertex<Character>('E'));
		g.addEdge(new Vertex<Character>('E'), new Vertex<Character>('D'));
		//g.addEdge(new Vertex<Character>('D'), new Vertex<Character>('A'));
		//System.out.println(g.containsCycle());
		g.DFS();
	}
}
