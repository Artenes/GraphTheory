package graph.adjacencymatrix;

import graph.IndexedVertex;
import graph.Vertex;

public class WeightedAdjacencyMatrixGraph<T> extends AdjacecyMatrixGraph<T> {

	public WeightedAdjacencyMatrixGraph(int maxVertices, boolean directed) {
		super(maxVertices, directed);
	}

	@Override
	public void addEdge(int u, int v) {
		addEdge(u, v, 0);
	}

	@Override
	public void addEdge(Vertex<T> u, Vertex<T> v) {
		addEdge(vertexIndex(u), vertexIndex(v), 0);
	}
	
	public void addEdge(int u, int v, double weight){
		adj[u][v] = weight;
		if (!directed)
			adj[u][v] = weight;
		numberOfEdges++;
	}
	
	public void addEdge(Vertex<T> u, Vertex<T> v, double weight){
		addEdge(vertexIndex(u), vertexIndex(v), weight);
	}
	
	private int vertexIndex(Vertex<T> u){
		return ((IndexedVertex<T>)u).index();
	}
	
	public double edgeWeight (int u, int v) {
		return adj[u][v];
	}
	
	public double edgeWeight (Vertex<T> u, Vertex<T> v) {
		return adj[vertexIndex(u)][vertexIndex(v)];
	}
}