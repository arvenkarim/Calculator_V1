package Calaculator;

import Calaculator.operation.AbstractOperation;
import Calaculator.operation.impl.*;

import java.util.ArrayList;
import java.util.List;
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
  private final ArrayList<String> history = new ArrayList<>();
  private final List<AbstractOperation> operations;

  public Calculator(List<AbstractOperation> operations) {
    this.operations = operations;
  }

  /**
   * Einstiegspunkt des Programms.
   *
   * @param args Programmargumente (werden nicht verwendet)
   */
  public static void main(String[] args) {
    List<AbstractOperation> operations = List.of(
      new Add(),
      new Divide(),
      new Modulo(),
      new Multiply(),
      new Power(),
      new Subtract()
    );
    Calculator calculator = new Calculator(operations);
    calculator.run();
  }

  /**
   * Startet die Hauptlogik des Taschenrechners
   * und verarbeitet die Benutzereingaben.
   */
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
      double num1 = parseNumber(input.nextLine());
      if (Double.isNaN(num1)) continue;

      System.out.println("Enter second number:");
      double num2 = parseNumber(input.nextLine());
      if (Double.isNaN(num2)) continue;

      String calculation;

      AbstractOperation executor = operations.stream()
        .filter(abstractOperation -> abstractOperation.getOperation() == operator)
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Operation not implemented"));

      double result = executor.calculate(num1, num2);

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
      history.removeFirst();
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
