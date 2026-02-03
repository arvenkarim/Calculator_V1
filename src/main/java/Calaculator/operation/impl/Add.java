package Calaculator.operation.impl;

import Calaculator.operation.AbstractOperation;

public class Add extends AbstractOperation {
  public Add() {
    super('+');
  }

  @Override
  public double calculate(double a, double b) {
    return a + b;
  }
}
