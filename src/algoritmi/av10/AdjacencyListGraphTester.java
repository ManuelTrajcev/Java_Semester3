//package algoritmi.av10;
//
//public class AdjacencyListGraphTester {
//
//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		AdjacencyListGraph<Character> graph = new AdjacencyListGraph<Character>();
//		graph.addEdge('A', 'B'); //dodavam rebro od A do B
//		graph.addEdge('B', 'C'); //dodavam rebro od B do C
//		graph.addEdge('C', 'D'); //dodavam rebro od C do D
//		graph.addEdge('D', 'G'); //dodavam rebro od D do G
//		graph.addEdge('G', 'J'); //dodavam rebro od G do J
//		graph.addEdge('B', 'F'); //dodavam rebro od B do F
//		graph.addEdge('F', 'I'); //dodavam rebro od F do I
//		graph.addEdge('A', 'E'); //dodavam rebro od A do E
//		System.out.println(graph);
//		System.out.println("Depth First Search Recursive:");
//		graph.DFS('A');
//		System.out.println("\nDepth First Search Nonrecursive:");
//		graph.DFSNonRecursive('A');
//		System.out.println("\nBreath First Search:");
//		graph.BFS('A');
//
//	}
//
//}
