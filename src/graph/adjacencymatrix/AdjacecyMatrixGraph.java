package graph.adjacencymatrix;

import java.util.Iterator;

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
	
	public Iterable<Vertex<T>> vertices() {
		return new VertexIterator();
	}
	
	public Iterable<Vertex<T>> adjacentVertices(int u){
		return new AdjacentVertexIterator(u);
	}
	
	public class VertexIterator implements Iterator<Vertex<T>>, Iterable<Vertex<T>> {

		protected int lastVisited;
		
		public VertexIterator() {
			lastVisited = -1;
		}
		
		@Override
		public Iterator<Vertex<T>> iterator() {
			return this;
		}

		@Override
		public boolean hasNext() {
			return lastVisited < numberOfVertices - 1;
		}

		@Override
		public Vertex<T> next() {
			return vertices[++lastVisited];
		}
		
	}
	
	public class AdjacentVertexIterator implements Iterator<Vertex<T>>, Iterable<Vertex<T>> {

		protected int current;
		protected int u;
		
		public AdjacentVertexIterator(int u) {
			this.u = u;
			current = -1;
		}
		
		@Override
		public Iterator<Vertex<T>> iterator() {
			return this;
		}

		@Override
		public boolean hasNext() {
			int v = current + 1;
			while(v < numberOfVertices && !edgeExists(u, v))
				v++;
			return v < numberOfVertices;
		}

		@Override
		public Vertex<T> next() {
			current++;
			while(!edgeExists(u, current))
				current++;
			return vertices[current];
		}	
	}	
}