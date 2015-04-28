package graph.adjacencymatrix;

import graph.Graph;
import graph.IndexedVertex;
import graph.Vertex;

public class AdjacecyMatrixGraph<T> implements Graph<T> {

	protected boolean directed;
	protected int numberOfVertices;
	protected int numberOfEdges;
	protected IndexedVertex<T>[] vertices;
	protected double[][] adj;
	
	public AdjacecyMatrixGraph(int maxVertices, boolean directed) {
		this.directed = directed;
		numberOfVertices = 0;
		numberOfEdges = 0;
		vertices = new IndexedVertex[maxVertices];
		adj = new double[maxVertices][maxVertices];
		for (int i = 0; i < maxVertices; i++) {
			for (int j = 0; j < maxVertices; j++) {
				adj[i][j] = Double.NaN;
			}
		}
	}
	
	@Override
	public Vertex<T> addVertex(String name, T element) {
		int index = numberOfVertices++;
		vertices[index] = new IndexedVertex<T>(name, element, index);
		return vertices[index];
	}

	@Override
	public Vertex<T> getVertex(int index) {
		return vertices[index];
	}

	@Override
	public void addEdge(int u, int v) {
		adj[u][v] = 1.0;
		if (!directed) {
			adj[v][u] = 1.0;
		}
		numberOfEdges++;
	}

	@Override
	public void addEdge(Vertex<T> u, Vertex<T> v) {
		addEdge(((IndexedVertex<T>)u).index(), ((IndexedVertex<T>)v).index());
	}

	@Override
	public int numberOfVertices() {
		return numberOfVertices;
	}

	@Override
	public int numberOfEdges() {
		return numberOfEdges;
	}

	@Override
	public boolean isDirected() {
		return directed;
	}

	public boolean edgeExists(int u, int v) {
		return !Double.isNaN(adj[u][v]);
	}
	
	public boolean edgeExists(Vertex<T> u, Vertex<T> v) {
		return edgeExists(((IndexedVertex<T>)u).index(), ((IndexedVertex<T>)v).index());
	}
}
