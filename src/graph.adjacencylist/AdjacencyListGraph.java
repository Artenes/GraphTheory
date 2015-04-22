package graph.adjacencylist;

import java.util.Iterator;

import graph.Edge;
import graph.Graph;
import graph.IndexedVertex;
import graph.Vertex;

public class AdjacencyListGraph<T> implements Graph<T> {

	protected boolean directed;
	protected int numberOfVertices;
	protected int numberOfEdges;
	protected AdjacencyList<T>[] adj;
	
	@SuppressWarnings("unchecked")
	public AdjacencyListGraph(int maxVertices, boolean directed) {
		this.directed = directed;
		this.numberOfVertices = 0;
		this.numberOfEdges = 0;
		this.adj = new AdjacencyList[maxVertices];
	}
	
	@Override
	public Vertex<T> addVertex(String name, T element) {
		IndexedVertex<T> v = new IndexedVertex<T>(name, element, numberOfVertices++);
		adj[v.index()] = new AdjacencyList<T>(v);
		return v;
	}

	@Override
	public Vertex<T> getVertex(int index) {
		return adj[index].vertex();
	}

	private int vertexIndex (Vertex<T> u) {
		return ((IndexedVertex<T>)u).index();
	}
	
	@Override
	public void addEdge(int u, int v) {
		Edge<T> e = new Edge<T>(getVertex(u), getVertex(v));
		adj[u].addEdge(e);
		if (!directed) {
			e = new Edge<T>(getVertex(v), getVertex(u));
			adj[v].addEdge(e);
		}
		numberOfEdges++;

	}

	@Override
	public void addEdge(Vertex<T> u, Vertex<T> v) {
		addEdge(vertexIndex(u), vertexIndex(v));
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

	public Iterable<Vertex<T>> vertices() {
		return new VertexIterator();
	}
	
	public Iterable<Vertex<T>> adjacentVertices(int index) {
		return new AdjacentVerticesIterator(index);
	}
	
	public Iterable<Vertex<T>> adjacentVertices (Vertex<T> v) {
		return adjacentVertices(vertexIndex(v));
	}
	
	public class VertexIterator implements Iterator<Vertex<T>>, Iterable<Vertex<T>> {

		protected int lastVertexVisited;
		
		public VertexIterator() {
			lastVertexVisited = -1;
		}
		
		@Override
		public Iterator<Vertex<T>> iterator() {
			return this;
		}

		@Override
		public boolean hasNext() {
			return lastVertexVisited < numberOfVertices -1;
		}

		@Override
		public Vertex<T> next() {
			return adj[++lastVertexVisited].vertex();
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub

		}
	}
	
	public class AdjacentVerticesIterator implements Iterator<Vertex<T>>, Iterable<Vertex<T>> {
		
		protected NodeEdge<T> current;
		protected int index;
		
		public AdjacentVerticesIterator(int i){
			current = null;
			index = i;
		}

		@Override
		public boolean hasNext() {
			if (current == null) {
				return adj[index].getHead() != null;
			} else {
				return current.getNext() != null;
			}
		}
		
		@Override
		public Vertex<T> next() {
			if (current == null) {
				current = adj[index].getHead();
			} else {
				current =  current.getNext();
			}
			return current.getEdge().getVertexV();
		}
		
		@Override
		public Iterator<Vertex<T>> iterator() {
			return this;
		}

		@Override
		public void remove() {
			//nada
		}
		
	}
	
}
