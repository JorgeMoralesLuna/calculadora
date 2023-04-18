package com.test.calculator.controller;

import com.test.calculator.service.CalculatorService;
import io.corp.calculator.TracerImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class CalculatorControllerTest {

  CalculatorController calculatorController;
  @Mock
  CalculatorService calculatorService;

  @Mock
  TracerImpl tracer;

  private static final String SUM_OPERATION = "3+2";
  private static final Double SUM_RESULT = Double.parseDouble("5");

  @BeforeEach
  void setUp() {
    calculatorController = new CalculatorController(calculatorService);
  }

  @Test
  void sumTest_OK() {
    org.mockito.Mockito.when(calculatorService.calculate(SUM_OPERATION)).thenReturn(SUM_RESULT);
    ResponseEntity expected = new ResponseEntity<>(String.valueOf(SUM_RESULT), HttpStatus.ACCEPTED);
    Assertions.assertEquals(expected, calculatorController.calculate(SUM_OPERATION));
  }


}
