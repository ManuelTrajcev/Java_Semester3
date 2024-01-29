package algoritmi.av11;

import algoritmi.av11.AdjacencyMatrixGraph;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


public class Flood2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int p = sc.nextInt();
        sc.nextLine();

        HashMap<String, Integer> map = new HashMap<>();

        AdjacencyMatrixGraph<String> cityNetwork = new AdjacencyMatrixGraph<>(m);

        int[] trees = new int[m];

        for (int i = 0; i < m; i++) {
            String city = sc.nextLine();
            map.put(city, i);
            cityNetwork.addVertex(i, city);
            trees[i] = i + 1;
        }

        for (int i = 0; i < n; i++) {
            String[] parts = sc.nextLine().split("\\s+");
            cityNetwork.addEdge(map.get(parts[0]), map.get(parts[1]), Integer.parseInt(parts[2]));
        }

        for (int i = 0; i < p; i++) {
            String[] parts = sc.nextLine().split("\\s+");
            trees[map.get(parts[0])] = 0;
            trees[map.get(parts[1])] = 0;
        }

        List<Edge> result = cityNetwork.adaptedKruskal(trees);

        int sum = result.stream().mapToInt(Edge::getWeight).sum();
        System.out.println(result.size() + " - " + sum);

        for(Edge e : result) {
            System.out.println(cityNetwork.getVertex(e.getFrom()) + " " + cityNetwork.getVertex(e.getTo()));
        }
    }
}
