package graph;

import graph.adjacencylist.AdjacencyListGraph;
import graph.adjacencymatrix.AdjacecyMatrixGraph;
import graph.adjacencymatrix.WeightedAdjacencyMatrixGraph;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println();
		System.out.println("*******************************************************");
		System.out.println("GRAFO NÃO PONDERADO REPRESENTADO COM LISTA DE ADJACÊNCIA");
		System.out.println("*******************************************************");
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
		System.out.println(g);
		
		System.out.println();
		System.out.println("*******************************************************");
		System.out.println("GRAFO NÃO PONDERADO REPRESENTADO COM MATRIZ DE ADJACÊNCIA");
		System.out.println("*******************************************************");
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
		
		//Imprimir vértices adjacentes
		System.out.println(m);
		
		System.out.println();
		System.out.println("*******************************************************");
		System.out.println("GRAFO PONDERADO REPRESENTADO COM MATRIZ DE ADJACÊNCIA");
		System.out.println("*******************************************************");
		//Criando um outro grafo
		WeightedAdjacencyMatrixGraph<Integer> pm = new WeightedAdjacencyMatrixGraph<Integer>(5, false);
		
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
		System.out.println(pm);
		
		System.out.println();
		System.out.println("*******************************************************");
		System.out.println("GRAFO NÃO PONDERADO GERADO POR ARQUIVO");
		System.out.println("*******************************************************");
		AdjacecyMatrixGraph<Integer> grafo = null;
		try {
			grafo = AdjacecyMatrixGraph.createGraphFromFile("GrafoMatriz.txt", true);
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		System.out.println(grafo);
		
		//*********************************
		//*****Exportando arquivo dot******
		//*********************************
		try {
			grafo.exportToDotFile("GrafoNaoPonderado.txt");
		} catch (FileNotFoundException | UnsupportedEncodingException erro) {
			erro.printStackTrace();
		}
		
		
		System.out.println();
		System.out.println("*******************************************************");
		System.out.println("GRAFO PONDERADO GERADO POR ARQUIVO");
		System.out.println("*******************************************************");
		WeightedAdjacencyMatrixGraph<Integer> grafoPond = null;
		try {
			grafoPond = WeightedAdjacencyMatrixGraph.createGraphFromFile("GrafoMatrizPonderado.txt", true);
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		System.out.println(grafoPond);
		
		//*********************************
		//*****Exportando arquivo dot******
		//*********************************
		try {
			grafoPond.exportToDotFile("GrafoPonderado.txt");
		} catch (FileNotFoundException | UnsupportedEncodingException erro) {
			erro.printStackTrace();
		}
	}
}