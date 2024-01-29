//package algoritmi.vtorKolokvium;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
//public class RiskFactor {
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int N = Integer.parseInt(br.readLine());
//
//        CBHT<String, String> pozitivni = new CBHT<>(2 * N);
//        CBHT<String, String> negativni = new CBHT<>(2 * N);
//
//
//        for (int i = 0; i < N; i++) {
//            String[] p = br.readLine().split(" ");
//            String opstina = p[0];
//            String prezime = p[1];
//            String rezultat = p[2];
//
//            if (rezultat.equals("pozitiven")) {
//                pozitivni.insert(opstina, prezime);
//            } else {
//                negativni.insert(opstina, prezime);
//            }
//        }
//
//
//        String opstina = br.readLine();
//
//        int pozSlucaevi = 0;
//
//        SLLNode<MapEntry<String, String>> pom = pozitivni.search(opstina);
//        while (pom != null) {
//            pozSlucaevi++;
//            pom = pom.succ;
//        }
//
//        int negSlucaevi = 0;
//
//        pom = negativni.search(opstina);
//        while (pom != null) {
//            negSlucaevi++;
//            pom = pom.succ;
//        }
//
//        float riskFactor = (float) pozSlucaevi / (negSlucaevi + pozSlucaevi);
//        System.out.println(String.format("%.2f", riskFactor));
//    }
//}
