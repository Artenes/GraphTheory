package graph.adjacencylist;

import graph.Edge;

public class NodeEdge<T> {

	protected Edge<T> edge;
	protected NodeEdge<T> next;
	
	public NodeEdge(Edge<T> edge) {
		this(edge, null);
	}
	
	public NodeEdge(Edge<T> edge, NodeEdge<T> next) {
		this.edge = edge;
		this.next = next;
	}

	public Edge<T> getEdge() {
		return edge;
	}

	public void setEdge(Edge<T> edge) {
		this.edge = edge;
	}

	public NodeEdge<T> getNext() {
		return next;
	}

	public void setNext(NodeEdge<T> next) {
		this.next = next;
	}
	
}
