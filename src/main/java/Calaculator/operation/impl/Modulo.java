package Calaculator.operation.impl;

import Calaculator.operation.AbstractOperation;

public class Modulo extends AbstractOperation {

  public Modulo() {
    super("%");
  }

  @Override
  public double calculate(double intA, double intB) {
    return intA % intB;
  }
}
