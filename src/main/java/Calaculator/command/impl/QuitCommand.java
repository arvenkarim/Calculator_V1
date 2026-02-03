package Calaculator.command.impl;

import Calaculator.command.Command;

/**
 * Command to quit the calculator.
 */
public class QuitCommand implements Command {

  @Override
  public char getKey() {
    return 'q';
  }

  @Override
  public boolean execute() {
    System.out.println("Bye!");
    return true;
  }
}
