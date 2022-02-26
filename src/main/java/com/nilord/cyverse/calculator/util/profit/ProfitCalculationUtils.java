package com.nilord.cyverse.calculator.util.profit;

import java.math.BigDecimal;
import com.nilord.cyverse.calculator.error.CyverseCalculationException;
import com.nilord.cyverse.calculator.model.service.calculator.ProfitCalculatorRequestDTO;
import com.nilord.cyverse.calculator.model.service.calculator.ProfitCalculatorResponseDTO;
import com.nilord.cyverse.calculator.model.service.domain.TimeAmountWrapperDTO;
import com.nilord.cyverse.calculator.model.service.domain.InvestmentIWrapperDTO;

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
   * 
   * @param sucessDays number of sucess days
   * @param serverId server id
   * @return sucess money reward per month
   * @throws CyverseCalculationException Invalid data is sent
   */
  public BigDecimal getMoneyRewardPerMoth(Integer sucessDays, Integer serverId)
      throws CyverseCalculationException;

  /**
   * Calculate backdoor fee in a month
   * 
   * @param drivers number of drivers
   * @return total drivers month investment
   * @throws CyverseCalculationException Invalid data is sent
   */
  public TimeAmountWrapperDTO getBackDoorFee(Integer drivers) throws CyverseCalculationException;

  /**
   * Calculate server feed in a month
   * 
   * @param pods number of pods
   * @return total pods investment
   * @throws CyverseCalculationException Invalid data is sent
   */
  public TimeAmountWrapperDTO getServerFee(Integer serverId) throws CyverseCalculationException;

  /**
   * Returns total investment in a single month
   * 
   * @param drivers number of drivers
   * @param serverId server id
   * @return total pods investment wrapper
   * @throws CyverseCalculationException Invalid data is sent
   */
  public InvestmentIWrapperDTO getTotalInvestment(Integer drivers, Integer serverId)
      throws CyverseCalculationException;

  /**
   * Calculate the estimated profits for the current data
   * 
   * @param totalInvestment total investment in a single month
   * @param totalEarn total earn in a month
   * @return profits data (monthly, weekly, daily)
   * @throws CyverseCalculationException Invalid data is sent
   */
  public TimeAmountWrapperDTO getProfits(BigDecimal totalInvestment, BigDecimal totalEarn)
      throws CyverseCalculationException;

  /**
   * Return the amount of money you can win in the actual server
   * 
   * @param server to check
   * @return the amount
   * @throws CyverseCalculationException Invalid data is sent
   */
  public BigDecimal getServerAmount(Integer serverId) throws CyverseCalculationException;

}
