package graph;

public class IndexedVertex<T> extends Vertex<T> {

	public static final int UNKNOWN_INDEX = -1;
	protected int index;
	
	public IndexedVertex(String name, T element) {
		this(name, element, UNKNOWN_INDEX);
	}
	
	public IndexedVertex(String name, T element, int index) {
		super(name, element);
		this.index = index;
	}
	
	public int index() {
		return index;
	}
}
