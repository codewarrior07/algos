package graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class UndirectedWeightedGraphList<T> {
	List<Vertex<T>> vList;
	Map<Vertex<T>,Set<Edge>> adjList;
	public UndirectedWeightedGraphList(){
		this.vList = new ArrayList<Vertex<T>>();
		this.adjList = new HashMap<Vertex<T>,Set<Edge>>();
	}
	class Edge {
		Vertex<T> from;
		Vertex<T> to;
		int weight;
		public Edge(Vertex<T> from, Vertex<T> to, int weight){
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public boolean equals(Object o){
			if(o != null && o instanceof UndirectedWeightedGraphList.Edge){
				Edge e = ((Edge)o);
				return this.from.equals(e.from) && this.to.equals(e.to) && this.weight==e.weight;
			}
			return false;
		}

		@Override
		public String toString(){
			return this.from+" "+this.to;
		}
	}

	public void addVertex(T dat){
		Vertex<T> newVert = new Vertex<T>(dat);
		vList.add(newVert);
	}

	public void addWeightedEdge(Vertex<T> v1, Vertex<T> v2,int weight) {
		Set<Edge> temp;
		if(adjList.containsKey(v1)) 
			temp = adjList.get(v1);
		else 
			temp = new HashSet<Edge>();
		temp.add(new Edge(v1,v2,weight));
		adjList.put(v1,temp);
	}

	//not working currently
	public Map<Vertex<T>,Set<Edge>> mst(){
		Map<Vertex<T>,Set<Edge>> mst = new HashMap<Vertex<T>,Set<Edge>>();
		Set<Vertex<T>> seen = new HashSet<Vertex<T>>();
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>(10, new Comparator<Edge>(){
			@Override
			public int compare(Edge e1,Edge e2){
				return e1.weight - e2.weight;
			}
		});
		for(int i=0;i<vList.size();++i){
			if(!seen.contains(vList.get(i))){
				Vertex<T> curr = vList.get(i);
				seen.add(curr);
				for(Edge e:(adjList.containsKey(curr)?adjList.get(curr):Collections.<Edge>emptySet()))
					pq.add(e);
				while(!pq.isEmpty()){
					Edge min = pq.remove();

					Set<Edge> temp1 = mst.containsKey(curr)?mst.get(curr):new HashSet<Edge>();
					temp1.add(min);
					mst.put(curr,temp1);
					Set<Edge> temp2 = mst.containsKey(min.to)?mst.get(min.to):new HashSet<Edge>();
					temp2.add(min);
					mst.put(min.to,temp2);

					seen.add(min.to);

					for(Edge e:pq){
						if(e.to.equals(min.to))
							pq.remove(e);
					}

					curr = min.to;
					for(Edge e:(adjList.containsKey(curr)?adjList.get(curr):Collections.<Edge>emptySet())){
						if(!seen.contains(e.to))
							pq.add(e);
					}
				}
			}
		}
		return mst;
	}

	//djikstra's algo
	public Map<Vertex<T>,Integer> djikstra(Vertex<T> source) {
		Map<Vertex<T>,Integer> dist = new HashMap<Vertex<T>,Integer>();
		Set<Vertex<T>> seen = new HashSet<Vertex<T>>();
		PriorityQueue<VertWeight<T>> pq = new PriorityQueue<VertWeight<T>>(10,new Comparator<VertWeight<T>>(){
			@Override
			public int compare(VertWeight<T> v1,VertWeight<T> v2){
				return v2.weight-v1.weight;
			}
		});
		//dist.put(source,0);
		pq.add(new VertWeight<T>(source,0));
		while(!pq.isEmpty()){
			VertWeight<T> curr= pq.remove();
			seen.add(curr.vert);
			dist.put(curr.vert,curr.weight);
			for(Edge e:(adjList.containsKey(curr.vert)?adjList.get(curr.vert):Collections.<Edge>emptySet())){
				Vertex<T> v = (e.from.equals(curr.vert))?e.to:e.from;
				if(!seen.contains(v)){
					boolean flag = false;
					for(VertWeight<T> vw:pq){
						if(vw.vert.equals(v)){
							if(curr.weight+e.weight < vw.weight){
								pq.remove(vw);
								pq.add(new VertWeight<T>(vw.vert,curr.weight+e.weight));
							}
							flag = true;
							break;
						}
					}// end for
					if(!flag)
						pq.add(new VertWeight<T>(v,curr.weight+e.weight));
				}// end if
			}
		}
		return dist;
	}

	public static void main(String[] args){
		UndirectedWeightedGraphList<String> g = new UndirectedWeightedGraphList<String>();
		g.addVertex("A");
		g.addVertex("B");
		g.addVertex("C");
		g.addVertex("D");
		g.addVertex("E");
		g.addVertex("Z");
		g.addWeightedEdge(new Vertex<String>("A"),new Vertex<String>("B"),4);
		g.addWeightedEdge(new Vertex<String>("A"),new Vertex<String>("C"),2);
		g.addWeightedEdge(new Vertex<String>("B"),new Vertex<String>("C"),1);
		g.addWeightedEdge(new Vertex<String>("B"),new Vertex<String>("D"),5);
		g.addWeightedEdge(new Vertex<String>("C"),new Vertex<String>("D"),8);
		g.addWeightedEdge(new Vertex<String>("C"),new Vertex<String>("E"),10);
		g.addWeightedEdge(new Vertex<String>("D"),new Vertex<String>("E"),2);
		g.addWeightedEdge(new Vertex<String>("D"),new Vertex<String>("Z"),6);
		g.addWeightedEdge(new Vertex<String>("E"),new Vertex<String>("Z"),3);
		for(Map.Entry<Vertex<String>,Integer> entr:g.djikstra(new Vertex<String>("A")).entrySet())
			System.out.println(entr.getKey()+ " "+entr.getValue());

	}
}
