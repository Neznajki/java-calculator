package com.example.calculator;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {

    @GetMapping
    public Result calculate(
            @RequestParam BigDecimal a,
            @RequestParam BigDecimal b,
            @RequestParam(defaultValue = "add") String op
    ) {
        BigDecimal result;
        switch (op.toLowerCase()) {
            case "add" -> result = a.add(b);
            case "sub", "subtract" -> result = a.subtract(b);
            case "mul", "multiply" -> result = a.multiply(b);
            case "div", "divide" -> {
                if (b.compareTo(BigDecimal.ZERO) == 0) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Division by zero");
                }
                result = a.divide(b, 10, RoundingMode.HALF_UP);
            }
            default -> throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Unsupported op. Use one of: add, sub|subtract, mul|multiply, div|divide"
            );
        }
        return new Result(a, b, op.toLowerCase(), result);
    }

    public record Result(BigDecimal a, BigDecimal b, String op, BigDecimal result) {}
}
