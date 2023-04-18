package com.test.calculator.controller;

import com.test.calculator.service.CalculatorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/calculator")
public class CalculatorController {

  private final CalculatorService calculatorService;

  @Operation(summary = "calculate the incoming operation", description = "calculate the incoming operation",
    parameters =  {
      @Parameter(in = ParameterIn.PATH, name = "operation", description = "The operation for calculate",
          example = "2+3-2.1+5-1", schema = @Schema(type = "String"))})
  @ApiResponses(value = {
      @ApiResponse(description = "Successful operation", responseCode = "200"),
      @ApiResponse(description = "Invalid operation", responseCode = "400")
  })
  @GetMapping("/calculate")
  public ResponseEntity<String> calculate(@RequestParam(value = "operation", defaultValue = "") String operation) {
    Double result = null;
    try {
      result = calculatorService.calculate(operation);
      return new ResponseEntity<>(String.valueOf(result), HttpStatus.ACCEPTED);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }
}
