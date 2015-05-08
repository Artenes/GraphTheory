package graph.adjacencymatrix;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import graph.IndexedVertex;
import graph.Vertex;

public class WeightedAdjacencyMatrixGraph<T> extends AdjacecyMatrixGraph<T> {

	public WeightedAdjacencyMatrixGraph(int maxVertices, boolean directed) {
		super(maxVertices, directed);
	}

	@Override
	public void addEdge(int u, int v) {
		addEdge(u, v, 0);
	}

	@Override
	public void addEdge(Vertex<T> u, Vertex<T> v) {
		addEdge(vertexIndex(u), vertexIndex(v), 0);
	}
	
	public void addEdge(int u, int v, double weight){
		adj[u][v] = weight;
		if (!directed)
			adj[v][u] = weight;
		numberOfEdges++;
	}
	
	public void addEdge(Vertex<T> u, Vertex<T> v, double weight){
		addEdge(vertexIndex(u), vertexIndex(v), weight);
	}
	
	private int vertexIndex(Vertex<T> u){
		return ((IndexedVertex<T>)u).index();
	}
	
	public double edgeWeight (int u, int v) {
		return adj[u][v];
	}
	
	public double edgeWeight (Vertex<T> u, Vertex<T> v) {
		return adj[vertexIndex(u)][vertexIndex(v)];
	}
	
	/**
	 * Gera uma inst�ncia da classe WeightedAdjacencyMatrixGraph a partir de um arquivo de texto.
	 * @param fileName Diret�rio do arquivo
	 * @param isDirected Define se o grafo � dirigido ou n�o
	 * @return Inst�ncia da classe WeightedAdjacencyMatrixGraph
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	public static WeightedAdjacencyMatrixGraph<Integer> createGraphFromFile (String fileName, boolean isDirected) throws IOException, NumberFormatException {
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String currentLine = null;
		WeightedAdjacencyMatrixGraph<Integer> graph = null;
		
		//Le-se a primeira linha. Supoe-se que a primeira linha ira conter o n�mero de v�rtices
		currentLine = reader.readLine();
		graph = new WeightedAdjacencyMatrixGraph<Integer>(Integer.parseInt(currentLine), isDirected);
		
		String vertexAName = null, vertexBName = null, weight = null, rest = null;
		boolean foundA = false, foundB = false;
		Vertex<Integer> vertexA = null, vertexB = null;
		
		while ((currentLine = reader.readLine()) != null) {
			//Cada aresta possui um espa�o entre suas v�rtices
			//Por isso o v�rtice A vai de 0 at� o primeiro espa�o
			//E o v�rtice B vai do primeiro espa�o + 1 at� o final da linha
			vertexAName = currentLine.substring(0, currentLine.indexOf(" "));
			rest = currentLine.substring(currentLine.indexOf(" ") + 1);
			vertexBName = rest.substring(0, rest.indexOf(" "));
			weight = rest.substring(rest.indexOf(" ") + 1, rest.length());
			
			foundA = foundB = false;
			vertexA = vertexB = null;
			//Para cada novo v�rtice lido, deve-se verificar se ele j� existe no grafo
			for (Vertex<Integer> vertex : graph.vertices()) {
				//Caso o nome do v�rtice atual seja igual ao nome do v�rtice lido, ent�o o v�rtice foi encontrado
				if (vertex.getName().equals(vertexAName)) { foundA = true; vertexA = vertex; }
				if (vertex.getName().equals(vertexBName)) { foundB = true; vertexB = vertex; }
				//Caso ambos os v�rtices tenhas sido encontrados, o loop ser� quebrado
				if (foundA && foundB) break;
			}
			//Caso os v�rtice lido n�o tenha sido encontrado, um novo ser� criado para o grafo
			if (!foundA) vertexA = graph.addVertex(vertexAName, Integer.parseInt(vertexAName));
			if (!foundB) vertexB = graph.addVertex(vertexBName, Integer.parseInt(vertexBName));
			//E por fim a aresta � criada
			graph.addEdge(vertexA, vertexB, Double.parseDouble(weight));
		}
		reader.close();
		
		return graph;		
	}
	
	@Override
	public String toString() {
		String resultado = this.directed ? "digraph Grafo {\n" : "graph Grafo {\n";
		String aresta = this.directed ? " -> " : " -- ";
		
		for (Vertex<T> vertice : this.vertices()) {
			for (Vertex<T> verticeAdjacente : this.adjacentVertices(((IndexedVertex<T>)vertice).index())) {
				resultado += "\t" + vertice.getName() + aresta + verticeAdjacente.getName() + "[label=\"" + this.edgeWeight(vertice, verticeAdjacente) + "\"];\n";
			}
		}
		
		resultado += "}";
		return resultado;
	}
	
}