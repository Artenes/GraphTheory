
package graph;

public class WeightedEdge<T> extends Edge<T>{
    protected double weight;

    public WeightedEdge(Vertex<T> u, Vertex<T> v, double weight) {
        super(u, v);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

       
}