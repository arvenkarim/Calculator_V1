package Calaculator.operation.impl;

import Calaculator.operation.AbstractOperation;

public class Power extends AbstractOperation {

  public Power(){
    super("^");
  }

  @Override
  public double calculate(double intA, double intB) {
    return Math.pow(intA, intB);
  }
}
