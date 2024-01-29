package mk.ukim.finki.av2;


import java.util.stream.IntStream;

public class zad1 {
    public static boolean isPrefix(String str1, String str2) {
        if (str1.length() > str2.length()) {
            return false;
        }
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                return false;
            }
        }
        return true;
    }
    public static boolean isPrefixStream(String str1, String str2){
        if (str1.length() > str2.length()) {
            return false;
        }
        return IntStream.range(0,str1.length())
                .allMatch(i -> str1.charAt(i) == str2.charAt(i));
    }

    public static void main(String[] args) {
        String str1 = "Hello world";
        String str2 = "Hello";
        System.out.println(isPrefix(str2, str1));
        System.out.println(isPrefixStream(str2, str1));

    }
}
