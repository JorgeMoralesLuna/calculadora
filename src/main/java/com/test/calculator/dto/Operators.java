package com.test.calculator.dto;

public enum Operators {

  SUM('+'),
  SUBTRACT('-');

  private final char operator;

  Operators(char operator) {
    this.operator = operator;
  }

  public char getOperator() {
    return operator;
  }
}
