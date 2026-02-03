package Calaculator.operation.impl;

import Calaculator.operation.AbstractOperation;

public class Divide extends AbstractOperation {

  public Divide() {
    super("/");
  }

  @Override
  public double calculate(double intA, double intB) {
    return intA / intB;
  }
}
