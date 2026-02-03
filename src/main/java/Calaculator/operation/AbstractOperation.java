package Calaculator.operation;

public abstract class AbstractOperation {
  private final char operation;

  public AbstractOperation(char operation) {
    this.operation = operation;
  }

  public char getOperation() {
    return this.operation;
  }

  public abstract double calculate(double intA, double intB);
}
