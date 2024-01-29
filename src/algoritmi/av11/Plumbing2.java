package algoritmi.av11;

import java.util.Map;
import java.util.Scanner;

public class Plumbing2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        AdjacencyListGraph<Integer> waterNetwork = new AdjacencyListGraph<>();
        int[] values = new int[n];

        for (int i = 0; i < n; i++) {
            int index = sc.nextInt();
            int value = sc.nextInt();
            waterNetwork.addVertex(index);
            values[index] = value;
        }

        int m = sc.nextInt();

        for (int i = 0; i < m; i++) {
            waterNetwork.addEdge(sc.nextInt(), sc.nextInt(), sc.nextInt());
        }

        int p = sc.nextInt();

        for (int i = 0; i < p; i++) {
            int startV = sc.nextInt();
            int endV = sc.nextInt();
            int coef = sc.nextInt();
            waterNetwork.addEdge(startV, endV, waterNetwork.getNeighbors(startV).get(endV)*coef);
        }

        Map<Integer, Integer> path = waterNetwork.shortestPath(0);

        for (int i = 0; i < n; i++) {
            if (values[i] != 0 && (values[0] < (100-(path.get(i) + values[i])))) {
                System.out.println(i);
            }
        }
    }
}
