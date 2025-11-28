package com.example.calculator.service;

import lombok.RequiredArgsConstructor;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ValidatorService {
  private final CalculatorService calculatorService;

  public void validate(String[] args) {
    if (args.length != 3) {
      throw new InvalidParameterException("Expected 3 arguments: <a> <b> <operator> got: %d".formatted(args.length));
    }

    List<String> errors = new ArrayList<>();
    String a = args[0];
    String b = args[1];
    String operator = args[2];

    if (a == null || a.isBlank()) {
      errors.add("A is required");
    } else {
      if (notNumeric(a)) {
        errors.add("a must be numeric: " + a);
      }
    }

    if (b == null || b.isBlank()) {
      errors.add("B is required");
    } else {
      if (notNumeric(b)) {
        errors.add("b must be numeric: " + b);
      }
    }

    if (operator == null || operator.isBlank()) {
      errors.add("Operator is required");
    } else {
      if (! calculatorService.getSupportedOperations().contains(operator)) {
        errors.add("operator not supported: %s supports only (%s)".formatted(operator, String.join(", ", calculatorService.getSupportedOperations())));
      }
    }

    if ((long) errors.size() > 0) {
      throw new InvalidParameterException(String.join(", ", errors));
    }
  }

  private static boolean notNumeric(String value) {
    try {
      Float.parseFloat(value);
      return false;
    } catch (NumberFormatException e) {
      return true;
    }
  }
}
