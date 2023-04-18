package com.test.calculator.service;

import com.test.calculator.dto.Operators;
import io.corp.calculator.TracerImpl;
import java.util.Stack;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class CalculatorService {

  private static final String OPERATION_PATTERN = "^\\d+(\\.\\d+)?([\\+-]\\d+(\\.\\d+)?)*$";
  private static final char DOT_CHARACTER = '.';
  private final TracerImpl tracer;

  public Double calculate(String operation) {
    if(!isIntoPattern(operation)) {
      log.error("Invalid operation {}", operation);
      throw new RuntimeException("Enter a valid number addition and/or subtraction operation. Avoid spaces and encode the '+' character.");
    }
    return resolveOperation(operation);

  }

  private boolean isIntoPattern(String operation) {
    log.info("Checking the validity of the chain: {}", operation);
    return operation.matches(OPERATION_PATTERN);
  }


  private double resolveOperation(String operation) {
    double result;
    log.info("Performing operation {}", operation);
    Stack<Double> numbers = new Stack<>();
    Stack<Character> operators = new Stack<>();

    for(int i=0; i<operation.length(); i++) {
      char c = operation.charAt(i);
      if(Character.isDigit(c) || c == DOT_CHARACTER) {
        StringBuilder num = new StringBuilder();
        num.append(c);
        while (i + 1 < operation.length() && (Character.isDigit(operation.charAt(i + 1)) || operation.charAt(i + 1) == DOT_CHARACTER)) {
          num.append(operation.charAt(i + 1));
          i++;
        }
        numbers.push(Double.parseDouble(num.toString()));
      }else if (c == Operators.SUM.getOperator() || c == Operators.SUBTRACT.getOperator()){
        while (!operators.isEmpty() && (operators.peek() == Operators.SUM.getOperator() || operators.peek() == Operators.SUBTRACT.getOperator())) {
          char op = operators.pop();
          double num2 = numbers.pop();
          double num1 = numbers.pop();
          if(op == Operators.SUM.getOperator()) {
            numbers.push(num1+num2);
          } else if (op == Operators.SUBTRACT.getOperator()) {
            numbers.push(num1-num2);
          }
        }
        operators.push(c);
      }
    }

    while (!operators.isEmpty()) {
      char op = operators.pop();
      double num2 = numbers.pop();
      double num1 = numbers.pop();
      if(op == Operators.SUM.getOperator()) {
        numbers.push(num1+num2);
      } else if (op == Operators.SUBTRACT.getOperator()) {
        numbers.push(num1-num2);
      }
    }
    result = numbers.pop();
    tracer.trace(result);
    return result;
  }
}
