package prim;

import graph.Vertex;

public class PrimAttributes<T> {

	private double chave;
	private Vertex<T> predecessor;
	/**
	 * Esse novo predecessor surgiu da necessidade de se armazenar um novo
	 * v�rtice (com um novo �ndice) que foi ferado pelo grafo que 
	 * esta armazenando os v�rtices para formar a �rvore geradora m�nima 
	 */
	private Vertex<T> novoPredecessor;
	
	public PrimAttributes() {
		this.chave = Double.POSITIVE_INFINITY;
		this.predecessor = null;
	}
	
	public void setChave(double chave) {
		this.chave = chave;
	}
	
	public Vertex<T> getPredecessor () {
		return this.predecessor;
	}
	
	public double getChave(){
		return this.chave;
	}
	
	public void setPredecessor(Vertex<T> vertex){
		this.predecessor = vertex;
	}

	public Vertex<T> getNovoPredecessor() {
		return novoPredecessor;
	}

	public void setNovoPredecessor(Vertex<T> novoPredecessor) {
		this.novoPredecessor = novoPredecessor;
	}
	
}