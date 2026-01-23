package Calaculator;

import java.util.ArrayList;
import java.util.Scanner;

public class Calculator {
    private static ArrayList<String> history = new ArrayList<>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("\nEnter The Operator (+, -, *, /, ^) or h for history or q to quit");
            String opLine = input.nextLine();

            if (opLine.equalsIgnoreCase("q")) {
                input.close();
                System.out.println("Bye!");
                return;
            }

            if (opLine.equalsIgnoreCase("h")) {
                showHistory();
                continue;
            }

            char operator;
            try {
                operator = opLine.charAt(0);
            } catch (Exception e) {
                System.out.println("Invalid operator");
                continue;
            }

            System.out.println("Enter the two numbers one by one (or (3 + 4) for brackets)");

            String line1 = input.nextLine();
            double num1 = parseInput(line1);
            if (Double.isNaN(num1)) continue;

            String line2 = input.nextLine();
            double num2 = parseInput(line2);
            if (Double.isNaN(num2)) continue;

            String calculation;
            double result = 0;

            switch (operator) {
                case '+':
                    result = add(num1, num2);
                    System.out.printf("%.2f + %.2f = %.2f%n", num1, num2, result);
                    calculation = String.format("%.2f + %.2f = %.2f", num1, num2, result);
                    break;
                case '-':
                    result = subtract(num1, num2);
                    System.out.printf("%.2f - %.2f = %.2f%n", num1, num2, result);
                    calculation = String.format("%.2f - %.2f = %.2f", num1, num2, result);
                    break;
                case '*':
                    result = multiply(num1, num2);
                    System.out.printf("%.2f * %.2f = %.2f%n", num1, num2, result);
                    calculation = String.format("%.2f * %.2f = %.2f", num1, num2, result);
                    break;
                case '/':
                    if (num2 != 0) {
                        result = divide(num1, num2);
                        System.out.printf("%.2f / %.2f = %.2f%n", num1, num2, result);
                        calculation = String.format("%.2f / %.2f = %.2f", num1, num2, result);
                    } else {
                        System.out.println("Cannot divide by zero");
                        continue;
                    }
                    break;
                case '^':
                    result = power(num1, num2);
                    System.out.println(result);
                    calculation = String.format("%.2f ^ %.2f = %.2f", num1, num2, result);
                    break;
                default:
                    System.out.printf("%c is an invalid operator%n", operator);
                    continue;
            }

            addToHistory(calculation);

            System.out.println("Do you Want to make more Calculations");
        }
    }

    // SRP: Jede Operation eigene Methode
    private static double add(double a, double b) { return a + b; }
    private static double subtract(double a, double b) { return a - b; }
    private static double multiply(double a, double b) { return a * b; }
    private static double divide(double a, double b) { return a / b; }
    private static double power(double a, double b) { return Math.pow(a, b); }

    // SRP: History-Methoden getrennt
    private static void addToHistory(String calc) {
        history.add(calc);
        if (history.size() > 10) {
            history.remove(0);
        }
    }

    private static void showHistory() {
        if (history.isEmpty()) {
            System.out.println("No history yet");
        } else {
            System.out.println("Last 10 calculations:");
            for (int i = history.size() - 1; i >= 0; i--) {
                System.out.println(history.get(i));
            }
        }
    }

    // SRP: Eingabe + Klammern in eigener Methode
    private static double parseInput(String line) {
        line = line.trim();

        if (line.startsWith("(") && line.endsWith(")")) {
            String inside = line.substring(1, line.length() - 1).trim();
            String[] parts = inside.split(" ");
            if (parts.length == 3) {
                try {
                    double a = Double.parseDouble(parts[0]);
                    char op = parts[1].charAt(0);
                    double b = Double.parseDouble(parts[2]);
                    double res = 0;
                    switch (op) {
                        case '+': res = add(a, b); break;
                        case '-': res = subtract(a, b); break;
                        case '*': res = multiply(a, b); break;
                        case '/': res = divide(a, b); break;
                        case '^': res = power(a, b); break;
                        default:
                            System.out.println("Invalid operator in bracket");
                            return Double.NaN;
                    }
                    System.out.println("Bracket result: " + res);
                    return res;
                } catch (Exception e) {
                    System.out.println("Invalid bracket");
                    return Double.NaN;
                }
            }
        }

        try {
            return Double.parseDouble(line);
        } catch (Exception e) {
            System.out.println("Invalid number");
            return Double.NaN;
        }
    }
}