package graph;

import java.util.List;

public class UnweightedGraph<V> extends AbstractGraph<V> {
    public UnweightedGraph(){

    }

    public UnweightedGraph(V[] vertices, int[][] edges){
        super(vertices, edges);
    }

    public static void testGraph(){
        String[] vertices = {"Seattle", "San Francisco", "Los Angeles",
        "Denver", "Kansas City", "Chicago", "Boston", "New York",
        "Atlanta", "Miami", "Dallas", "Houston"};

        int[][] edges = {
                {0, 1}, {0, 3}, {0, 5},
                {1, 0}, {1, 2}, {1, 3},
                {2, 1}, {2, 3}, {2, 4}, {2, 10},
                {3, 0}, {3, 1}, {3, 2}, {3, 4}, {3, 5},
                {4, 2}, {4, 3}, {4, 5}, {4, 7}, {4, 8}, {4, 10},
                {5, 0}, {5, 3}, {5, 4}, {5, 6}, {5, 7},
                {6, 5}, {6, 7},
                {7, 4}, {7, 5}, {7, 6}, {7, 8},
                {8, 4}, {8, 7}, {8, 9}, {8, 10}, {8, 11},
                {9, 8}, {9, 11},
                {10, 2}, {10, 4}, {10, 8}, {10, 11},
                {11, 8}, {11, 9}, {11, 10}
        };
        Graph<String> graph1 = new UnweightedGraph<>(vertices, edges);
//        System.out.println("The number of vertices in graph1: " + graph1.getSize());
//        System.out.println("The vertex with index 1 is " + graph1.getVertex(1));
//        System.out.println("The index for Miami is " + graph1.getIndex("Miami"));
//        System.out.println("The edges for graph1: ");
//        graph1.printEdges();

//        AbstractGraph<String>.Tree dfs = graph1.dfs(graph1.getIndex("Chicago"));
//
//        List<Integer> searchOrders = dfs.getSearchOrder();
//        System.out.println(dfs.getNumberOfVerticesFound() + " vertices are searched in this DFS order:");
//        for(int i = 0; i < searchOrders.size(); i++){
//            System.out.print(graph1.getVertex(searchOrders.get(i)) + " ");
//        }
//        System.out.println();
//
//        for(int i = 0; i < searchOrders.size(); i++){
//            if(dfs.getParent(i) != -1){
//                System.out.println("parent of " + graph1.getVertex(i) + " is " + graph1.getVertex(dfs.getParent(i)));
//            }
//        }

        AbstractGraph<String>.Tree bfs = graph1.bfs(graph1.getIndex("Chicago"));

        List<Integer> searchOrders = bfs.getSearchOrder();
        System.out.println(bfs.getNumberOfVerticesFound() + " vertices are searched in this DFS order:");
        for(int i = 0; i < searchOrders.size(); i++){
            System.out.print(graph1.getVertex(searchOrders.get(i)) + " ");
        }
        System.out.println();

        for(int i = 0; i < searchOrders.size(); i++){
            if(bfs.getParent(i) != -1){
                System.out.println("parent of " + graph1.getVertex(i) + " is " + graph1.getVertex(bfs.getParent(i)));
            }
        }




    }


}
