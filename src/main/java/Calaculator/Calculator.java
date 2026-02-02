package Calaculator;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Einfacher Konsolen-Taschenrechner.
 * <p>
 * Der Calculator nutzt für jede Rechenart eine eigene Klasse
 * (z. B. Add, Subtract, Multiply, usw.).
 * Er unterstützt die Operatoren +, -, *, /, % und ^ sowie
 * eine einfache History der letzten 10 Berechnungen.
 * </p>
 *
 * @author Said Arven Karim
 * @version 1.0
 */
public class Calculator {

    /** Speichert die letzten 10 Berechnungen */
    private ArrayList<String> history = new ArrayList<>();

    /**
     * Einstiegspunkt des Programms.
     *
     * @param args Programmargumente (werden nicht verwendet)
     */
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.run();
    }

    /**
     * Startet die Hauptlogik des Taschenrechners
     * und verarbeitet die Benutzereingaben.
     */
    public void run() {
        Scanner input = new Scanner(System.in);

        // Erzeugen der Operator-Klassen
        Add add = new Add();
        Subtract subtract = new Subtract();
        Multiply multiply = new Multiply();
        Divide divide = new Divide();
        Power power = new Power();
        Modulo modulo = new Modulo();

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
            double num1 = parseNumber(input.nextLine());
            if (Double.isNaN(num1)) continue;

            System.out.println("Enter second number:");
            double num2 = parseNumber(input.nextLine());
            if (Double.isNaN(num2)) continue;

            double result;
            String calculation;

            switch (operator) {
                case '+':
                    result = add.calculate(num1, num2);
                    break;
                case '-':
                    result = subtract.calculate(num1, num2);
                    break;
                case '*':
                    result = multiply.calculate(num1, num2);
                    break;
                case '/':
                    if (num2 == 0) {
                        System.out.println("Cannot divide by zero");
                        continue;
                    }
                    result = divide.calculate(num1, num2);
                    break;
                case '%':
                    if (num2 == 0) {
                        System.out.println("Cannot modulo by zero");
                        continue;
                    }
                    result = modulo.calculate(num1, num2);
                    break;
                case '^':
                    result = power.calculate(num1, num2);
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

    /**
     * Wandelt einen String in eine Zahl um.
     *
     * @param input Eingabe des Benutzers
     * @return die geparste Zahl oder Double.NaN bei ungültiger Eingabe
     */
    private double parseNumber(String input) {
        try {
            return Double.parseDouble(input.trim());
        } catch (Exception e) {
            System.out.println("Invalid number");
            return Double.NaN;
        }
    }

    /**
     * Fügt eine Berechnung zur History hinzu.
     * Es werden maximal die letzten 10 Einträge gespeichert.
     *
     * @param calculation Berechnung als String
     */
    private void addToHistory(String calculation) {
        history.add(calculation);
        if (history.size() > 10) {
            history.remove(0);
        }
    }

    /**
     * Gibt die gespeicherten Berechnungen aus.
     */
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
}
