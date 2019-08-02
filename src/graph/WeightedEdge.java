package graph;

public class WeightedEdge extends Edge implements Comparable<WeightedEdge> {
    private double weight;

    WeightedEdge(int u, int v, double weight){
        super(u, v);
        this.weight = weight;
    }

    public double getWeight(){
        return weight;
    }

    @Override
    public int compareTo(WeightedEdge edge){
        if (weight > edge.weight){
            return 1;
        } else if (weight == edge.weight){
            return 0;
        } else {
            return -1;
        }
    }

}
