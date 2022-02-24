package com.nilord.cyverse.calculator.command.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.nilord.cyverse.calculator.command.ProfitCalculatorCommand;
import com.nilord.cyverse.calculator.error.CyverseCalculationException;
import com.nilord.cyverse.calculator.model.service.calculator.ProfitCalculatorRequestDTO;
import com.nilord.cyverse.calculator.model.service.calculator.ProfitCalculatorResponseDTO;
import com.nilord.cyverse.calculator.util.profit.ProfitCalculationUtils;

@Component("ProfitCalculatorCommand")
public class ProfitCalculatorCommandImpl implements ProfitCalculatorCommand {
  
  @Autowired
  private ProfitCalculationUtils profitCalculationUtils;

  @Override
  public ProfitCalculatorResponseDTO calculate(ProfitCalculatorRequestDTO request)
      throws CyverseCalculationException {
    
    return profitCalculationUtils.getProfitCalculatorWrapper(request);
  }

}
