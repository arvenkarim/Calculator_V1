package Calaculator.operation.impl;

import Calaculator.operation.AbstractOperation;

public class Multiply extends AbstractOperation {

  public Multiply() {
    super('*');
  }

  @Override
  public double calculate(double a, double b) {
    return a * b;
  }
}
