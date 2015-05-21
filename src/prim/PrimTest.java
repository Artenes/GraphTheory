package prim;

import graph.adjacencylist.WeightedAdjacencyListGraph;

public class PrimTest {

	public static void main(String[] args) {

		//Criando um grafo
		WeightedAdjacencyListGraph<String> g = new WeightedAdjacencyListGraph<String>(6, false);

		//Adicionando os vértices
		g.addVertex("A", "A"); //0
		g.addVertex("B", "B"); //1
		g.addVertex("C", "C"); //2
		g.addVertex("D", "D"); //3
		g.addVertex("E", "E"); //4
		g.addVertex("F", "F"); //5

		//Adicionando as arestas
		g.addEdge(0, 1, 7);
		g.addEdge(0, 4, 3);
		g.addEdge(1, 4, 5);
		g.addEdge(1, 2, 9);
		g.addEdge(1, 5, 6);
		g.addEdge(2, 5, 2);
		g.addEdge(2, 3, 3);
		g.addEdge(3, 5, 4);
		
		try {
			WeightedAdjacencyListGraph<String> arvoreMinima = new Prim<String>().execute(g, g.getVertex(0));
			System.out.println(arvoreMinima);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}