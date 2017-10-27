package graphs;

public class VertWeight<T>{
	Vertex<T> vert;
	int weight;
	public VertWeight(Vertex<T> v, int weight){
		this.vert = v;
		this.weight = weight;
	}
	@Override
	public int hashCode(){
		return this.vert.hashCode();
	}
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o){
		if(o instanceof VertWeight) {
			return this.vert.equals(((VertWeight<T>)o).vert);
		}
		return false;
	}
}