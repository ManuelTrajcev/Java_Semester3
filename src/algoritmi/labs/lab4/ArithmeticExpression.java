package algoritmi.labs.lab4;

// package algoritmi.labs.lab4;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static java.lang.Character.isDigit;

public class ArithmeticExpression {

    // funkcija za presmetuvanje na izrazot pocnuvajki
    // od indeks l, zavrsuvajki vo indeks r

    static int operation(int a, int b, char c) {
        switch (c) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '/':
                return a / b;
            case '*':
                return a * b;
        }
        return -1;
    }

    static int presmetaj(char c[], int l, int r) {
        int result = 0;
        int a = 0, b, counter = 0;
        char op;
        l++;
        if (isDigit(c[l])) {
            a = Integer.parseInt(String.valueOf(c[l++]));
        } else {
            if (c[l] == '(') {
                counter++;
                a = presmetaj(c, l, r);
                l++;

                while (counter > 0) {
                    if (c[l] == '(')
                        counter++;
                    if (c[l] == ')')
                        counter--;
                    l++;
                }
            }
        }
        op = c[l++];
        if (c[l] == '(') {
            counter++;

            b = presmetaj(c, l, r);
            l++;
            while (counter > 0) {
                if (c[l] == '(')
                    counter++;
                if (c[l] == ')')
                    counter--;
                l++;
            }
        } else
            b = Integer.parseInt(String.valueOf(c[l++]));
        result = operation(a, b, op);

        return result;
    }

    public static void main(String[] args) throws Exception {
        int i, j, k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String expression = br.readLine();
        char exp[] = expression.toCharArray();

        int rez = presmetaj(exp, 0, exp.length - 1);
        System.out.println(rez);

        br.close();

    }

}
