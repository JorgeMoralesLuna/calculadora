package com.test.calculator.service;

import io.corp.calculator.TracerImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CalculatorServiceTest {

  private static final String SUM_OPERATION = "3.2+2";
  private static final Double SUM_RESULT = Double.parseDouble("5.2");
  private static final String INVALID_OPERATION = "2+e";

  CalculatorService calculatorService;

  @Mock
  TracerImpl tracer;

  @BeforeEach
  void setUp() {
    calculatorService = new CalculatorService(tracer);
  }

  @Test
  void invalidOperationTest() {
    Assertions.assertThrows(RuntimeException.class, () -> calculatorService.calculate(INVALID_OPERATION));
  }

  @Test
  void calculateWithDecimal_OK() {
    Assertions.assertEquals(SUM_RESULT, calculatorService.calculate(SUM_OPERATION));
  }

}
