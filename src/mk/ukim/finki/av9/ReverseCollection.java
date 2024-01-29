package mk.ukim.finki.av9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ReverseCollection {
    public static <T> void reverseCollection(Collection<? extends T> collection) {
        Collections.reverse(new ArrayList<>(collection));

        // Print or return the reversed collection if needed
        System.out.println(collection);
    }

    public static void main(String[] args) throws IOException {
        List<String> coll = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            coll.add(br.readLine());
        }
        System.out.println(coll);
        Collections.reverse(coll);
        System.out.println(coll);
    }
}
