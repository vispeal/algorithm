package graph;

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public abstract class AbstractGraph<V> implements Graph<V> {

    protected List<V> vertices = new ArrayList<>();
    protected List<List<Edge>> neighbors = new ArrayList<>();

    protected AbstractGraph(){
    }

    protected AbstractGraph(V[] vertices, int[][] edges){
        for(V v: vertices){
            addVertex(v);
        }
        createAdjacencyLists(edges, vertices.length);
    }

    private void createAdjacencyLists(int[][] edges, int length){
        for(int[] edge: edges){
            addEdge(edge[0], edge[1]);
        }
    }

    boolean addEdge(Edge e){
        if(e.getU() < 0 || e.getU() >= vertices.size()){
            return false;
        }
        if(e.getV() < 0 || e.getV() >= vertices.size()){
            return false;
        }
        List<Edge> edges = neighbors.get(e.getU());
        if (!edges.contains(e)) {
            edges.add(e);
            return true;
        }
        return false;
    }

    @Override
    public int getSize(){
        return vertices.size();
    }

    @Override
    public List<V> getVertices(){
        return vertices;
    }

    @Override
    public V getVertex(int index){
        return vertices.get(index);
    }

    public int getIndex(V v){
        return vertices.indexOf(v);
    }

    @Override
    public List<Integer> getNeighbors(int index){
        List<Integer> adjacency = new ArrayList<>();
        for (Edge edge: neighbors.get(index)){
            adjacency.add(edge.getV());
        }
        return adjacency;
    }

    @Override
    public int getDegree(int index){
        return neighbors.get(index).size();
    }

    @Override
    public void printEdges(){
        for(int i=0; i < neighbors.size(); i++){
            System.out.print(getVertex(i) + "(" + i + "): ");
            for(Edge e: neighbors.get(i)){
                System.out.print("(" + getVertex(e.getU()) + ", " + getVertex(e.getV()) + ") ");
            }
            System.out.println();
        }
    }

    @Override
    public void clear(){
        vertices.clear();
        neighbors.clear();
    }

    public boolean addVertex(V v){
        if(!vertices.contains(v)) {
            vertices.add(v);
            neighbors.add(new ArrayList<>());
            return true;
        }
        return false;
    }

    public boolean addEdge(int u, int v){
        Edge edge = new Edge(u, v);
        return addEdge(edge);
    }

    public Tree dfs(int v){
        List<Integer> searchOrder = new ArrayList<>();
        int[] parent = new int[getSize()];
        for(int i=0; i < parent.length; i++){
            parent[i] = -1;
        }
        boolean[] isVisited = new boolean[getSize()];
        dfs(v, parent, searchOrder, isVisited);

        return new Tree(v, parent, searchOrder);
    }

    private void dfs(int u, int[] parent, List<Integer> searchOrder, boolean[] isVisited){
        isVisited[u] = true;
        searchOrder.add(u);
        for(Edge edge: neighbors.get(u)){
            if(!isVisited[edge.getV()]) {
                parent[edge.getV()] = u;
                dfs(edge.getV(), parent, searchOrder, isVisited);
            }
        }
    }

    public Tree bfs(int v){
        List<Integer> searchOrder = new ArrayList<>();
        int[] parent = new int[getSize()];
        for(int i=0; i < parent.length; i++){
            parent[i] = -1;
        }
        boolean[] isVisited = new boolean[getSize()];
        LinkedList<Integer> queue = new LinkedList<>();
        queue.offer(v);
        isVisited[v] = true;

        while(!queue.isEmpty()){
            int u = queue.poll();
            searchOrder.add(u);
            for (Edge edge: neighbors.get(u)){
                if(!isVisited[edge.getV()]){
                    queue.offer(edge.getV());
                    parent[edge.getV()] = u;
                    isVisited[edge.getV()] = true;
                }
            }
        }
        queue.offer(v);
        return new Tree(v, parent, searchOrder);
    }

    public class Tree {
        private int root;
        private int[] parent;
        private List<Integer> searchOrder;

        public Tree(){

        }

        public Tree(int root, int[] parent, List<Integer> searchOrder){
            this.root = root;
            this.parent = parent;
            this.searchOrder = searchOrder;
        }

        public int getRoot(){
            return root;
        }

        public List<Integer> getSearchOrder(){
            return searchOrder;
        }

        public int getParent(int index){
            return parent[index];
        }

        public int getNumberOfVerticesFound(){
            return searchOrder.size();
        }

        public List<V> getPath(int index){
            List<V> path = new ArrayList<>();
            do{
                path.add(getVertex(index));
                index = parent[index];
            }while(index != -1);
            return path;
        }

        public void printPath(int index){
            List<V> path = getPath(index);
            System.out.print("A path from " + getVertex(root) + " to " + getVertex(index) + ": ");
            for(int i = path.size() - 1; i >= 0; i--){
                System.out.print(path.get(i) + " ");
            }
        }

        public void printTree(){
            System.out.println("Root is: " + getVertex(root));
            System.out.print("Edges: ");
            for(int i = 0; i < parent.length; i++){
                if (parent[i] != -1){
                    System.out.print("(" + getVertex(parent[i]) + ", " + getVertex(i) + ")");
                }
                System.out.println();
            }

        }
    }
}
