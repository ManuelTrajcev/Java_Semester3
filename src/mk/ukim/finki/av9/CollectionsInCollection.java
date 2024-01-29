package mk.ukim.finki.av9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CollectionsInCollection {
    public static int count(Collection<Collection<String>> c, String str) {
        return (int) c.stream()
                .mapToLong(col -> col.stream()
                        .filter(s -> s.toLowerCase().contains(str.toLowerCase()))
                        .count())
                .sum();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Collection<Collection<String>> collections = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Collection<String> innerCollection = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                innerCollection.add(br.readLine().toString());
            }
            collections.add(innerCollection);
        }

        br.close();
        System.out.println(count(collections, "hello"));
    }
}
