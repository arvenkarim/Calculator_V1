package Calaculator;

import Calaculator.command.Command;
import Calaculator.command.impl.HistoryCommand;
import Calaculator.command.impl.QuitCommand;
import Calaculator.operation.AbstractOperation;
import Calaculator.operation.impl.*;

import java.util.List;

/**
 * Einstiegspunkt für den Taschenrechner.
 */
public class Main {

  public static void main(String[] args) {
    CalculationHistory history = new CalculationHistory();

    List<AbstractOperation> operations = List.of(
      new Add(),
      new Subtract(),
      new Multiply(),
      new Divide(),
      new Modulo(),
      new Power()
    );

    List<Command> commands = List.of(
      new QuitCommand(),
      new HistoryCommand(history)
    );

    Calculator calculator = new Calculator(operations, commands, history);
    calculator.run();
  }
}
