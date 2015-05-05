package graph.adjacencymatrix;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import graph.Graph;
import graph.IndexedVertex;
import graph.Vertex;

public class AdjacecyMatrixGraph<T> implements Graph<T> {

	protected boolean directed;
	protected int numberOfVertices;
	protected int numberOfEdges;
	protected IndexedVertex<T>[] vertices;
	protected double[][] adj;
	
	public AdjacecyMatrixGraph(int maxVertices, boolean directed) {
		this.directed = directed;
		numberOfVertices = 0;
		numberOfEdges = 0;
		vertices = new IndexedVertex[maxVertices];
		adj = new double[maxVertices][maxVertices];
		for (int i = 0; i < maxVertices; i++) {
			for (int j = 0; j < maxVertices; j++) {
				adj[i][j] = Double.NaN;
			}
		}
	}
	
	@Override
	public Vertex<T> addVertex(String name, T element) {
		int index = numberOfVertices++;
		vertices[index] = new IndexedVertex<T>(name, element, index);
		return vertices[index];
	}

	@Override
	public Vertex<T> getVertex(int index) {
		return vertices[index];
	}

	@Override
	public void addEdge(int u, int v) {
		adj[u][v] = 1.0;
		if (!directed) {
			adj[v][u] = 1.0;
		}
		numberOfEdges++;
	}

	@Override
	public void addEdge(Vertex<T> u, Vertex<T> v) {
		addEdge(((IndexedVertex<T>)u).index(), ((IndexedVertex<T>)v).index());
	}

	@Override
	public int numberOfVertices() {
		return numberOfVertices;
	}

	@Override
	public int numberOfEdges() {
		return numberOfEdges;
	}

	@Override
	public boolean isDirected() {
		return directed;
	}

	public boolean edgeExists(int u, int v) {
		return !Double.isNaN(adj[u][v]);
	}
	
	public boolean edgeExists(Vertex<T> u, Vertex<T> v) {
		return edgeExists(((IndexedVertex<T>)u).index(), ((IndexedVertex<T>)v).index());
	}
	
	public Iterable<Vertex<T>> vertices() {
		return new VertexIterator();
	}
	
	public Iterable<Vertex<T>> adjacentVertices(int u){
		return new AdjacentVertexIterator(u);
	}
	
	/**
	 * Gera uma inst�ncia da classe AdjacencyMatrixGraph a partir de um arquivo de texto.
	 * @param fileName Diret�rio do arquivo
	 * @param isDirected Define se o grafo � dirigido ou n�o
	 * @return inst�ncia da classe AdjacencyMatrixGraph
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	public static AdjacecyMatrixGraph<Integer> createGraphFromFile (String fileName, boolean isDirected) throws IOException, NumberFormatException {
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String currentLine = null;
		AdjacecyMatrixGraph<Integer> graph = null;
		
		//Le-se a primeira linha. Supoe-se que a primeira linha ira conter o n�mero de v�rtices
		currentLine = reader.readLine();
		graph = new AdjacecyMatrixGraph<Integer>(Integer.parseInt(currentLine), isDirected);
		
		String vertexAName = null, vertexBName = null;
		boolean foundA = false, foundB = false;
		Vertex<Integer> vertexA = null, vertexB = null;
		
		while ((currentLine = reader.readLine()) != null) {
			//Cada aresta possui um espa�o entre suas v�rtices
			//Por isso o v�rtice A vai de 0 at� o primeiro espa�o
			//E o v�rtice B vai do primeiro espa�o + 1 at� o final da linha
			vertexAName = currentLine.substring(0, currentLine.indexOf(" "));
			vertexBName = currentLine.substring(currentLine.indexOf(" ") + 1, currentLine.length());
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
			graph.addEdge(vertexA, vertexB);
		}
		reader.close();
		
		return graph;		
	}
	
	public class VertexIterator implements Iterator<Vertex<T>>, Iterable<Vertex<T>> {

		protected int lastVisited;
		
		public VertexIterator() {
			lastVisited = -1;
		}
		
		@Override
		public Iterator<Vertex<T>> iterator() {
			return this;
		}

		@Override
		public boolean hasNext() {
			return lastVisited < numberOfVertices - 1;
		}

		@Override
		public Vertex<T> next() {
			return vertices[++lastVisited];
		}
		
	}
	
	public class AdjacentVertexIterator implements Iterator<Vertex<T>>, Iterable<Vertex<T>> {

		protected int current;
		protected int u;
		
		public AdjacentVertexIterator(int u) {
			this.u = u;
			current = -1;
		}
		
		@Override
		public Iterator<Vertex<T>> iterator() {
			return this;
		}

		@Override
		public boolean hasNext() {
			int v = current + 1;
			while(v < numberOfVertices && !edgeExists(u, v))
				v++;
			return v < numberOfVertices;
		}

		@Override
		public Vertex<T> next() {
			current++;
			while(!edgeExists(u, current))
				current++;
			return vertices[current];
		}	
	}
	
}