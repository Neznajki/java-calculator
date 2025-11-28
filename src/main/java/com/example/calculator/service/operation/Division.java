package com.example.calculator.service.operation;

import com.example.calculator.contract.CalculationOperationInterface;

public class Division implements CalculationOperationInterface {
  @Override
  public float calculate(float a, float b) {
    if (b == 0) {
      throw new IllegalArgumentException("Division by zero in division operation");
    }

    return a / b;
  }
}
