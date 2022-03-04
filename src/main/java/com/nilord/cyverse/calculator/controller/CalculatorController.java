package com.nilord.cyverse.calculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.nilord.cyverse.calculator.command.ProfitCalculatorCommand;
import com.nilord.cyverse.calculator.model.service.calculator.ProfitCalculatorRequestDTO;
import com.nilord.cyverse.calculator.model.service.calculator.ProfitCalculatorResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/calculator")
@Tag(name = "Cyverse calculator controller")
public class CalculatorController {

  @Autowired
  private ProfitCalculatorCommand profitCalculatorCommand;

  /**
   * Reponse the profit calculator
   * 
   * @param request data required for the calculation
   * @return calculation response wrapper for the sent data
   */
  @RequestMapping(value = "/profit", method = RequestMethod.GET,
      produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = MediaType.ALL_VALUE)
  public ProfitCalculatorResponseDTO calculateProfit(ProfitCalculatorRequestDTO request) {
    return profitCalculatorCommand.calculate(request);
  }

}
