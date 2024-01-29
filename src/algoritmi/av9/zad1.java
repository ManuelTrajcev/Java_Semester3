package algoritmi.av9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class zad1 {
    public static void main(String[] args) throws IOException {
        int i, j, k;
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            tree.insert(num);
        }
        br.close();
        System.out.println(tree.maxPathSum(tree.root));
    }
}
