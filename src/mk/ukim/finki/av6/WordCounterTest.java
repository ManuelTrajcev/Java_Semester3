package mk.ukim.finki.av6;


import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.Collectors;

class WordCounter {
    public static void count(InputStream is) {
        int lines = 0, words = 0, chars = 0;
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            ++lines;
            String[] parts = line.split("\\s+");
            words += parts.length;
            chars += line.length();
        }
        scanner.close();
        System.out.println(String.format("Lines: %d Words: %d Chars:%d", lines, words, chars));
    }

    public static void countBr(InputStream is) {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

    }

    public static void countStreams(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        List<String> lines = br.lines().collect(Collectors.toList());
        System.out.println(
                String.format(
                        "Lines: %d Words: %d Chars: %d",
                        lines.size(),
                        lines.stream()
                )
        );
    }
}

class LineConsumer implements Consumer<String> {
    int lines = 0, words = 0, chars = 0;

    @Override
    public void accept(String line) {
        ++lines;
        words += line.split("\\s+").length;
        chars += line.length();
    }

    @Override
    public String toString() {
        return "LineConsumer{" +
                "lines=" + lines +
                ", words=" + words +
                ", chars=" + chars +
                '}';
    }

}


public class WordCounterTest {

    public static void main(String[] args) {
        try {
            InputStream is = new FileInputStream("src\\mk\\ukim\\finki\\av6\\source.txt");
            WordCounter.countBr(is);
        } catch (FileNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
