package graphs;

public class Vertex<T> {
	public T label;
	public boolean wasVisited;
	public Vertex(T label){
		this.label = label;
		wasVisited = false;
	}
	@Override
	public String toString() {
		return String.valueOf(this.label);
	}
	@Override
	public boolean equals(Object obj){
		if(obj != null && obj instanceof Vertex) {
			return ((Vertex)obj).label == this.label;
		}
		return false;
	}
	@Override
	public int hashCode() {
		return (String.valueOf(this.label).length());
	}
}
