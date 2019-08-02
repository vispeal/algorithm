package graph;

import java.util.*;

public class WeightedGraph<V> extends AbstractGraph<V>{
    public WeightedGraph(){

    }

    public WeightedGraph(V[] vertices, int[][] edges){
        this.vertices = Arrays.asList(vertices);
        for(int i=0; i < edges.length; i++){
            neighbors.add(new ArrayList<Edge>());
        }
        for(int i=0; i < edges.length; i++){
            neighbors.get(edges[i][0]).add(new WeightedEdge(edges[i][0],
                    edges[i][1], edges[i][2]));
        }

    }

    public void printWeightedEdges(){
        for(int i=0; i < neighbors.size(); i++){
            System.out.print(getVertex(i) + " (" + i + "): ");
            List<Edge> edges = neighbors.get(i);
            for(Edge edge: edges){
                System.out.print("(" + edge.getU() + ", " + edge.getV() + ", " +
                        ((WeightedEdge)edge).getWeight() + ") ");

            }
            System.out.println();
        }

    }

    public double getWeight(int u, int v){
        for(Edge e: neighbors.get(u)){
            if(e.getU() == u && e.getV() == v){
                return ((WeightedEdge)e).getWeight();
            }
        }
        return 0;
    }

    public void addEdges(int u, int v, double weight){
        neighbors.get(u).add(new WeightedEdge(u, v, weight));
    }

    public class MST extends Tree{
        private double totalWeight;

        public MST(int root, int[] parent, List<Integer> searchOrder, double totalWeight){
            super(root, parent, searchOrder);
            this.totalWeight = totalWeight;
        }

        public double getTotalWeight(){
            return totalWeight;
        }
    }

    public MST getMinimumSpanningTree(){
        return getMinimumSpanningTree(0);
    }

    public MST getMinimumSpanningTree(int index){
        double[] cost = new double[getSize()];
        for(int i=0; i<cost.length; i++){
            cost[i] = Double.POSITIVE_INFINITY;
        }
        cost[index] = 0;
        int[] parent = new int[getSize()];
        parent[index] = -1;
        List<Integer> searchOrder = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        while(set.size() < getSize()){
            int u = -1;
            double min = Double.POSITIVE_INFINITY;
            for(int i=0; i < getSize(); i++){
                if(!set.contains(i) && cost[i] < min){
                    min = cost[i];
                    u = i;
                }
            }

            set.add(u);
            for(Edge edge: neighbors.get(u)){
                if(!set.contains(edge.getV()) && cost[edge.getV()] > ((WeightedEdge)edge).getWeight()){
                    cost[edge.getV()] = ((WeightedEdge)edge).getWeight();
                    parent[edge.getV()] = u;
                }
            }
        }
        double totalWeight = 0;
        for(double weight: cost){
            totalWeight += weight;
        }
        return new MST(index, parent, searchOrder, totalWeight);
    }

    public class ShortestPathTree extends Tree{
        private double[] cost;

        public ShortestPathTree(int source, int[] parent, List<Integer> searchOrder, double[] cost){
            super(source, parent, searchOrder);
            this.cost = cost;
        }

        public double getCost(int v){
            return cost[v];
        }
        public void printAllPaths(){
            System.out.println("All shortest paths from " + getVertex(getRoot())
            + " are: ");
            for(int i=0; i < cost.length; i++){
                printPath(i);
                System.out.println("(cost: " + cost[i] + ")");
            }
        }
    }

    public ShortestPathTree getShortestPathTree(int source){
        List<Integer> searchOrder = new ArrayList<>();
        double[] cost = new double[vertices.size()];
        for(int i = 0; i < cost.length; i++){
            cost[i] = Double.POSITIVE_INFINITY;
        }
        cost[source] = 0;
        int[] parent = new int[vertices.size()];
        parent[source] = -1;
        Set<Integer> set = new HashSet<>();
        while(set.size() < vertices.size()){
            int u = -1;
            double min = Double.POSITIVE_INFINITY;
            for(int i=0; i < cost.length; i++){
                if (!set.contains(i) && cost[i] < min){
                    u = i;
                    min = cost[i];
                }
            }
            set.add(u);
            searchOrder.add(u);
            for(Edge edge: neighbors.get(u)){
                if(!set.contains(edge.getV())){
                    if(cost[edge.getV()] > cost[u] + ((WeightedEdge)edge).getWeight()){
                        cost[edge.getV()] = cost[u] + ((WeightedEdge)edge).getWeight();
                        parent[edge.getV()] = edge.getU();
                    }
                }
            }
        }
        return new ShortestPathTree(source, parent, searchOrder, cost);

    }

    public static void testGraph(){
        String[] vertices = {"Seattle", "San Francisco", "Los Angeles",
                "Denver", "Kansas City", "Chicago", "Boston", "New York",
                "Atlanta", "Miami", "Dallas", "Houston"};

        int[][] edges = {
                {0, 1, 807}, {0, 3, 1331}, {0, 5, 2097},
                {1, 0, 807}, {1, 2, 381}, {1, 3, 1267},
                {2, 1, 381}, {2, 3, 1015}, {2, 4, 1663}, {2, 10, 1435},
                {3, 0, 1331}, {3, 1, 1267}, {3, 2, 1015}, {3, 4, 599}, {3, 5, 1003},
                {4, 2, 1663}, {4, 3, 599}, {4, 5, 533}, {4, 7, 1260}, {4, 8, 864}, {4, 10, 496},
                {5, 0, 2097}, {5, 3, 1003}, {5, 4, 533}, {5, 6, 983}, {5, 7, 787},
                {6, 5, 983}, {6, 7, 214},
                {7, 4, 1260}, {7, 5, 787}, {7, 6, 214}, {7, 8, 888},
                {8, 4, 864}, {8, 7, 888}, {8, 9, 661}, {8, 10, 781}, {8, 11, 810},
                {9, 8, 661}, {9, 11, 1187},
                {10, 2, 1435}, {10, 4, 496}, {10, 8, 781}, {10, 11, 239},
                {11, 8, 810}, {11, 9, 1187}, {11, 10, 239}
        };
        WeightedGraph<String> graph = new WeightedGraph<>(vertices, edges);
//        WeightedGraph<String>.ShortestPathTree tree1 = graph.getShortestPathTree(graph.getIndex("Chicago"));
//        tree1.printAllPaths();
//
//        System.out.println("Shortest path from Houston to Chicago: ");
//        List<String> path = tree1.getPath(graph.getIndex("Houston"));
//        for(String s: path){
//            System.out.print(s + " ");
//        }

        WeightedGraph<String>.MST mst1 = graph.getMinimumSpanningTree();
        System.out.println("Total weight is " + mst1.getTotalWeight());
        mst1.printTree();


    }
}
