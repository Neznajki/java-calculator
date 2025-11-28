package com.example.calculator;

import com.example.calculator.service.CalculatorService;
import com.example.calculator.service.ExtractorService;
import com.example.calculator.service.ValidatorService;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CalculatorApplication {
  private final ExtractorService extractorService = new ExtractorService();
  private final CalculatorService calculatorService = new CalculatorService();
  private final ValidatorService validatorService = new ValidatorService(calculatorService);

    public static void main(String[] args) {
      var calculatorApplication = new CalculatorApplication();
      try {
        System.out.println(calculatorApplication.doExecute(args));
      } catch (Exception e) {
        System.err.println(e.getMessage());
      }
    }

    public String doExecute(String[] args) {
      validatorService.validate(args);
      var calcOptions = extractorService.extract(args);

      var resp = calculatorService.calculate(calcOptions);

      return String.format("%.4f", resp).replaceAll("\\.?0+$", "");
    }
}
