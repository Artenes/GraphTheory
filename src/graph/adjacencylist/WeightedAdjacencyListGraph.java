package graph.adjacencylist;

import graph.IndexedVertex;
import graph.Vertex;
import graph.WeightedEdge;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

public class WeightedAdjacencyListGraph<T> extends AdjacencyListGraph<T> {

	public WeightedAdjacencyListGraph(int maxVertices, boolean directed) {
		super(maxVertices, directed);
	}

	private int vertexIndex(Vertex<T> u) {
		return ((IndexedVertex<T>) u).index();
	}

	@Override
	public void addEdge(int u, int v) {
		addEdge(u, v, 0);
	}

	public void addEdge(int u, int v, double weight) {
		WeightedEdge<T> e = new WeightedEdge<>(getVertex(u), getVertex(v), weight);
		adj[u].addEdge(e);

		if (!directed) {
			e = new WeightedEdge<T>(getVertex(v), getVertex(u), weight);
			adj[v].addEdge(e);
		}
		numberOfEdges++;
	}

	public void addEdge(Vertex<T> u, Vertex<T> v, double weight) {
		addEdge(vertexIndex(u), vertexIndex(v), weight);
	}

	public Iterable<WeightedEdge<T>> weightEdges() {
		return new WeightedEdgeIterator();
	}

	public Iterable<WeightedEdge<T>> adjacencyWeightEdges(int indice) {
		return new AdjacencyWeightedEdgeIterator(indice);
	}
	
	public class WeightedEdgeIterator implements Iterable<WeightedEdge<T>>, Iterator<WeightedEdge<T>> {

		private int visitedEdges;
		private int currentVertexIndex;
		private NodeEdge<T> currentNodeEdge;
		private int actualNumberOfEdges;

		public WeightedEdgeIterator() {
			currentVertexIndex = -1;
			visitedEdges = 0;
			currentNodeEdge = null;

			if (isDirected()) {
				actualNumberOfEdges = numberOfEdges();
			} else {
				actualNumberOfEdges = 2 * numberOfEdges();
			}
		}

		@Override
		public boolean hasNext() {
			return visitedEdges < actualNumberOfEdges;
		}

		@Override
		public WeightedEdge<T> next() {
			if (currentNodeEdge != null) {
				currentNodeEdge = currentNodeEdge.next;
			}
			if (currentNodeEdge == null) {
				while (adj[++currentVertexIndex].getHead() == null);
				currentNodeEdge = adj[currentVertexIndex].getHead();
			}
			visitedEdges++;
			return (WeightedEdge<T>) currentNodeEdge.getEdge();
		}

		@Override
		public Iterator<WeightedEdge<T>> iterator() {
			return this;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
		}
	}

	public class AdjacencyWeightedEdgeIterator implements Iterable<WeightedEdge<T>>, Iterator<WeightedEdge<T>> {
		protected NodeEdge<T> current;
		protected int index;

		public AdjacencyWeightedEdgeIterator (int i){
			current = null;
			index = i;
		}

		@Override
		public boolean hasNext() {
			if (current == null) {
				return adj[index].getHead() != null;
			} else {
				return current.getNext() != null;
			}
		}

		@Override
		public WeightedEdge<T> next() {
			if (current == null) {
				current = adj[index].getHead();
			} else {
				current =  current.getNext();
			}
			return (WeightedEdge<T>) current.getEdge();
		}

		@Override
		public Iterator<WeightedEdge<T>> iterator() {
			return this;
		}

		@Override
		public void remove() {
			//nada
		}
	}

	@Override
	public String toString() {
		String dotstring = "";
		String tipoaresta = "";

		if (this.directed) {
			dotstring += "digraph wGraph{\n";
			tipoaresta += "->";
		} else {
			dotstring += "graph wGraph{\n";
			tipoaresta += "--";
		}

		for (WeightedEdge<T> verticeAdjacente : this.weightEdges()) {
			dotstring += verticeAdjacente.getVertexU() + " " + tipoaresta + " " + verticeAdjacente.getVertexV() + " [style=bold,label=\"" + verticeAdjacente.getWeight() + "\"]" + ";\n";
		}

		dotstring += "}";
		return dotstring;
	}

	public void exportToDotFile(String filename) {
		try {
			PrintWriter pw = new PrintWriter(new FileOutputStream(filename + ".dot"));
			pw.write(this.toString());
			pw.flush();
			pw.close();
		} catch (Exception e) {

		}
	}

	public static WeightedAdjacencyListGraph<Integer> gerarGrafoPonderadoPorArquivo(String nomeArquivo, boolean isDirected) throws IOException, NumberFormatException {
		FileReader arquivo = new FileReader(nomeArquivo);
		BufferedReader lerArquivo = new BufferedReader(arquivo);
		String linha = null;
		linha = lerArquivo.readLine();
		int maxVertices = Integer.parseInt(linha);
		WeightedAdjacencyListGraph<Integer> grafo = new WeightedAdjacencyListGraph<Integer>(maxVertices, isDirected);

		String x = null, y = null, peso = null, aux = null;
		boolean xExiste = false, yExiste = false;
		Vertex<Integer> verticeX = null, verticeY = null;

		while ((linha = lerArquivo.readLine()) != null) {
			x = linha.substring(0, linha.indexOf(" "));
			aux = linha.substring(linha.indexOf(" ") + 1);
			y = aux.substring(0, aux.indexOf(" "));
			peso = aux.substring(aux.indexOf(" ") + 1, aux.length());

			xExiste = yExiste = false;
			verticeX = verticeY = null;

			for (Vertex<Integer> vertex : grafo.vertices()) {
				if (vertex.getName().equals(x)) {
					xExiste = true;
					verticeX = vertex;
				}
				if (vertex.getName().equals(y)) {
					yExiste = true;
					verticeY = vertex;
				}

				if (xExiste && yExiste) {
					break;
				}
			}

			if (!xExiste) {
				verticeX = grafo.addVertex(x, Integer.parseInt(x));
			}
			if (!yExiste) {
				verticeY = grafo.addVertex(y, Integer.parseInt(y));
			}
			grafo.addEdge(verticeX, verticeY, Double.parseDouble(peso));
			//grafo.addEdge(Integer.parseInt(x), Integer.parseInt(y), Double.parseDouble(peso));
		}
		lerArquivo.close();
		return grafo;
	}

}
