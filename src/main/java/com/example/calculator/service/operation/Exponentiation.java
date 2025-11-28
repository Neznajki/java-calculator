package com.example.calculator.service.operation;

import com.example.calculator.contract.CalculationOperationInterface;

public class Exponentiation implements CalculationOperationInterface {
  @Override
  public double calculate(double a, double b) {
    if (b < 0) {
      throw new ArithmeticException("power in negative state not allowed");
    }

    return Math.pow(a, b);
  }
}
