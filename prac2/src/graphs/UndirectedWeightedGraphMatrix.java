package graphs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class UndirectedWeightedGraphMatrix<T> {
	List<Vertex<T>> vList;
	int[][] adjMatrix;
	int maxSize;
	public UndirectedWeightedGraphMatrix(int maxSize) {
		vList = new ArrayList<Vertex<T>>();
		adjMatrix = new int[maxSize][maxSize];
		this.maxSize = maxSize;
	}
	public Vertex<T> addVertex(T dat) {
		if(vList.size() < maxSize){
			Vertex<T> newVert = new Vertex<T>(dat);
			vList.add(newVert);
			return newVert;
		}
		return null;
	}
	public void addEdge(Vertex<T> from, Vertex<T> to, int weight) {
		int v1Index = -1, v2Index = -1;
		for(int i=0;i<vList.size();++i){
			if(vList.get(i).label == from.label)
				v1Index = i;
			else if(vList.get(i).label == to.label)
				v2Index = i;
		}
		if(v1Index != -1 && v2Index != -1) {
			adjMatrix[v1Index][v2Index] = weight;
			adjMatrix[v2Index][v1Index] = weight;
		}
	}
	public int[][] mst() {
		int[][] mst = new int[vList.size()][vList.size()];
		SortedList<Edge<T>> pq = new SortedList<Edge<T>>();
		List<Integer> vertList = new ArrayList<Integer>();
		vertList.add(0);
		int newVert = 0;
		while(vertList.size()<vList.size()) {
			List<Edge<T>> edgeList = new ArrayList<Edge<T>>();
			for(int i=0;i<adjMatrix[newVert].length;++i) {
				if(adjMatrix[newVert][i] > 0 && !vertList.contains(i))
					edgeList.add(new Edge<T>(vList.get(newVert),vList.get(i),adjMatrix[newVert][i]));
			}
			if(edgeList.isEmpty())
				break;
			if(pq.isEmpty()) {
				for(Edge<T> e:edgeList) {
					pq.insert(e);
				}
			}
			else {
				for(Edge<T> e:edgeList) {
					Edge<T> pqEdge = pq.findWithComparator(e,new ToVertexComparator<T>());
					if(pqEdge!= null) {
						if(pqEdge.weight > e.weight) {
							pq.remove(pqEdge);
							pq.insert(e);
						}
					} else {
						pq.insert(e);
					}
				}
			}
			Edge<T> nextEdge = pq.removeMin();			
			int fromVertIndex = vList.indexOf(nextEdge.fromVertex);
			int toVertIndex = vList.indexOf(nextEdge.toVertex);
			mst[fromVertIndex][toVertIndex] = nextEdge.weight;
			mst[toVertIndex][fromVertIndex] = nextEdge.weight;
			if(newVert != fromVertIndex)
				newVert = fromVertIndex;
			else
				newVert = toVertIndex;
			vertList.add(newVert);
		}
		return mst;
	}
	public static void main(String[] args) {
		/*Vertex<Character> v1 = new Vertex<Character>('A');
		Vertex<Character> v2 = new Vertex<Character>('B');
		Vertex<Character> v3 = new Vertex<Character>('C');
		Edge<Character> e1 = new Edge<Character>(v1,v2,10);
		Edge<Character> e2 = new Edge<Character>(v2,v3,5);
		Edge<Character> e3 = new Edge<Character>(v1,v3,1);
		SortedList<Edge<Character>> sl = new SortedList<Edge<Character>>();
		sl.insert(e1);
		sl.insert(e2);
		sl.insert(e3);
		sl.remove(e1);
		sl.display();
		System.out.println(sl.findWithComparator(e1, new ToVertexComparator<Character>()));*/
		UndirectedWeightedGraphMatrix<Character> g = new UndirectedWeightedGraphMatrix<Character>(10);
		Vertex<Character> v1=g.addVertex('A');
		Vertex<Character> v2=g.addVertex('B');
		Vertex<Character> v3=g.addVertex('C');
		Vertex<Character> v4=g.addVertex('D');
		Vertex<Character> v5=g.addVertex('E');
		Vertex<Character> v6=g.addVertex('F');
		g.addEdge(v1, v2, 6);
		g.addEdge(v2, v3, 10);
		g.addEdge(v3, v4, 8);
		g.addEdge(v4, v5, 12);
		g.addEdge(v5, v6, 7);
		g.addEdge(v6, v3, 6);
		g.addEdge(v2, v5, 7);
		g.addEdge(v1, v4, 4);
		g.addEdge(v3, v5, 5);
		g.addEdge(v2, v4, 7);
		int[][] mst = g.mst();
		for(int i=0;i<mst.length;++i){
			for(int j=0;j<mst.length;++j)
				System.out.print(mst[i][j]);
			System.out.println();
		}
	}
}

class PQNode<T> {
	T data;
	PQNode<T> next;
	PQNode<T> prev;
	public PQNode(T data) {
		this.data = data;
		next = prev = null;
	}
}

class SortedList<T extends Comparable> {
	PQNode<T> first;
	public SortedList() {
		this.first = null;
	}
	public boolean isEmpty() {
		return first == null;
	}
	public void insert(T data) {
		PQNode<T> nn = new PQNode<T>(data);
		if(first == null)
			first = nn;
		else {
			PQNode<T> curr = first;
			PQNode<T> prev = first;
			while(curr != null && curr.data.compareTo(data) <= 0) {
				prev = curr;
				curr = curr.next;
			}
			if(prev == first) {
				nn.next = first;
				first.prev = nn;
				first = nn;
			} else {
				nn.next = prev.next;
				nn.prev = prev;
				prev.next = nn;
			}
		}
	}
	public T removeMin() {
		if(isEmpty())
			return null;
		PQNode<T> temp = first;
		first = first.next;
		if(first!=null)
			first.prev = null;
		return temp.data;
	}
	public void remove(T data) {
		if(isEmpty())
			return;
		PQNode<T> curr = find(data);
		if(curr!=null) {
			if(curr == first)
				first = first.next;
			else
				curr.prev.next = curr.next;
			if(curr.next != null)
				curr.next.prev = curr.prev;
		}
	}
	public PQNode<T> find(T data) {
		PQNode<T> curr = first;
		while(curr != null) {
			if(curr.data.equals(data))
				return curr;
			curr = curr.next;
		}
		return null;
	}
	public T findWithComparator(T data,Comparator<T> comp) {
		PQNode<T> curr = first;
		while(curr!=null) {
			if(comp.compare(curr.data, data) == 0)
				return curr.data;
			curr = curr.next;
		}
		return null;
	}
	public void display() {
		PQNode<T> curr = first;
		while(curr!=null) {
			System.out.println(curr.data);
			curr = curr.next;
		}
	}
}
