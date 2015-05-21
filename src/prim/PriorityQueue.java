package prim;

import graph.Vertex;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

/**
 * Optamos em usar uma List e a classe Comparator para controlar a fila de prioridade
 * Essa é uma implementação valida para o trabalho?
 */
public class PriorityQueue<T> {

	private List<Vertex<T>> priorityQueue;
	private Hashtable<Vertex<T>, PrimAttributes<T>> hashTable;
	
	public PriorityQueue(Hashtable<Vertex<T>, PrimAttributes<T>> hashTable) {
		this.hashTable = hashTable;
		Enumeration<Vertex<T>> vertices = hashTable.keys();
		priorityQueue = new ArrayList<Vertex<T>>();
		while(vertices.hasMoreElements()){
			this.priorityQueue.add((Vertex<T>)vertices.nextElement());
		}
		this.order();
	}
	
	public boolean isEmpty() {
		return this.priorityQueue.isEmpty();
	}
	
	public Vertex<T> pull () {
		return this.priorityQueue.remove(0);
	}
	
	public boolean exists (Vertex<T> vertex) {
		return this.priorityQueue.indexOf(vertex) != -1 ? true : false;
	}
	
	public void order(){
		Collections.sort(this.priorityQueue, new Comparator<Vertex<T>>() {
			@Override
			public int compare(Vertex<T> vertexA, Vertex<T> vertexB) {
				if (((PrimAttributes<T>)hashTable.get(vertexA)).getChave() > ((PrimAttributes<T>)hashTable.get(vertexB)).getChave()) {
					return 1;
				} else if (((PrimAttributes<T>)hashTable.get(vertexA)).getChave() < ((PrimAttributes<T>)hashTable.get(vertexB)).getChave()) {
					return -1;
				} else {
					return 0;
				}
				
			}
		});
	}
}