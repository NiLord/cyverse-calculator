package com.nilord.cyverse.calculator.command;

import com.nilord.cyverse.calculator.error.CyverseCalculationException;
import com.nilord.cyverse.calculator.model.service.calculator.ProfitCalculatorRequestDTO;
import com.nilord.cyverse.calculator.model.service.calculator.ProfitCalculatorResponseDTO;

/**
 * Interface to profit calculator command
 * 
 * @author nilord
 *
 */
public interface ProfitCalculatorCommand {

  /**
   * Profit calculation command method
   * 
   * @param request necesary data for calculation
   * @return calculation data
   * @throws CyverseCalculationException
   */
  public ProfitCalculatorResponseDTO calculate(ProfitCalculatorRequestDTO request)
      throws CyverseCalculationException;

}
