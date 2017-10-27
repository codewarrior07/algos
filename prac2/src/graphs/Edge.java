package graphs;

import java.util.Comparator;

public class Edge<T> implements Comparable<T>{
	public Vertex<T> fromVertex;
	public Vertex<T> toVertex;
	int weight;
	public Edge(Vertex<T> from, Vertex<T> to, int weight) {
		this.fromVertex = from;
		this.toVertex = to;
		this.weight = weight;
	}
	@SuppressWarnings("unchecked")
	@Override
	public int compareTo(Object obj) {
		if(obj != null && obj instanceof Edge) {
			return (this.weight - ((Edge<T>)obj).weight); 
		}
		return 0;
	}
	
	@Override
	public String toString() {
		return ""+this.fromVertex + " "+this.toVertex +" "+ this.weight;
	}
}

class ToVertexComparator<T> implements Comparator<Edge<T>> {
	@Override
	public int compare(Edge<T> e1, Edge<T> e2) {
		if(e1.toVertex.equals(e2.toVertex))
			return 0;
		else
			return -1;
	}
}
