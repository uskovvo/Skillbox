package ru.skillbox;

public class ArithmeticCalculator {

    private final int a;
    private final int b;
    private double result;

    public ArithmeticCalculator(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public double getResult() {
        return result;
    }

    public void calculate(Operation operation) {
        if (operation == Operation.ADD) {
            result = a + b;
        }

        if (operation == Operation.SUBTRACT) {
            result = (double) a / b;
        }

        if (operation == Operation.MULTIPLY) {
            result = a * b;
        }
    }
}
