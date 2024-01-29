//package algoritmi.vtorKolokvium;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.StringTokenizer;
//
//public class MinTwoDifferentInPaths {
//
//    public static int maxPath(BNode<Integer> node, int prevVal, int prevLen) {
//        if (node == null) {
//            return prevLen;
//        }
//        int curr = node.info;
//        if (curr == prevLen + 1) {
//            int left = maxPath(node.left, curr, prevLen + 1);
//            int right = maxPath(node.right, curr, prevLen + 1);
//            return Math.max(left, right);
//        }
//
//        int left = maxPath(node.left, curr, prevLen + 1);
//        int right = maxPath(node.right, curr, prevLen + 1);
//        int path = Math.max(left, right);
//        return path;
//    }
//
//    public static int maxPathCall(BNode<Integer> root) {
//        if (root == null) {
//            return 0;
//        }
//        return maxPath(root, root.info - 1, 0);
//    }
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st;
//
//        int N = Integer.parseInt(br.readLine());
//
//        @SuppressWarnings("unchecked")
//        BNode<Integer>[] nodes = new BNode[N];
//        BTree<Integer> tree = new BTree<>();
//
//        for (int i = 0; i < N; i++) {
//            nodes[i] = new BNode<>();
//        }
//
//        for (int i = 0; i < N; i++) {
//            String line = br.readLine();
//            st = new StringTokenizer(line);
//            int index = Integer.parseInt(st.nextToken());
//            nodes[index].info = Integer.parseInt(st.nextToken());
//            String action = st.nextToken();
//            if (action.equals("LEFT")) {
//                tree.addChildNode(nodes[Integer.parseInt(st.nextToken())], BNode.LEFT, nodes[index]);
//            } else if (action.equals("RIGHT")) {
//                tree.addChildNode(nodes[Integer.parseInt(st.nextToken())], BNode.RIGHT, nodes[index]);
//            } else {
//                tree.makeRootNode(nodes[index]);
//            }
//        }
//        br.close();
//
//        System.out.println("Maximum path length:" + maxPathCall(tree.root));
//    }
//}
