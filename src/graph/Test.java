package graph;

import graph.adjacencylist.AdjacencyListGraph;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Criando um grafo
		AdjacencyListGraph<Integer> g = new AdjacencyListGraph<Integer>(5, true);
		
		//Adicionando os vértices
		g.addVertex("0", 0);
		g.addVertex("1", 1);
		g.addVertex("2", 2);
		g.addVertex("3", 3);
		g.addVertex("4", 4);
		
		//Adicionando as arestas
		g.addEdge(0, 1);
		g.addEdge(1, 3);
		g.addEdge(1, 4);
		g.addEdge(2, 3);
		g.addEdge(3, 2);
		g.addEdge(4, 4);
		
		//Imprimir vértices adjacentes
		for (Vertex<Integer> vertice : g.vertices()) {
			System.out.println(vertice.getElement());
			for (Vertex<Integer> verticeAdjacente : g.adjacentVertices(vertice)) {
				System.out.println("   ->" + verticeAdjacente.getElement());
			}
		}	
	}
}
