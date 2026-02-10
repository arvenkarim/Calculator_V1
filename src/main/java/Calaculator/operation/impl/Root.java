package Calaculator.operation.impl;

import Calaculator.operation.AbstractOperation;

public class Root extends AbstractOperation {

    public Root() {
        super('r');
    }

    @Override
    public double calculate(double a, double b) {
        return Math.sqrt(a);
    }
}
