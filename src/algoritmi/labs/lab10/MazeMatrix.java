package algoritmi.labs.lab10;

import java.util.*;

class AdjacencyMatrixGraph<T> {
    private int numVertices;
    private int[][] matrix;
    private T[] vertices;

    @SuppressWarnings("unchecked")
    public AdjacencyMatrixGraph(int numVertices) {
        this.numVertices = numVertices;
        matrix = new int[numVertices][numVertices];
        vertices = (T[]) new Object[numVertices];
    }

    public void addVertex(int index, T data) {
        vertices[index] = data;
    }

    public T getVertex(int index) {
        return vertices[index];
    }

    public void addEdge(int source, int destination) {
        matrix[source][destination] = 1;
        matrix[destination][source] = 1; // For undirected graph
    }

    public boolean isEdge(int source, int destination) {
        return matrix[source][destination] == 1;
    }


    public void removeEdge(int source, int destination) {
        matrix[source][destination] = 0;
        matrix[destination][source] = 0; // For undirected graph
    }

    @SuppressWarnings("unchecked")
    public void removeVertex(int vertexIndex) {
        if (vertexIndex < 0 || vertexIndex >= numVertices) {
            throw new IndexOutOfBoundsException("Vertex index out of bounds!");
        }
        int[][] newMatrix = new int[numVertices - 1][numVertices - 1];
        T[] newVertices = (T[]) new Object[numVertices - 1];
        // Copy the vertices and matrix excluding the given vertex
        int ni = 0;
        for (int i = 0; i < numVertices; i++) {
            if (i == vertexIndex) continue;
            int nj = 0;
            for (int j = 0; j < numVertices; j++) {
                if (j == vertexIndex) continue;
                newMatrix[ni][nj] = matrix[i][j];
                nj++;
            }
            newVertices[ni] = vertices[i];
            ni++;
        }
        // Replace the old matrix and vertices with the new ones
        matrix = newMatrix;
        vertices = newVertices;
        numVertices--;
    }

    public List<T> getNeighbors(int vertexIndex) {
        List<T> neighbors = new ArrayList<>();
        for (int i = 0; i < matrix[vertexIndex].length; i++) {
            if (matrix[vertexIndex][i] == 1) {
                neighbors.add(vertices[i]);
            }
        }
        return neighbors;
    }


    @Override
    public String toString() {
        String ret = "  ";
        for (int i = 0; i < numVertices; i++)
            ret += vertices[i] + " ";
        ret += "\n";
        for (int i = 0; i < numVertices; i++) {
            ret += vertices[i] + " ";
            for (int j = 0; j < numVertices; j++)
                ret += matrix[i][j] + " ";
            ret += "\n";
        }
        return ret;
    }

    public int getNumVertices() {
        return numVertices;
    }
}

public class MazeMatrix {
    static int start;
    static int end;
    static AdjacencyMatrixGraph<String> maze;
    static HashMap<String, Integer> mazeMap = new HashMap<>();

    private static void createGraph(int n, int m, String[] in) {
        int totalNodes = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (in[i].charAt(j) != '#') {
                    String key = i + "," + j;
                    mazeMap.put(key, totalNodes++);
                    if (in[i].charAt(j) == 'S') {
                        start = totalNodes - 1;
                    }

                    if (in[i].charAt(j) == 'E') {
                        end = totalNodes - 1;
                    }
                }
            }
        }
        maze = new AdjacencyMatrixGraph<>(totalNodes);
        int counter = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (in[i].charAt(j) != '#') {
                    String x = i + "," + j;
                    maze.addVertex(counter++, x);
                }
            }
        }
//        System.out.println(mazeMap.get("1,1"));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (in[i].charAt(j) != '#') {
                    if (in[i].charAt(j - 1) != '#') {
                        String x = i + "," + j;
                        String y = i + "," + (j - 1);
                        maze.addEdge(mazeMap.get(x), mazeMap.get(y));
                    }
                    if (in[i].charAt(j + 1) != '#') {
                        String x = i + "," + j;
                        String y = i + "," + (j + 1);
                        maze.addEdge(mazeMap.get(x), mazeMap.get(y));
                    }
                    if (in[i - 1].charAt(j) != '#') {
                        String x = i + "," + j;
                        String y = (i - 1) + "," + j;
                        maze.addEdge(mazeMap.get(x), mazeMap.get(y));
                    }
                    if (in[i + 1].charAt(j) != '#') {
                        String x = i + "," + j;
                        String y = (i + 1) + "," + j;
                        maze.addEdge(mazeMap.get(x), mazeMap.get(y));
                    }
                }
            }
        }
//        System.out.println(maze);
        findPath(start, end, maze);
    }

    private static void findPath(int start, int end, AdjacencyMatrixGraph<String> maze) {
        boolean[] visited = new boolean[maze.getNumVertices()];
        for (int i = 0; i < maze.getNumVertices(); i++) {
            visited[i] = false;
        }
        visited[start] = true;

        Stack<String> path = new Stack<>();
        Stack<Integer> pathInt = new Stack<>();
        path.push(maze.getVertex(start));
        pathInt.push(start);
        int tmp = start;
        while (!path.isEmpty() && !path.peek().equals(maze.getVertex(end))) {
            int tmp1 = tmp;
            for (int i = 0; i < maze.getNumVertices(); i++) {
                if (maze.isEdge(tmp, i)) {
                    tmp1 = i;
                    if (!visited[i]) break;
                }
            }
            if (!visited[tmp1]) {
                tmp = tmp1;
                visited[tmp1] = true;
                path.push(maze.getVertex(tmp1));
                pathInt.push(tmp1);
            } else {
                path.pop();
                pathInt.pop();
                tmp = pathInt.peek();
            }
        }

        Stack<String> flipped = new Stack<>();
        while (!path.isEmpty()) {
            flipped.push(path.pop());
        }

        while (!flipped.isEmpty()) {
            System.out.println(flipped.pop());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] numbers = sc.nextLine().split(",");
        int n = Integer.parseInt(numbers[0]);
        int m = Integer.parseInt(numbers[1]);
        String[] input = new String[n];
        for (int i = 0; i < n; i++) {
            input[i] = sc.nextLine();
        }
        createGraph(n, m, input);
//        System.out.println(maze);

    }
}
