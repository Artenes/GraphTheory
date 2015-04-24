package graph.adjacencylist;

import graph.Edge;
import graph.IndexedVertex;
import graph.Vertex;

public class AdjacencyList<T> {
	
	private IndexedVertex<T> vertex;
	private NodeEdge<T> head;
	private int size;
	
	public AdjacencyList(IndexedVertex<T> v) {
		vertex = v;
		head = null;
		size = 0;
	}
	
	public Vertex<T> vertex() {
		return vertex;
	}
	
	public void setHead (NodeEdge<T> h) {
		head = h;
	}
	
	public NodeEdge<T> getHead() {
		return head;
	}
	
	public void addEdge (Edge<T> e) {
		NodeEdge<T> ne = new NodeEdge<T>(e, getHead());
		setHead(ne);
		size++;
	}
}
