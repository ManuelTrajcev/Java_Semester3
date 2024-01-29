package mk.ukim.finki.av4.Calculator;

import mk.ukim.finki.av4.calc.Operation;

import java.util.Scanner;

public class CalculatorTest {

    Operation complex = (x, y) -> {
        if (x > y) {
            return x / y;
        } else {
            return y / x;
        }
    };

    private static char getFirstCharacter(String string) {
        return string.trim().toLowerCase().charAt(0);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();

        while (true) {
            while (true) {
                String line = scanner.nextLine();
                if (getFirstCharacter(line) == 'r') {
                    break;
                }
                String[] parts = line.split("\s++");
                try {
                    calculator.execute(parts[0].charAt(0), Double.parseDouble(parts[1]));
                    System.out.println(calculator);
                } catch (OperationNotSupported exception) {
                    System.out.println(exception.getMessage());
                }
            }
            String line = scanner.nextLine();
            if (getFirstCharacter(line) == 'n') {
                break;
            }

        }
    }
}
