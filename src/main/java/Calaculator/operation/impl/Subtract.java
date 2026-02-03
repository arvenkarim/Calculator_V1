package Calaculator.operation.impl;

import Calaculator.operation.AbstractOperation;

public class Subtract extends AbstractOperation {

  public Subtract() {
    super('-');
  }

  @Override
  public double calculate(double a, double b) {
    return a - b;
  }
}
