package Calaculator.operation.impl;

import Calaculator.operation.AbstractOperation;

public class Multiply extends AbstractOperation {

  public Multiply() {
    super("*");
  }

  @Override
  public double calculate(double intA, double intB) {
    return intA * intB;
  }
}
