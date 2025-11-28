package com.example.calculator.service.operation;

import com.example.calculator.contract.CalculationOperationInterface;

public class Addition implements CalculationOperationInterface {
  @Override
  public float calculate(float a, float b) {
    return a+b;
  }
}
