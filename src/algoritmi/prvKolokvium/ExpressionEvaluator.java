package algoritmi.prvKolokvium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class ExpressionEvaluator {
    public static int evaluateExpression(String expression) {
        ArrayList<Integer> numbers = new ArrayList<>();
        ArrayList<Integer> multiplied = new ArrayList<>();
        ArrayList<Character> operations = new ArrayList<>();
        for (int i = 0; i < expression.length(); i++) {
            StringBuilder stringBuilder = new StringBuilder();
            if (Character.isDigit(expression.charAt(i))) {
                while (Character.isDigit(expression.charAt(i))) {
                    stringBuilder.append(expression.charAt(i));
                    i++;
                    if (i == expression.length()) break;
                }
                numbers.add(Integer.parseInt(stringBuilder.toString()));
                if (i == expression.length()) break;
            }
            operations.add(expression.charAt(i));
        }

        Integer[] n = numbers.toArray(new Integer[0]);

        int a = 0, b = 0, j = 0, sum = 0;
        for (int i = 0; i < n.length; i++) {
            if (operations.get(j) == '+') {
                multiplied.add(n[i]);
            } else {
                a = n[i];
                b = n[i + 1];
                n[i + 1] = a * b;
            }
            j++;
            if (j == operations.size()) {
                multiplied.add(n[++i]);
                break;
            }
        }
        for (int i = 0; i < multiplied.size(); i++) {
            sum += multiplied.get(i);
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(evaluateExpression(input.readLine()));
    }

}