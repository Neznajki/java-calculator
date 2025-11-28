package com.example.calculator.service;

import com.example.calculator.contract.CalculationOperationInterface;
import com.example.calculator.dto.CalcOptionsDTO;
import com.example.calculator.service.operation.*;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CalculatorService {
  private final Map<String, CalculationOperationInterface> operations =
      Map.of(
          "+", new Addition(),
          "-", new Subtraction(),
          "*", new Multiplication(),
          "/", new Division(),
          "%", new Modulus(),
          "^", new Exponentiation()
      );

  public double calculate(CalcOptionsDTO  calcOptionsDTO) {
    return operations.get(calcOptionsDTO.operation()).calculate(calcOptionsDTO.a(), calcOptionsDTO.b());
  }

  public Set<String> getSupportedOperations() {
    return operations.keySet().stream().sorted().collect(Collectors.toCollection(LinkedHashSet::new));
  }
}
