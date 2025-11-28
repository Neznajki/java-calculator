package com.example.calculator.service.operation;

import com.example.calculator.contract.CalculationOperationInterface;

public class Multiplication implements CalculationOperationInterface {
  @Override
  public double calculate(double a, double b) {
    return a * b;
  }
}
