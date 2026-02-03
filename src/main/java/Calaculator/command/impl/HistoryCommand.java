package Calaculator.command.impl;

import Calaculator.CalculationHistory;
import Calaculator.command.Command;

import java.util.List;

/**
 * Command to display the calculation history.
 */
public class HistoryCommand implements Command {

  private final CalculationHistory history;

  public HistoryCommand(CalculationHistory history) {
    this.history = history;
  }

  @Override
  public char getKey() {
    return 'h';
  }

  @Override
  public boolean execute() {
    if (history.isEmpty()) {
      System.out.println("No history yet");
      return false;
    }

    List<String> entries = history.getEntries();
    System.out.println("Last calculations:");
    for (int i = entries.size() - 1; i >= 0; i--) {
      System.out.println(entries.get(i));
    }
    return false;
  }
}
