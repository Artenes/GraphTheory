package graph;

import graph.adjacencylist.AdjacencyListGraph;
import graph.adjacencymatrix.AdjacecyMatrixGraph;
import graph.adjacencymatrix.WeightedAdjacencyMatrixGraph;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Grafo com lista de adjacência:");
		//Criando um grafo
		AdjacencyListGraph<Integer> g = new AdjacencyListGraph<Integer>(5, true);
		
		//Adicionando os vÃ©rtices
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
		
		//Imprimir vÃ©rtices adjacentes
		for (Vertex<Integer> vertice : g.vertices()) {
			System.out.println(vertice.getElement());
			for (Vertex<Integer> verticeAdjacente : g.adjacentVertices(vertice)) {
				System.out.println("   ->" + verticeAdjacente.getElement());
			}
		}
		
		System.out.println("\nGrafo com matriz de adjacência:");
		//Criando um outro grafo
		AdjacecyMatrixGraph<Integer> m = new AdjacecyMatrixGraph<Integer>(5, true);
		
		//Adicionando outros vertices
		m.addVertex("0", 0);
		m.addVertex("1", 1);
		m.addVertex("2", 2);
		m.addVertex("3", 3);
		m.addVertex("4", 4);
		
		//Adicionando as arestas
		m.addEdge(0, 1);
		m.addEdge(1, 3);
		m.addEdge(1, 4);
		m.addEdge(2, 3);
		m.addEdge(3, 2);
		m.addEdge(4, 4);
		
		//Imprimir vÃ©rtices adjacentes
		for (Vertex<Integer> vertice : m.vertices()) {
			System.out.println(vertice.getElement());
			for (Vertex<Integer> verticeAdjacente : m.adjacentVertices(((IndexedVertex<Integer>)vertice).index)) {
				System.out.println("   ->" + verticeAdjacente.getElement());
			}
		}
		
		System.out.println("\nGrafo ponderado com matriz de adjacência:");
		//Criando um outro grafo
		WeightedAdjacencyMatrixGraph<Integer> pm = new WeightedAdjacencyMatrixGraph<Integer>(5, true);
		
		//Adicionando outros vertices
		pm.addVertex("0", 0);
		pm.addVertex("1", 1);
		pm.addVertex("2", 2);
		pm.addVertex("3", 3);
		pm.addVertex("4", 4);
		
		//Adicionando as arestas
		pm.addEdge(0, 1, 34);
		pm.addEdge(1, 3, 45);
		pm.addEdge(1, 4, 17);
		pm.addEdge(2, 3, 90);
		pm.addEdge(3, 2, 22);
		pm.addEdge(4, 4, 66);
		
		//Imprimir vértices adjacentes
		for (Vertex<Integer> vertice : pm.vertices()) {
			System.out.println(vertice.getElement());
			for (Vertex<Integer> verticeAdjacente : pm.adjacentVertices(((IndexedVertex<Integer>)vertice).index)) {
				System.out.println("   ("+pm.edgeWeight(vertice, verticeAdjacente)+")->" + verticeAdjacente.getElement());
			}
		}
	}
}
