package Calaculator;

import Calaculator.command.Command;
import Calaculator.operation.AbstractOperation;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

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

  private final List<AbstractOperation> operations;
  private final List<Command> commands;
  private final CalculationHistory history;

  public Calculator(List<AbstractOperation> operations, List<Command> commands, CalculationHistory history) {
    this.operations = operations;
    this.commands = commands;
    this.history = history;
  }

  /**
   * Startet die Hauptlogik des Taschenrechners
   * und verarbeitet die Benutzereingaben.
   */
  public void run() {
    Scanner input = new Scanner(System.in);

    while (true) {
      System.out.println("\nEnter operator (" + getAvailableOperators() + ") or command (" + getAvailableCommands() + ")");
      String opLine = input.nextLine().trim();

      if (opLine.length() != 1) {
        System.out.println("Invalid input");
        continue;
      }

      char inputChar = Character.toLowerCase(opLine.charAt(0));

      // Check if input matches a command
      Optional<Command> command = commands.stream()
        .filter(cmd -> cmd.getKey() == inputChar)
        .findFirst();

      if (command.isPresent()) {
        boolean shouldExit = command.get().execute();
        if (shouldExit) {
          break;
        }
        continue;
      }

      char operator = opLine.charAt(0);

      System.out.println("Enter first number:");
      double num1 = parseNumber(input.nextLine());
      if (Double.isNaN(num1)) continue;

      System.out.println("Enter second number:");
      double num2 = parseNumber(input.nextLine());
      if (Double.isNaN(num2)) continue;

      AbstractOperation executor = operations.stream()
        .filter(abstractOperation -> abstractOperation.getOperation() == operator)
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Operation not implemented"));

      double result;
      try {
        result = executor.calculate(num1, num2);
      } catch (ArithmeticException e) {
        System.out.println("Error: " + e.getMessage());
        continue;
      }

      String calculation = num1 + " " + operator + " " + num2 + " = " + result;
      System.out.println(calculation);
      history.add(calculation);
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
    } catch (NumberFormatException e) {
      System.out.println("Invalid number");
      return Double.NaN;
    }
  }

  /**
   * Gibt die verfügbaren Operatoren als kommagetrennten String zurück.
   *
   * @return String mit allen verfügbaren Operatoren
   */
  private String getAvailableOperators() {
    return operations.stream()
      .map(op -> String.valueOf(op.getOperation()))
      .collect(Collectors.joining(", "));
  }

  /**
   * Gibt die verfügbaren Commands als kommagetrennten String zurück.
   *
   * @return String mit allen verfügbaren Commands
   */
  private String getAvailableCommands() {
    return commands.stream()
      .map(cmd -> String.valueOf(cmd.getKey()))
      .collect(Collectors.joining(", "));
  }
}
