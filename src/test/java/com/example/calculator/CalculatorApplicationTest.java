package com.example.calculator;

import com.github.stefanbirkner.systemlambda.SystemLambda;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CalculatorApplicationTest {

    @ParameterizedTest
    @CsvSource(value = {
        "2;3;+;5;",
        "5;3;-;2;",
        "4.2;2.2;-;2;",
        "100;0;/;;Division by zero in division operation",
        "100;0;%;;Division by zero in modulus operation",
        "25;4;%;1;",
        "26;4;%;2;",
        "25;4;/;6.25;",
        "1;3;/;0.3333;",
        "2;3;/;0.6667;",
        "2;5;^;32;",
        "2;5;*;10;",
        "2;0;^;1;",
        "2;-1;^;32;power in negative state not allowed",//not in requirements but why not?
        "2;5;r;32;operator not supported: r supports only (%, *, +, -, /, ^)",
        "2;5;;10;Operator is required",
        "2;;*;10;B is required",
        ";2;*;10;A is required",
        "2t;0s;^;1;a must be numeric: 2t, b must be numeric: 0s",
        "2v;0;^;1;a must be numeric: 2v",
        "2;0g;^;1;b must be numeric: 0g",
        " ; ; ;1;A is required, B is required, Operator is required",
    }, delimiter = ';')
    public void testCalc(String a, String b, String operation, String result, String error) {
      var app = new CalculatorApplication();
      try {
        String[] args = {a, b, operation};
        var response = app.doExecute(args);
        Assertions.assertThat(response).isEqualTo(result);
      } catch (Exception e) {
        Assertions.assertThat(e.getMessage()).isEqualTo(error);
      }
    }

    @Test
    public void testApp() throws Exception {
      String output = SystemLambda.tapSystemOut(() -> {
        String[] ints = {"1", "2", "+"};
        CalculatorApplication.main(ints);
      });

      Assertions.assertThat(output.trim()).isEqualTo("3");

      output = SystemLambda.tapSystemErr(() -> {
        String[] ints = {"11", "0", "/"};
        CalculatorApplication.main(ints);
      });

      Assertions.assertThat(output.trim()).isEqualTo("Division by zero in division operation");

      output = SystemLambda.tapSystemErr(() -> {
        String[] ints = {"11", "0"};
        CalculatorApplication.main(ints);
      });

      Assertions.assertThat(output.trim()).isEqualTo("Expected 3 arguments: <a> <b> <operator> got: 2");

      output = SystemLambda.tapSystemErr(() -> {
        String[] ints = {"11", "0", "11", "11"};
        CalculatorApplication.main(ints);
      });

      Assertions.assertThat(output.trim()).isEqualTo("Expected 3 arguments: <a> <b> <operator> got: 4");
    }
}
