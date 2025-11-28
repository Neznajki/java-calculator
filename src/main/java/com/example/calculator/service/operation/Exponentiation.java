package com.example.calculator.service.operation;

import com.example.calculator.contract.CalculationOperationInterface;

public class Exponentiation implements CalculationOperationInterface {
  @Override
  public float calculate(float a, float b) {
    if (b < 0) {
      throw new ArithmeticException("power in negative state not allowed");
    }

    return (float) Math.pow(a, b);
  }
}
