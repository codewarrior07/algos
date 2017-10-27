package graphs;

import java.util.ArrayList;
import java.util.List;

import StacksnQueues.QueueEmptyException;
import StacksnQueues.QueueLL;
import StacksnQueues.StackEmptyException;
import StacksnQueues.StackLL;

public class UndirectedGraphMatrix<T> {
	List<Vertex<T>> vList;
	int[][] adjMatrix;
	int maxSize;
	public UndirectedGraphMatrix(int maxSize) {
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
			if(vList.get(i).label == v1.label)
				v1Index = i;
			else if(vList.get(i).label == v2.label)
				v2Index = i;
		}
		if(v1Index != -1 && v2Index != -1) {
			adjMatrix[v1Index][v2Index] = 1;
			adjMatrix[v2Index][v1Index] = 1;
		}
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
			for(int j=i+1;j<mstMatrix[i].length;++j) {
				if(mstMatrix[i][j] == 1)
					System.out.println(""+vList.get(i) + vList.get(j));
				
			}
		}
		return mstMatrix;
	}
	public static void main(String[] args) throws StackEmptyException, QueueEmptyException{
		UndirectedGraphMatrix<Character> g = new UndirectedGraphMatrix<Character>(10);
		g.addVertex('A');
		g.addVertex('B');
		g.addVertex('C');
		g.addVertex('D');
		g.addVertex('E');
		g.addEdge(new Vertex<Character>('A'), new Vertex<Character>('B'));
		g.addEdge(new Vertex<Character>('A'), new Vertex<Character>('C'));
		g.addEdge(new Vertex<Character>('B'), new Vertex<Character>('D'));
		g.addEdge(new Vertex<Character>('D'), new Vertex<Character>('E'));
		g.BFS();
	}		
}
