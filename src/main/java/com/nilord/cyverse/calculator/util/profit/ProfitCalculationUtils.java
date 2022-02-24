package com.nilord.cyverse.calculator.util.profit;

import java.math.BigDecimal;
import com.nilord.cyverse.calculator.error.CyverseCalculationException;
import com.nilord.cyverse.calculator.model.service.calculator.ProfitCalculatorRequestDTO;
import com.nilord.cyverse.calculator.model.service.calculator.ProfitCalculatorResponseDTO;

public interface ProfitCalculationUtils {

  /**
   * Get all calculations at once
   * 
   * @param request All necesary data for calculation
   * @return all calculations 
   * @throws CyverseCalculationException Invalid data is sent
   */
  public ProfitCalculatorResponseDTO getProfitCalculatorWrapper(ProfitCalculatorRequestDTO request)
      throws CyverseCalculationException;

  /**
   * Calculate the best server id using the hacking power
   * 
   * @param hackingPower
   * @return server id
   * @throws CyverseCalculationException Invalid data is sent
   */
  public Integer getServerId(Integer hackingPower) throws CyverseCalculationException;
  
  
  /**
   * Calculate the win rate for the actual server
   * 
   * @param serverId server id
   * @return the win rate
   * @throws CyverseCalculationException Invalid data is sent
   */
  public BigDecimal getWinRate(Integer serverId) throws CyverseCalculationException;
  
  /**
   * Calculate the sucess days in a month
   * 
   * @param winRate server win rate
   * @return sucess winner days
   * @throws CyverseCalculationException Invalid data is sent
   */
  public Integer getSucessDays(BigDecimal winRate) throws CyverseCalculationException;
  
  /**
   * Money rewards per month
   * @param sucessDays number of sucess days
   * @param serverId server id
   * @return sucess money reward per month
   * @throws CyverseCalculationException
   */
  public BigDecimal getMoneyRewardPerMoth(Integer sucessDays, Integer serverId) throws CyverseCalculationException;

}
