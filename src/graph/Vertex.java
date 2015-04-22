package graph;

public class Vertex<T> {
	
	protected String name;
	protected T element;
	
	public Vertex(String name, T element) {
		this.name = name;
		this.element = element;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public T getElement() {
		return element;
	}

	public void setElement(T element) {
		this.element = element;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
