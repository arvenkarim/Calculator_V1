package Calaculator;

import java.util.ArrayList;
import java.util.Scanner;

public class Calculator {

    /** Speichert die letzten 10 Berechnungen */
    private ArrayList<String> history = new ArrayList<>();

    /** Einstiegspunkt */
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.run();
    }

    /** Startet den Taschenrechner */
    public void run() {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("\nEnter operator (+, -, *, /, %, ^) or h for history or q to quit");
            String opLine = input.nextLine().trim();

            if (opLine.equalsIgnoreCase("q")) {
                System.out.println("Bye!");
                break;
            }

            if (opLine.equalsIgnoreCase("h")) {
                showHistory();
                continue;
            }

            if (opLine.length() != 1) {
                System.out.println("Invalid operator");
                continue;
            }

            char operator = opLine.charAt(0);

            System.out.println("Enter first number:");
            double num1 = parseInput(input.nextLine());
            if (Double.isNaN(num1)) continue;

            System.out.println("Enter second number:");
            double num2 = parseInput(input.nextLine());
            if (Double.isNaN(num2)) continue;

            double result;
            String calculation;

            switch (operator) {
                case '+':
                    result = add(num1, num2);
                    break;
                case '-':
                    result = subtract(num1, num2);
                    break;
                case '*':
                    result = multiply(num1, num2);
                    break;
                case '/':
                    if (num2 == 0) {
                        System.out.println("Cannot divide by zero");
                        continue;
                    }
                    result = divide(num1, num2);
                    break;
                case '%':
                    if (num2 == 0) {
                        System.out.println("Cannot modulo by zero");
                        continue;
                    }
                    result = modulo(num1, num2);
                    break;
                case '^':
                    result = power(num1, num2);
                    break;
                default:
                    System.out.println("Invalid operator");
                    continue;
            }

            calculation = num1 + " " + operator + " " + num2 + " = " + result;
            System.out.println(calculation);
            addToHistory(calculation);
        }

        input.close();
    }

    // ===== Rechenmethoden (NICHT static) =====

    private double add(double a, double b) { return a + b; }
    private double subtract(double a, double b) { return a - b; }
    private double multiply(double a, double b) { return a * b; }
    private double divide(double a, double b) { return a / b; }
    private double modulo(double a, double b) { return a % b; }
    private double power(double a, double b) { return Math.pow(a, b); }

    // ===== History =====

    private void addToHistory(String calc) {
        history.add(calc);
        if (history.size() > 10) {
            history.remove(0);
        }
    }

    private void showHistory() {
        if (history.isEmpty()) {
            System.out.println("No history yet");
            return;
        }
        System.out.println("Last calculations:");
        for (int i = history.size() - 1; i >= 0; i--) {
            System.out.println(history.get(i));
        }
    }

    // ===== Input =====

    private double parseInput(String line) {
        try {
            return Double.parseDouble(line.trim());
        } catch (Exception e) {
            System.out.println("Invalid number");
            return Double.NaN;
        }
    }
}
