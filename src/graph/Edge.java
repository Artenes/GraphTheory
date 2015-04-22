package graph;

public class Edge<T> {

	protected Vertex<T> u;
	protected Vertex<T> v;
	
	public Edge(Vertex<T> u, Vertex<T> v) {
		this.u = u;
		this.v = v;
	}

	public Vertex<T> getVertexU() {
		return u;
	}

	public void setVertexU(Vertex<T> u) {
		this.u = u;
	}

	public Vertex<T> getVertexV() {
		return v;
	}

	public void setVertexV(Vertex<T> v) {
		this.v = v;
	}
	
	@Override
	public String toString() {
		return "("+u+", "+v+")";
	}
	
}
