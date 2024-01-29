package algoritmi.av10;

import java.util.*;

/*
######
# # ##
# # S#
# # ##
# E #
######
*/


class AdjacencyListGraph<T> {
    private Map<T, Set<T>> adjacencyList;

    public AdjacencyListGraph() {
        this.adjacencyList = new HashMap<>();
    }

    // Add a vertex to the graph
    public void addVertex(T vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            adjacencyList.put(vertex, new HashSet<>());
        }
    }

    // Remove a vertex from the graph
    public void removeVertex(T vertex) {
        // Remove the vertex from all adjacency lists
        for (Set<T> neighbors : adjacencyList.values()) {
            neighbors.remove(vertex);
        }
        // Remove the vertex's own entry in the adjacency list
        adjacencyList.remove(vertex);
    }

    // Add an edge to the graph
    public void addEdge(T source, T destination) {
        addVertex(source);
        addVertex(destination);

        adjacencyList.get(source).add(destination);
        adjacencyList.get(destination).add(source); // for undirected graph
    }

    // Remove an edge from the graph
    public void removeEdge(T source, T destination) {
        if (adjacencyList.containsKey(source)) {
            adjacencyList.get(source).remove(destination);
        }
        if (adjacencyList.containsKey(destination)) {
            adjacencyList.get(destination).remove(source); // for undirected graph
        }
    }

    // Get all neighbors of a vertex
    public Set<T> getNeighbors(T vertex) {
        return adjacencyList.getOrDefault(vertex, new HashSet<>());
    }

    public void DFS(T startVertex) {
        Set<T> visited = new HashSet<>();
        DFSUtil(startVertex, visited);
    }

    private void DFSUtil(T vertex, Set<T> visited) {
        // Mark the current node as visited and print it
        visited.add(vertex);
        System.out.print(vertex + " ");

        // Recur for all the vertices adjacent to this vertex
        for (T neighbor : getNeighbors(vertex)) {
            if (!visited.contains(neighbor)) {
                DFSUtil(neighbor, visited);
            }
        }
    }


    public void DFSNonRecursive(T startVertex, T endVertex) {
        Set<T> visited = new HashSet<>();
        Stack<T> stack = new Stack<>();

        stack.push(startVertex);

        while (!stack.isEmpty() && stack.peek() != endVertex) {
            T vertex = stack.pop();
            if (!visited.contains(vertex)) {
                visited.add(vertex);
//                System.out.print(vertex + " ");
                for (T neighbor : getNeighbors(vertex)) {
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }

        Stack<Integer> path = new Stack<>();
        while (!stack.isEmpty()) {
            path.push(path.pop());
        }
        System.out.println(path);
    }

    public void BFS(T startVertex) {
        Set<T> visited = new HashSet<>();
        Queue<T> queue = new LinkedList<>();

        visited.add(startVertex);
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            T vertex = queue.poll();
            System.out.print(vertex + " ");

            for (T neighbor : getNeighbors(vertex)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }

    public void findPath(T startVertex, T endVertex) {
        Set<T> visited = new HashSet<>();
        Stack<T> invertedPath = new Stack<>();
        visited.add(startVertex);
        invertedPath.push(startVertex);

        while (!invertedPath.isEmpty() && !invertedPath.peek().equals(endVertex)) {
            T currentVertex = invertedPath.peek();
            T tmp = currentVertex;

            for (T vertex : getNeighbors(currentVertex)) {
                tmp = vertex;
                if (!visited.contains(vertex)) {
                    break;
                }
            }

            if (!visited.contains(tmp)) {
                visited.add(tmp);
                invertedPath.push(tmp);
            } else {
                invertedPath.pop();
            }
        }

        Stack<T> path = new Stack<>();
        while (!invertedPath.isEmpty()) {
            path.push(invertedPath.pop());
        }
        while (!path.isEmpty()) {
            System.out.println(path.pop());
        }
    }

    @Override
    public String toString() {
        String ret = new String();
        for (Map.Entry<T, Set<T>> vertex : adjacencyList.entrySet())
            ret += vertex.getKey() + ": " + vertex.getValue() + "\n";
        return ret;
    }

}

public class Maze {
    public int start;
    public int end;
    public AdjacencyListGraph graph;
    Map<String, Integer> map;   //1,1 -> 0

    public Maze() {
        map = new HashMap<>();
    }

    private void generateGraph(int rows, int columns, String[] in) {
        int total = 0;

        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < columns - 1; j++) {
                if (in[i].charAt(j) != '#') {
                    String key = i + "," + j;
                    if (in[i].charAt(j) == 'S') start = total;
                    if (in[i].charAt(j) == 'E') end = total;
                    map.put(key, total++);

                }
            }
        }
        graph = new AdjacencyListGraph<String>();

        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < columns; j++) {
                if (in[i].charAt(j) != '#') {
                    if (in[i].charAt(j - 1) != '#') {
                        String x = i + "," + j;
                        String y = i + "," + (j - 1);
                        graph.addEdge(map.get(x), map.get(y));
                    }
                    if (in[i].charAt(j + 1) != '#') {
                        String x = i + "," + j;
                        String y = i + "," + (j + 1);
                        graph.addEdge(map.get(x), map.get(y));
                    }
                    if (in[i - 1].charAt(j) != '#') {
                        String x = i + "," + j;
                        String y = (i - 1) + "," + j;
                        graph.addEdge(map.get(x), map.get(y));
                    }
                    if (in[i + 1].charAt(j) != '#') {
                        String x = i + "," + j;
                        String y = (i + 1) + "," + j;
                        graph.addEdge(map.get(x), map.get(y));
                    }
                }

            }
        }
    }


    private void findPath() {
        graph.DFSNonRecursive(start, end);
    }


    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String tmp = sc.nextLine();
        String[] parts = tmp.split(",");

        int m = Integer.parseInt(parts[0]);
        int n = Integer.parseInt(parts[1]);

        String[] lines = new String[m];

        AdjacencyListGraph<String> mazeGraph = new AdjacencyListGraph<>();

        String startVertex = "";
        String endVertex = "";

        for (int i = 0; i < m; i++) {
            lines[i] = sc.nextLine();

            for (int j = 0; j < n; j++) {
                if (lines[i].charAt(j) != '#') {
                    mazeGraph.addVertex(i + "," + j);

                    if (lines[i].charAt(j) == 'S') {
                        startVertex = i + "," + j;
                    } else if (lines[i].charAt(j) == 'E') {
                        endVertex = i + "," + j;
                    }

                    if (i > 0 && lines[i - 1].charAt(j) != '#') {
                        mazeGraph.addEdge((i - 1) + "," + j, i + "," + j);
                    }

                    if (j > 0 && lines[i].charAt(j - 1) != '#') {
                        mazeGraph.addEdge(i + "," + (j - 1), i + "," + j);
                    }
                }
            }
        }
        sc.close();
//        System.out.println(mazeGraph);
        mazeGraph.findPath(startVertex, endVertex);
    }

}
