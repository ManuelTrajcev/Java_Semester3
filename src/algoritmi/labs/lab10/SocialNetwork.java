package algoritmi.labs.lab10;

import java.util.*;

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


    public void DFSNonRecursive(T startVertex) {
        Set<T> visited = new HashSet<>();
        Stack<T> stack = new Stack<>();

        stack.push(startVertex);
        while (!stack.isEmpty()) {
            T vertex = stack.pop();
            if (!visited.contains(vertex)) {
                visited.add(vertex);
                System.out.print(vertex + " ");
                for (T neighbor : getNeighbors(vertex)) {
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }
    }

    public void BFS(T startVertex, T endVertex) {
        Set<T> visited = new HashSet<>();
        Queue<T> queue = new LinkedList<>();
        Map<T, T> parents = new HashMap<>();
        visited.add(startVertex);
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            T vertex = queue.poll();

            for (T neighbor : getNeighbors(vertex)) {
                if (!visited.contains(neighbor)) {
                    parents.put(neighbor, vertex);
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        findPath(startVertex, endVertex, parents);
    }

    public void findPath(T startVertex, T endVertex, Map<T,T> parents) {
      Stack<T> path = new Stack<>();
      T curr = endVertex;
      path.push(startVertex);

      while (!curr.equals(startVertex)) {
          path.push(curr);
          curr = parents.get(curr);
      }

        System.out.println(path.size()-1);
    }


    public void BFSPathLength(T startVertex, T endVertex) {
        Set<T> visited = new HashSet<>();
        Queue<T> queue = new LinkedList<>();
        int counter = 0;
        visited.add(startVertex);
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            counter++;
            T vertex = queue.poll();
//            System.out.print(vertex + " ");

            for (T neighbor : getNeighbors(vertex)) {
                if (neighbor.equals(endVertex)) {
                    System.out.println(counter);
                    return;
                }
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }

    public void BFSPath(T startVertex, T endVertex) {
        Map<T, T> parentMap = new HashMap<>();
        Set<T> visited = new HashSet<>();
        Queue<T> queue = new LinkedList<>();

        visited.add(startVertex);
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            T currentVertex = queue.poll();

            if (currentVertex.equals(endVertex)) {
                printDistance(parentMap, startVertex, endVertex);
                return;
            }

            for (T neighbor : getNeighbors(currentVertex)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                    parentMap.put(neighbor, currentVertex);
                }
            }
        }

        System.out.println("No path found");
    }

    private void printDistance(Map<T, T> parentMap, T startVertex, T endVertex) {
        Stack<T> path = new Stack<>();
        T currentVertex = endVertex;

        while (currentVertex != null) {
            path.push(currentVertex);
            currentVertex = parentMap.get(currentVertex);
        }
        System.out.println(path.size() - 1);
    }

    @Override
    public String toString() {
        String ret = new String();
        for (Map.Entry<T, Set<T>> vertex : adjacencyList.entrySet())
            ret += vertex.getKey() + ": " + vertex.getValue() + "\n";
        return ret;
    }

}

public class SocialNetwork {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        AdjacencyListGraph<String> network = new AdjacencyListGraph<>();

        for (int i = 0; i < n; i++) {
            String user = sc.nextLine();
            network.addVertex(user);
            int f = Integer.parseInt(sc.nextLine());
            for (int j = 0; j < f; j++) {
                network.addEdge(user, sc.nextLine());
            }
        }
        network.BFS(sc.nextLine(), sc.nextLine());

//        for (int i = 0; i < n; i++) {
//            String user = sc.nextLine();
//            network.addVertex(user);
//            int m = Integer.parseInt(sc.nextLine());
//            for (int j = 0; j < m; j++) {
//                network.addEdge(user, sc.nextLine());
//            }
//        }
//        network.BFSPath(sc.nextLine(), sc.nextLine());
    }
}
