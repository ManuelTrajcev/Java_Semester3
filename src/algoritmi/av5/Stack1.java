package algoritmi.av5;

import java.util.Scanner;
import java.util.Stack;

public class Stack1 {

    public static boolean isCorrect(String expression) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (c == ')' || c == ']' || c == '}') {
                    if (stack.isEmpty())
                        return false;
                    if ((c == ')' && stack.peek() == '(') || (c == ']' && stack.peek() == '[') || (c == '}' && stack.peek() == '{')) {
                        stack.pop();
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        System.out.println(isCorrect(s));
    }

}
