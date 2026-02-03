package Calaculator.command;

/**
 * Interface for calculator commands (non-arithmetic operations).
 */
public interface Command {

  /**
   * Returns the key that triggers this command.
   *
   * @return the command key (e.g., 'q', 'h')
   */
  char getKey();

  /**
   * Executes the command.
   *
   * @return true if the calculator should exit, false to continue
   */
  boolean execute();
}
