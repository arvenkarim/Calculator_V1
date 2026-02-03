package Calaculator.operation.impl;

import Calaculator.operation.AbstractOperation;

public class Divide extends AbstractOperation {

  public Divide() {
    super('/');
  }

  @Override
  public double calculate(double a, double b) {
    if (b == 0) {
      throw new ArithmeticException("Cannot divide by zero");
    }
    return a / b;
  }
}
