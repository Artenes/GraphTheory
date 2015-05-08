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
	 * Gera uma instância da classe WeightedAdjacencyMatrixGraph a partir de um arquivo de texto.
	 * @param fileName Diretório do arquivo
	 * @param isDirected Define se o grafo é dirigido ou não
	 * @return Instância da classe WeightedAdjacencyMatrixGraph
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	public static WeightedAdjacencyMatrixGraph<Integer> createGraphFromFile (String fileName, boolean isDirected) throws IOException, NumberFormatException {
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String currentLine = null;
		WeightedAdjacencyMatrixGraph<Integer> graph = null;
		
		//Le-se a primeira linha. Supoe-se que a primeira linha ira conter o número de vértices
		currentLine = reader.readLine();
		graph = new WeightedAdjacencyMatrixGraph<Integer>(Integer.parseInt(currentLine), isDirected);
		
		String vertexAName = null, vertexBName = null, weight = null, rest = null;
		boolean foundA = false, foundB = false;
		Vertex<Integer> vertexA = null, vertexB = null;
		
		while ((currentLine = reader.readLine()) != null) {
			//Cada aresta possui um espaço entre suas vértices
			//Por isso o vértice A vai de 0 até o primeiro espaço
			//E o vértice B vai do primeiro espaço + 1 até o final da linha
			vertexAName = currentLine.substring(0, currentLine.indexOf(" "));
			rest = currentLine.substring(currentLine.indexOf(" ") + 1);
			vertexBName = rest.substring(0, rest.indexOf(" "));
			weight = rest.substring(rest.indexOf(" ") + 1, rest.length());
			
			foundA = foundB = false;
			vertexA = vertexB = null;
			//Para cada novo vértice lido, deve-se verificar se ele já existe no grafo
			for (Vertex<Integer> vertex : graph.vertices()) {
				//Caso o nome do vértice atual seja igual ao nome do vértice lido, então o vértice foi encontrado
				if (vertex.getName().equals(vertexAName)) { foundA = true; vertexA = vertex; }
				if (vertex.getName().equals(vertexBName)) { foundB = true; vertexB = vertex; }
				//Caso ambos os vértices tenhas sido encontrados, o loop será quebrado
				if (foundA && foundB) break;
			}
			//Caso os vértice lido não tenha sido encontrado, um novo será criado para o grafo
			if (!foundA) vertexA = graph.addVertex(vertexAName, Integer.parseInt(vertexAName));
			if (!foundB) vertexB = graph.addVertex(vertexBName, Integer.parseInt(vertexBName));
			//E por fim a aresta é criada
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