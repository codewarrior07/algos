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
import java.util.Stack;

import LinkedLists.DLL;
import StacksnQueues.QueueEmptyException;
import StacksnQueues.QueueLL;
import StacksnQueues.StackEmptyException;
import StacksnQueues.StackLL;

public class DAGList<T> {
	List<Vertex<T>> vList;
	Map<Vertex<T>,DLL<Vertex<T>>> adjList;
	Map<Vertex<T>,Set<Vertex<T>>> adjList1;
	Map<Vertex<T>,Set<VertWeight>> adjWeightedList;
	public DAGList(){
		vList = new ArrayList<Vertex<T>>();
		adjList = new HashMap<Vertex<T>,DLL<Vertex<T>>>();
		adjList1 = new HashMap<Vertex<T>,Set<Vertex<T>>>();
		adjWeightedList = new HashMap<Vertex<T>,Set<VertWeight>>();
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

		// adj list using set
		Set<Vertex<T>> temp1;
		if(adjList1.containsKey(v1)) 
			temp1 = adjList1.get(v1);
		else 
			temp1 = new HashSet<Vertex<T>>();
		temp1.add(v2);
		adjList1.put(v1,temp1);
		/*for(Map.Entry<Vertex<T>,DoubleLinkedDoubleEndedList<Vertex<T>>> entry:adjList.entrySet()) {
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
		}*/
	}
	public void addWeightedEdge(Vertex<T> v1, Vertex<T> v2,int weight) {
		Set<VertWeight> temp;
		if(adjWeightedList.containsKey(v1)) 
			temp = adjWeightedList.get(v1);
		else 
			temp = new HashSet<VertWeight>();
		temp.add(new VertWeight(v2,weight));
		adjWeightedList.put(v1,temp);
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

	// use this for iterative topological sort
	public List<Vertex<T>> topo() throws StackEmptyException {
		List<Vertex<T>> topo = new ArrayList<Vertex<T>>();
		StackLL<Vertex<T>> st = new StackLL<Vertex<T>>();
		int currIndex = 0;
		vList.get(currIndex).wasVisited = true;
		st.push(vList.get(currIndex));
		while(!st.isEmpty()){
			Vertex<T> curr = st.peek();
			Vertex<T> next = null;
			DLL<Vertex<T>> list = adjList.get(curr);
			while(list != null && !list.isEmpty()) {
				Vertex<T> temp = getVertex(list.deleteFirst().data);
				if(temp.wasVisited && st.contains(temp)) {
					System.out.println("Graph contains cycles");
					return null;
				}
				else if(!temp.wasVisited) {
					next = temp;
					break;
				}
			}
			if(next == null) {
				st.pop();
				topo.add(curr);
			} else {
				markVisited(next);
				st.push(next);
			}
			if(st.isEmpty()){
				while(++currIndex < vList.size()){
					if(!vList.get(currIndex).wasVisited){
						vList.get(currIndex).wasVisited = true;
						st.push(vList.get(currIndex));
						break;
					}
				}
			}
		}
		for(Vertex<T> v:vList)
			v.wasVisited = false;
		Collections.reverse(topo);
		for(Vertex<T> v:topo)
			System.out.println(v.label);
		return topo;
	}
	private void markVisited(Vertex<T> v){
		for(Vertex<T> v1:vList){
			if(v1.equals(v))
				v1.wasVisited = true;
		}
	}

	// use this for recursive topological sort
	public void topoHelper(List<Vertex<T>> top, Stack<Vertex<T>> temp, Set<Vertex<T>> seen) throws Exception{
		if(!temp.isEmpty()){
			Vertex<T> curr = temp.peek();
			for(Vertex<T> v:(adjList1.get(curr)==null?Collections.<Vertex<T>>emptyList():adjList1.get(curr))){
				if(seen.contains(v) && temp.contains(v))
					throw new Exception("Graph contains cycles");
				else if(!seen.contains(v)){
					seen.add(v);
					temp.push(v);
					topoHelper(top,temp,seen);
				}
			}
			temp.pop();
			top.add(curr);
		}
	}

	public List<Vertex<T>> topoRecursive() throws Exception{
		List<Vertex<T>> top = new ArrayList<Vertex<T>>();
		Stack<Vertex<T>> temp = new Stack<Vertex<T>>();
		Set<Vertex<T>> seen = new HashSet<Vertex<T>>();
		for(int i=0;i<vList.size();++i){
			Vertex<T> curr = vList.get(i);
			if(!seen.contains(curr)){
				seen.add(curr);
				temp.push(curr);
				topoHelper(top,temp,seen);
			}
		}
		Collections.reverse(top);
		return top;
	}

	//
	/*public void djikstra(Vertex<T> src){
		Map<Vertex<T>,Boolean> spt = new HashMap<Vertex<T>,Boolean>();
		PriorityQueue<VertWeight> pq = new PriorityQueue<VertWeight>(vList.size(),new Comparator<VertWeight>(){
											public int compare(VertWeight v1,VertWeight v2){
												return v1.weight - v2.weight;
											}
										});
		Map<Vertex<T>,DLL<Vertex<T>>> sptAdjList = new HashMap<Vertex<T>,DLL<Vertex<T>>>();
		spt.put(src,true);
		pq.add(new VertWeight(src,0));
		for(Vertex<T> v:vList){
			if(!v.equals(src)){
				spt.put(v,false);
				pq.add(new VertWeight(v,Integer.MAX_VALUE));
			}
		}
		for(int i=0;i<vList.size();++i){
			VertWeight minVertexWeight = pq.peek();
			//get adj list for the min vert
			DLL<VertWeight> list = this.adjWeightedList.get(minVertexWeight.vert);
			// check if each vertex exists in spt,else add with min weight
			for(VertWeight v:list) {
				if(!spt.get(v.vert)){
					spt.put(v.vert,true);
					pq.add(v);
				}
				else {
					pq.
				}
			}
		}

	}*/

	/*
	 Given a Directed Graph and two vertices in it, check whether there is a path from the first given vertex to second.
	 */
	public List<Vertex<T>> findPath(Vertex<T> src, Vertex<T> dest) {
		if(vList.indexOf(src) == -1 || vList.indexOf(dest) ==-1)
			return null;
		Stack<Vertex<T>> st = new Stack<Vertex<T>>();
		List<Vertex<T>> path = new ArrayList<Vertex<T>>();
		vList.get(vList.indexOf(src)).wasVisited = true;
		path.add(src);
		st.push(src);
		while(!st.isEmpty()){
			Vertex<T> curr = st.pop();
			DLL<Vertex<T>> list = adjList.get(curr);
			while(list!= null && !list.isEmpty()){
				Vertex<T> v =list.deleteFirst().data;
				if(!v.wasVisited) {
					v.wasVisited = true;
					path.add(v);
					if(v.equals(dest)){
						for(Vertex<T> temp:vList)
							temp.wasVisited = false;
						return path;
					}
					st.push(v);
				} else if(v.equals(src)){
					path.add(v);
					for(Vertex<T> temp:vList)
						temp.wasVisited = false;
					return path;
				}
			}
		}
		return null;
	}

	//find if graph has cycles
	public boolean hasCycles(){
		Stack<Vertex<T>> st = new Stack<Vertex<T>>();
		Set<Vertex<T>> seen = new HashSet<Vertex<T>>();
		for(int i=0;i<vList.size();++i){
			if(!seen.contains(vList.get(i))){
				seen.add(vList.get(i));
				st.push(vList.get(i));
				if(hasCyclesHelper(st,seen))
					return true;
			}
		}
		return false;
	}

	public boolean hasCyclesHelper(Stack<Vertex<T>> st, Set<Vertex<T>> seen){
		if(!st.isEmpty()){
			Vertex<T> curr = st.peek();
			for(Vertex<T> v:(adjList1.containsKey(curr)?adjList1.get(curr):Collections.<Vertex<T>>emptyList())){
				if(seen.contains(v) && st.contains(v))
					return true;
				else if(!seen.contains(v)){
					seen.add(v);
					st.push(v);
					if(hasCyclesHelper(st,seen))
						return true;
				}
			}
			st.pop();
		}
		return false;
	}

	//find number of connected components
	public int getNumberOfConnectedComponents(){
		Stack<Vertex<T>> st = new Stack<Vertex<T>>();
		Set<Vertex<T>> seen = new HashSet<Vertex<T>>();
		int count = 0;
		for(int i=0;i<vList.size();++i){
			if(!seen.contains(vList.get(i))){
				seen.add(vList.get(i));
				st.push(vList.get(i));
				++count;
				while(!st.isEmpty()){
					Vertex<T> curr = st.pop();
					for(Vertex<T> v:(adjList1.containsKey(curr)?adjList1.get(curr):Collections.<Vertex<T>>emptySet())){
						if(!seen.contains(v)){
							seen.add(v);
							st.push(v);
						}
					}
				} //end while
			}
		}
		return count;
	}

	// Find Strongly connected components using Kosaraju's algorithm, linear
	public List<List<Vertex<T>>> scc() {
		Set<Vertex<T>> seen = new HashSet<Vertex<T>>();
		List<Vertex<T>> order = new ArrayList<Vertex<T>>();

		// do dfs for normal graph and get reverse order
		for(int i=0;i<vList.size();++i){
			if(!seen.contains(vList.get(i)))
				dfsSpecial(adjList1,seen,vList.get(i),order);
		}
		Collections.reverse(order);

		//build transposed graph
		Map<Vertex<T>,Set<Vertex<T>>> transpose = transpose(adjList1);

		//reset seen
		seen.clear();

		//do dfs for transposed graph in stack order and get components
		List<List<Vertex<T>>> components = new ArrayList<List<Vertex<T>>>();
		for(int i=0;i<order.size();++i){
			if(!seen.contains(order.get(i))){
				List<Vertex<T>> list = new ArrayList<Vertex<T>>();
				dfsSpecial(transpose,seen,order.get(i),list);
				components.add(list);
			}
		}
		return components;

	}

	public void dfsSpecial(Map<Vertex<T>,Set<Vertex<T>>> list,Set<Vertex<T>> seen,Vertex<T> vert,List<Vertex<T>> order) {
		seen.add(vert);
		for(Vertex<T> v:(list.containsKey(vert)?list.get(vert):Collections.<Vertex<T>>emptySet())){
			if(!seen.contains(v))
				dfsSpecial(list,seen,v,order);
		}
		order.add(vert);
	}

	public Map<Vertex<T>,Set<Vertex<T>>> transpose(Map<Vertex<T>,Set<Vertex<T>>> list) { 
		Map<Vertex<T>,Set<Vertex<T>>> transpose = new HashMap<Vertex<T>,Set<Vertex<T>>>();
		for(Map.Entry<Vertex<T>,Set<Vertex<T>>> entr:list.entrySet()){
			for(Vertex<T> v:entr.getValue()){
				Set<Vertex<T>> temp;
				if(transpose.containsKey(v))
					temp = transpose.get(v);
				else
					temp = new HashSet<Vertex<T>>();
				temp.add(entr.getKey());
				transpose.put(v,temp);
			}
		}
		return transpose;
	}
	
	// Find the shortest path and distance from a given node to every other node in 
	// an unweighted DAG
	public void shortestPathUsingBFS(Vertex<T> node) {
		int distance =0;
		Map<Vertex<T>,Vertex<T>> prevNodes = new HashMap<>(); // used to trace path back
		Map<Vertex<T>,Integer> distances = new HashMap<>();
		for(Vertex<T> curr:vList)
			distances.put(curr,0);
		Queue<Vertex<T>> que = new LinkedList<Vertex<T>>();
		Set<Vertex<T>> seen = new HashSet<>();
		seen.add(node);
		que.add(node);
		while(!que.isEmpty()) {
			Vertex<T> curr = que.remove();
			for(Vertex<T> adj:(adjList1.containsKey(curr)?adjList1.get(curr):Collections.<Vertex<T>>emptyList())) {
				if(!seen.contains(adj)) {
					seen.add(adj);
					que.add(adj);
					distances.put(adj, distances.get(curr)+1);
					prevNodes.put(adj,curr);
				}
			}
		}
		//print shortest distances
		for(Map.Entry<Vertex<T>,Integer> entr:distances.entrySet()) {
			System.out.println(entr.getKey()+ " "+entr.getValue());
		}
		//print shortest paths
		for(Map.Entry<Vertex<T>,Vertex<T>> entr:prevNodes.entrySet()) {
			Vertex<T> curr = entr.getKey();
			while(!curr.equals(node)) {
				System.out.print(curr.label + " <- ");
				curr = prevNodes.get(curr);
			}
			System.out.print(node.label);
			System.out.println();
		}
	}

	public static void main(String[] args) throws Exception{
		/*DAGList<Character> g = new DAGList<Character>();
		g.addVertex('A');
		g.addVertex('B');
		g.addVertex('C');
		g.addVertex('D');
		g.addEdge(new Vertex<Character>('A'), new Vertex<Character>('C'));
		g.addEdge(new Vertex<Character>('B'), new Vertex<Character>('C'));
		g.addEdge(new Vertex<Character>('C'), new Vertex<Character>('A'));
		g.addEdge(new Vertex<Character>('A'), new Vertex<Character>('B'));
		g.addEdge(new Vertex<Character>('C'), new Vertex<Character>('D'));
		g.addEdge(new Vertex<Character>('D'), new Vertex<Character>('D'));
		List<Vertex<Character>> path = g.findPath(new Vertex<Character>('D'),new Vertex<Character>('D'));
		for(Vertex<Character> v:path)
			System.out.println(v);*/

		DAGList<String> g = new DAGList<String>();
		g.addVertex("0");
		g.addVertex("1");
		g.addVertex("2");
		g.addVertex("3");
		g.addVertex("4");
		g.addVertex("5");
		g.addVertex("6");
		g.addVertex("7");
		g.addVertex("8");
		g.addVertex("9");
		g.addVertex("10");
		g.addVertex("11");
		g.addVertex("12");
		g.addVertex("13");
		g.addVertex("14");
		g.addVertex("15");
		g.addVertex("16");
		g.addVertex("17");
		g.addEdge(new Vertex<String>("0"), new Vertex<String>("4"));
		g.addEdge(new Vertex<String>("1"), new Vertex<String>("4"));
		g.addEdge(new Vertex<String>("0"), new Vertex<String>("7"));
		g.addEdge(new Vertex<String>("4"), new Vertex<String>("5"));
		g.addEdge(new Vertex<String>("5"), new Vertex<String>("6"));
		g.addEdge(new Vertex<String>("2"), new Vertex<String>("3"));
		g.addEdge(new Vertex<String>("3"), new Vertex<String>("6"));
		g.addEdge(new Vertex<String>("2"), new Vertex<String>("6"));
		g.addEdge(new Vertex<String>("7"), new Vertex<String>("8"));
		g.addEdge(new Vertex<String>("8"), new Vertex<String>("9"));
		g.addEdge(new Vertex<String>("8"), new Vertex<String>("12"));
		g.addEdge(new Vertex<String>("11"), new Vertex<String>("12"));
		g.addEdge(new Vertex<String>("11"), new Vertex<String>("15"));
		g.addEdge(new Vertex<String>("13"), new Vertex<String>("16"));
		g.addEdge(new Vertex<String>("16"), new Vertex<String>("17"));

		/*for(Vertex<String> v:g.topoRecursive())
			System.out.println(v);*/
		g = new DAGList<String>();
		g.addVertex("0");
		g.addVertex("1");
		g.addVertex("2");
		g.addVertex("3");
		g.addVertex("4");
		g.addVertex("5");
		g.addEdge(new Vertex<String>("0"), new Vertex<String>("2"));
		g.addEdge(new Vertex<String>("0"), new Vertex<String>("1"));
		g.addEdge(new Vertex<String>("1"), new Vertex<String>("2"));
		//g.addEdge(new Vertex<String>("2"), new Vertex<String>("0"));
		g.addEdge(new Vertex<String>("2"), new Vertex<String>("3"));
		g.addEdge(new Vertex<String>("4"), new Vertex<String>("5"));
		g.addEdge(new Vertex<String>("5"), new Vertex<String>("4"));
		//System.out.println(g.hasCycles());

		g = new DAGList<String>();
		g.addVertex("0");
		g.addVertex("1");
		g.addVertex("2");
		//g.addVertex("3");
		g.addVertex("4");
		g.addVertex("5");
		g.addVertex("6");
		g.addEdge(new Vertex<String>("0"), new Vertex<String>("1"));
		g.addEdge(new Vertex<String>("1"), new Vertex<String>("2"));
		g.addEdge(new Vertex<String>("2"), new Vertex<String>("0"));
		g.addEdge(new Vertex<String>("4"), new Vertex<String>("5"));
		//System.out.println(g.getNumberOfConnectedComponents());

		g = new DAGList<String>();
		g.addVertex("0");
		g.addVertex("1");
		g.addVertex("2");
		g.addVertex("3");
		g.addVertex("4");
		g.addEdge(new Vertex<String>("0"), new Vertex<String>("2"));
		g.addEdge(new Vertex<String>("2"), new Vertex<String>("1"));
		g.addEdge(new Vertex<String>("1"), new Vertex<String>("0"));
		g.addEdge(new Vertex<String>("0"), new Vertex<String>("3"));
		g.addEdge(new Vertex<String>("3"), new Vertex<String>("4"));
		g.addEdge(new Vertex<String>("0"), new Vertex<String>("4"));
		//System.out.println(g.scc());
		g.shortestPathUsingBFS(g.getVertex(new Vertex<String>("0")));
		
	}
}
