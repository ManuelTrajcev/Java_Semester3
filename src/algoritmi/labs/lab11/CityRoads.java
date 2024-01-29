//package algoritmi.labs.lab11;
//
//import java.util.HashMap;
//import java.util.Scanner;
//
//
//import java.util.*;
//
//class AdjacencyListGraph<T> {
//    private Map<T, Map<T, Double>> adjacencyList;
//
//    public AdjacencyListGraph() {
//        this.adjacencyList = new HashMap<>();
//    }
//
//    // Add a vertex to the graph
//    public void addVertex(T vertex) {
//        if (!adjacencyList.containsKey(vertex)) {
//            adjacencyList.put(vertex, new HashMap<>());
//        }
//    }
//
//    // Remove a vertex from the graph
//    public void removeVertex(T vertex) {
//        // Remove the vertex from all adjacency lists
//        for (Map<T, Double> neighbors : adjacencyList.values()) {
//            neighbors.remove(vertex);
//        }
//        // Remove the vertex's own entry in the adjacency list
//        adjacencyList.remove(vertex);
//    }
//
//    // Add an edge to the graph
//    public void addEdge(T source, T destination, double weight) {
//        addVertex(source);
//        addVertex(destination);
//
//        adjacencyList.get(source).put(destination, weight);
////        adjacencyList.get(destination).put(source, weight); // for undirected graph
//    }
//
//    // Remove an edge from the graph
//    public void removeEdge(T source, T destination) {
//        if (adjacencyList.containsKey(source)) {
//            adjacencyList.get(source).remove(destination);
//        }
//        if (adjacencyList.containsKey(destination)) {
////            adjacencyList.get(destination).remove(source); // for undirected graph
//        }
//    }
//
//    // Get all neighbors of a vertex
//    public Map<T, Double> getNeighbors(T vertex) {
//        return adjacencyList.getOrDefault(vertex, new HashMap<>());
//    }
//
//    public void DFS(T startVertex) {
//        Set<T> visited = new HashSet<>();
//        DFSUtil(startVertex, visited);
//    }
//
//    private void DFSUtil(T vertex, Set<T> visited) {
//        // Mark the current node as visited and print it
//        visited.add(vertex);
//        System.out.print(vertex + " ");
//
//        // Recur for all the vertices adjacent to this vertex
//        for (T neighbor : getNeighbors(vertex).keySet()) {
//            if (!visited.contains(neighbor)) {
//                DFSUtil(neighbor, visited);
//            }
//        }
//    }
//
//
//    public void DFSNonRecursive(T startVertex) {
//        Set<T> visited = new HashSet<>();
//        Stack<T> stack = new Stack<>();
//
//        stack.push(startVertex);
//        while (!stack.isEmpty()) {
//            T vertex = stack.pop();
//            if (!visited.contains(vertex)) {
//                visited.add(vertex);
//                System.out.print(vertex + " ");
//                for (T neighbor : getNeighbors(vertex).keySet()) {
//                    if (!visited.contains(neighbor)) {
//                        stack.push(neighbor);
//                    }
//                }
//            }
//        }
//    }
//
//    public void printPath(T source, T destination) {
//        Set<T> visited = new HashSet<>();
//        Stack<T> stack = new Stack<>();
//
//        stack.push(source);
//        visited.add(source);
//        while (!stack.isEmpty() && !stack.peek().equals(destination)) {
//            T vertex = stack.peek();
//
//            boolean f = true;
//            for (T neighbor : getNeighbors(vertex).keySet()) {
//                if (!visited.contains(neighbor)) {
//                    stack.push(neighbor);
//                    visited.add(neighbor);
//                    f = false;
//                    break;
//                }
//            }
//
//            if (f) {
//                stack.pop();
//            }
//        }
//
//        Stack<T> path = new Stack<>();
//
//        while (!stack.isEmpty()) {
//            path.push(stack.pop());
//        }
//
//        while (!path.isEmpty()) {
//            System.out.print(path.pop() + " ");
//        }
//    }
//
//    public void BFS(T startVertex) {
//        Set<T> visited = new HashSet<>();
//        Queue<T> queue = new LinkedList<>();
//
//        visited.add(startVertex);
//        queue.add(startVertex);
//
//        while (!queue.isEmpty()) {
//            T vertex = queue.poll();
//            System.out.print(vertex + " ");
//
//            for (T neighbor : getNeighbors(vertex).keySet()) {
//                if (!visited.contains(neighbor)) {
//                    visited.add(neighbor);
//                    queue.add(neighbor);
//                }
//            }
//        }
//    }
//
//    public Map<T, T> BFSParents(T startVertex) {
//        Set<T> visited = new HashSet<>();
//        Queue<T> queue = new LinkedList<>();
//        Map<T, T> parents = new HashMap<>();
//
//        visited.add(startVertex);
//        queue.add(startVertex);
//
//        while (!queue.isEmpty()) {
//            T vertex = queue.poll();
//            System.out.print(vertex + " ");
//
//            for (T neighbor : getNeighbors(vertex).keySet()) {
//                parents.put(neighbor, vertex);
//                if (!visited.contains(neighbor)) {
//                    visited.add(neighbor);
//                    queue.add(neighbor);
//                }
//            }
//        }
//        return parents;
//    }
//
//    public Map<T, Double> shortestPath(T startVertex, Map<T, T> parents) {
//        Map<T, Double> distances = new HashMap<>();
//        PriorityQueue<T> queue = new PriorityQueue<>(Comparator.comparingDouble(distances::get));
//        Set<T> explored = new HashSet<>();
//
//        // Initialize distances
//        for (T vertex : adjacencyList.keySet()) {
//            distances.put(vertex, Double.MAX_VALUE);
//        }
//        distances.put(startVertex, 0.0);
//
//        queue.add(startVertex);
//
//        while (!queue.isEmpty()) {
//            T current = queue.poll();
//            explored.add(current);
//
//            for (Map.Entry<T, Double> neighborEntry : adjacencyList.get(current).entrySet()) {
//                T neighbor = neighborEntry.getKey();
//                double newDist = distances.get(current) + neighborEntry.getValue();
//
//                if (newDist < distances.get(neighbor)) {
//                    distances.put(neighbor, newDist);
//                    parents.put(neighbor, current);
//
//                    // Update priority queue
//                    if (!explored.contains(neighbor)) {
//                        queue.add(neighbor);
//                    }
//                }
//            }
//        }
//
//        return distances;
//    }
//
//    @Override
//    public String toString() {
//        String ret = new String();
//        for (Map.Entry<T, Map<T, Double>> vertex : adjacencyList.entrySet())
//            ret += vertex.getKey() + ": " + vertex.getValue() + "\n";
//        return ret;
//    }
//
//    public void printShortestPath(T from, T to, Map<T, T> parentsMap) {
//        T curr = to;
//        while (!curr.equals(from)) {
//
//            curr = parentsMap.get(from);
//        }
//    }
//}
//
//public class CityRoads {
//    private static Map<Integer, String> reverse(HashMap<String, Integer> cities) {
//        HashMap<Integer, String> result = new HashMap<>();
//        for (String key : cities.keySet()) {
//            result.put(cities.get(key), key);
//        }
//        return result;
//    }
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();   //cities
//        int m = sc.nextInt();   //roads
//        sc.nextLine();
//        AdjacencyListGraph<String> graph = new AdjacencyListGraph<>();
//        Map<String, String> parents = new HashMap<>();
//        double total = 0;
//
//        for (int i = 0; i < m; i++) {
//            String[] parts = sc.nextLine().split("\\s+");
//            graph.addVertex(parts[1]);
//            graph.addVertex(parts[3]);
//            graph.addEdge(parts[1], parts[3], Double.parseDouble(parts[4]));
//        }
//
//        String from = sc.nextLine();
//        String to = sc.nextLine();
//        Map<String, Double> fromPaths = graph.shortestPath(from, parents);
//        total += fromPaths.get(to);
//
//        printPath(from, to, parents);
//
//        Map<String, Double> toPaths = graph.shortestPath(to, parents);
//        total += toPaths.get(from);
//
//        printPath(to, from, parents);
//
//
//        System.out.println((toPaths.get(from) + fromPaths.get(to)));
//
////        HashMap<String, Integer> cities = new HashMap<>();
//        AdjacencyListGraph<Integer> citiesNetwork = new AdjacencyListGraph<>();
////
////        for (int i = 0; i < m; i++) {
////            String[] parts = sc.nextLine().split("\\s+");
////            cities.put(parts[1], Integer.parseInt(parts[0]));
////            cities.put(parts[3], Integer.parseInt(parts[2]));
////            citiesNetwork.addVertex(Integer.parseInt(parts[0]));
////            citiesNetwork.addVertex(Integer.parseInt(parts[2]));
////            citiesNetwork.addEdge(Integer.parseInt(parts[0]), Integer.parseInt(parts[2]), Float.parseFloat(parts[4]));
////        }
////
////        String from = sc.nextLine();
////        String to = sc.nextLine();
////
////        int fromIndex = cities.get(from);
////        int toIndex = cities.get(to);
////
////        Map<Integer, Integer> predecessors = new HashMap<>();
////        Map<Integer, String> reveresdMap = reverse(cities);
////        List<String> path = new ArrayList<>();
////
////        Map<Integer, Double> shortestFrom = citiesNetwork.shortestPath(cities.get(from), predecessors);
////
////        int curr = toIndex;
////        while (curr != fromIndex) {
////            path.add(reveresdMap.get(curr));
////            curr = predecessors.get(curr);
////        }
////        path.add(reveresdMap.get(curr));
////        for (int i = path.size() - 1; i >= 0; i--) {
////            System.out.printf("%s ", path.get(i));
////        }
////        System.out.println();
////        path = new ArrayList<>();
////
////        Map<Integer, Double> shortestTo = citiesNetwork.shortestPath(cities.get(to), predecessors);
////
////        curr = fromIndex;
////        fromIndex = toIndex;
////        while (curr != fromIndex) {
////            path.add(reveresdMap.get(curr));
////            curr = predecessors.get(curr);
////        }
////        path.add(reveresdMap.get(curr));
////        for (int i = path.size() - 1; i >= 0; i--) {
////            System.out.printf("%s ", path.get(i));
////        }
////        System.out.println();
////
////
////        double total = shortestFrom.get(cities.get(to)) + shortestTo.get(cities.get(from));
////
////        System.out.printf("%.1f", total);
//    }
//
//    private static void printPath(String from, String to, Map<String, String> parents) {
//        String curr = to;
//        while (!curr.equals(from)) {
//            System.out.print(curr + " ");
//            curr = parents.get(curr);
//        }
//        System.out.print(curr);
//        System.out.println();
//    }
//
//}
