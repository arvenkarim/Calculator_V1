package Calaculator;

import java.util.ArrayList;
import java.util.Scanner;

public class Calculator {

    private static final ArrayList<String> history = new ArrayList<>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Command: 'h' = Show history, 'q' = Quit");

        while (true) {
            System.out.println("\nEnter The Operator (+, -, *, /, %, ^)");
            String opLine = input.nextLine().trim();

            if (opLine.equalsIgnoreCase("q")) {
                System.out.println("Bey!");
                break;
            }
            if (opLine.equalsIgnoreCase("h")) {
                if (history.isEmpty()) {
                    System.out.println("No history yet");
                } else {
                    System.out.println("Last 10 calculations (newest first):");
                    for (int i = history.size() - 1; i >= 0; i--) {
                        System.out.println(history.get(i));
                    }
                }
                continue;
            }

            if (opLine.length() != 1) {
                System.out.println("Invalid operator");
                continue;
            }
            char operator = opLine.charAt(0);

            System.out.println("Enter the two numbers one by one (or bracket like (3 + 4))");

            double num1 = readNumber(input);
            if (Double.isNaN(num1)) continue;

            double num2 = readNumber(input);
            if (Double.isNaN(num2)) continue;

            double result;
            String calculation;

            switch (operator) {
                case '+':
                    result = num1 + num2;
                    calculation = String.format("%.2f + %.2f = %.2f", num1, num2, result);
                    System.out.printf("%.2f + %.2f = %.2f%n", num1, num2, result);
                    break;
                case '-':
                    result = num1 - num2;
                    calculation = String.format("%.2f - %.2f = %.2f", num1, num2, result);
                    System.out.printf("%.2f - %.2f = %.2f%n", num1, num2, result);
                    break;
                case '*':
                    result = num1 * num2;
                    calculation = String.format("%.2f * %.2f = %.2f", num1, num2, result);
                    System.out.printf("%.2f * %.2f = %.2f%n", num1, num2, result);
                    break;
                case '/':
                    if (num2 != 0) {
                        result = num1 / num2;
                        calculation = String.format("%.2f / %.2f = %.2f", num1, num2, result);
                        System.out.printf("%.2f / %.2f = %.2f%n", num1, num2, result);
                    } else {
                        System.out.println("Cannot divide by zero");
                        continue;
                    }
                    break;
                case '%':
                    if (num2 != 0) {
                        result = num1 % num2;
                        calculation = String.format("%.2f %% %.2f = %.2f", num1, num2, result);
                        System.out.printf("%.2f %% %.2f = %.2f%n", num1, num2, result);
                    } else {
                        System.out.println("Cannot modulo by zero");
                        continue;
                    }
                    break;
                case '^':
                    result = Math.pow(num1, num2);
                    calculation = String.format("%.2f ^ %.2f = %.2f", num1, num2, result);
                    System.out.println(result);
                    break;
                default:
                    System.out.printf("%c is an invalid operator%n", operator);
                    continue;
            }

            // History speichern (nur bei gültiger Berechnung)
            history.add(calculation);
            if (history.size() > 10) {
                history.removeFirst();
            }

            System.out.println("Do you Want to make more Calculations?");
        }

        input.close();
    }

    // Hilfsmethode zum Lesen einer Zahl oder Klammer (einfach und minimal)
    private static double readNumber(Scanner input) {
        String line = input.nextLine().trim();

        // Klammer erkennen: (zahl operator zahl)
        if (line.startsWith("(") && line.endsWith(")")) {
            String inside = line.substring(1, line.length() - 1).trim();
            String[] parts = inside.split("\\s+");
            if (parts.length == 3) {
                try {
                    double a = Double.parseDouble(parts[0]);
                    char op = parts[1].charAt(0);
                    double b = Double.parseDouble(parts[2]);
                    double res;
                    switch (op) {
                        case '+' : res = a + b; break;
                        case '-' : res = a - b; break;
                        case '*' : res = a * b; break;
                        case '/' :
                            if (b != 0) res = a / b;
                            else {
                                System.out.println("Cannot divide by zero in bracket");
                                return Double.NaN;
                            }
                            break;
                        case '%' :
                            if (b != 0) res = a % b;
                            else {
                                System.out.println("Cannot modulo by zero in bracket");
                                return Double.NaN;
                            }
                            break;
                        case '^' : res = Math.pow(a, b); break;
                        default:
                            System.out.println("Invalid operator in bracket");
                            return Double.NaN;
                    }
                    System.out.println("Bracket result: " + res);
                    return res;
                } catch (Exception e) {
                    System.out.println("Invalid bracket");
                }
            } else {
                System.out.println("Bracket must be like (5 + 3)");
            }
            return Double.NaN;
        }

        // Normale Zahl
        try {
            return Double.parseDouble(line);
        } catch (Exception e) {
            System.out.println("Invalid number");
            return Double.NaN;
        }
    }
}