package graph;

public interface Graph<T> {

	public Vertex<T> addVertex(String name, T element);
	public Vertex<T> getVertex(int index);
	public void addEdge(int u, int v);
	public void addEdge(Vertex<T> u, Vertex<T> v);
	public int numberOfVertices();
	public int numberOfEdges();
	public boolean isDirected();
	
}
