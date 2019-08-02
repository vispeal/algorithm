package graph;

public class Edge {
    private int u;
    private int v;
    public Edge(int u, int v){
        this.u = u;
        this.v = v;
    }

    public int getU(){
        return u;
    }

    public int getV(){
        return v;
    }

    public boolean equals(Object o){
        if (o instanceof Edge) {
            return u == ((Edge) o).u && v == ((Edge) o).v;
        }
        return false;
    }
}
