package Calaculator.operation.impl;

import Calaculator.operation.AbstractOperation;

public class Modulo extends AbstractOperation {

  public Modulo() {
    super('%');
  }

  @Override
  public double calculate(double a, double b) {
    if (b == 0) {
      throw new ArithmeticException("Cannot calculate modulo by zero");
    }
    return a % b;
  }
}
