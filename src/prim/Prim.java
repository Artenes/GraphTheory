package prim;

import java.util.Hashtable;

import graph.IndexedVertex;
import graph.Vertex;
import graph.WeightedEdge;
import graph.adjacencylist.WeightedAdjacencyListGraph;

public class Prim<T> {
	
	public WeightedAdjacencyListGraph<T> execute (WeightedAdjacencyListGraph<T> graph, Vertex<T> source) throws Exception {
		
		if (graph.isDirected())
			throw new Exception("O grafo deve ser n�o dirigido para que o algoritmo de Prim seja executado!");
		
		WeightedAdjacencyListGraph<T> minimumSpanningTree = new WeightedAdjacencyListGraph<T>(graph.numberOfVertices(), false);
		Hashtable<Vertex<T>, PrimAttributes<T>> primAttributes = new Hashtable<Vertex<T>, PrimAttributes<T>>();
		PriorityQueue<T> priorityQueue = null;
		boolean hasChanged = false;
		
		//Todos os vertices com chave inifinita e predecessor null
		for (Vertex<T> vertex : graph.vertices()) {
			primAttributes.put(vertex, new PrimAttributes<T>());
		}
		
		//A chave da raiz tem valor m�nimo
		((PrimAttributes<T>)primAttributes.get(source)).setChave(0);
		
		//Todos os v�rtices s�o incluidos na fila de prioridade
		priorityQueue = new PriorityQueue<T>(primAttributes);
		
		//Enquanto a fila de prioridade n�o estiver vazia
		while(!priorityQueue.isEmpty()) {
			
			//Retiro um item da lista de prioridade
			Vertex<T> vertex = priorityQueue.pull();
			
			//Adiciono � �rvore geradora m�nima
			Vertex<T> newVertex = minimumSpanningTree.addVertex(vertex.getName(), vertex.getElement());
			
			//E caso o v�rtice tenha predecessor, iremos criar uma aresta que o conecta ao seu predecessor
			if (((PrimAttributes<T>)primAttributes.get(vertex)).getPredecessor() != null) {
				minimumSpanningTree.addEdge(newVertex, ((PrimAttributes<T>)primAttributes.get(vertex)).getNovoPredecessor(), ((PrimAttributes<T>)primAttributes.get(vertex)).getChave());
			}
			
			//Para cada aresta incidente do v�rtice...
			for (WeightedEdge<T> weightedEdge : graph.adjacencyWeightEdges(((IndexedVertex<T>)vertex).index())) {
				
				//...iremos verificar se o v�rtice v est� na fila e se o peso da aresta � menor que sua chave
				if (priorityQueue.exists(weightedEdge.getVertexV()) && weightedEdge.getWeight() < ((PrimAttributes<T>)primAttributes.get(weightedEdge.getVertexV())).getChave()) {
					((PrimAttributes<T>)primAttributes.get(weightedEdge.getVertexV())).setPredecessor(vertex);
					((PrimAttributes<T>)primAttributes.get(weightedEdge.getVertexV())).setNovoPredecessor(newVertex);
					((PrimAttributes<T>)primAttributes.get(weightedEdge.getVertexV())).setChave(weightedEdge.getWeight());
					hasChanged = true;
				} 
				
			}
			
			//Caso a chave de algum v�rtice tenha sido alterado, a fila ser� ordenada
			if (hasChanged) {
				priorityQueue.order();
				hasChanged = false;
			}
		}
		
		return minimumSpanningTree;
	} 
	
}