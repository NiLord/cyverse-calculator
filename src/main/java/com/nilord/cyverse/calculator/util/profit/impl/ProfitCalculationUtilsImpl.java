package com.nilord.cyverse.calculator.util.profit.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Component;
import com.nilord.cyverse.calculator.constant.ServerEnum;
import com.nilord.cyverse.calculator.error.CyverseCalculationException;
import com.nilord.cyverse.calculator.model.service.calculator.ProfitCalculatorRequestDTO;
import com.nilord.cyverse.calculator.model.service.calculator.ProfitCalculatorResponseDTO;
import com.nilord.cyverse.calculator.model.service.domain.TimeAmountWrapperDTO;
import com.nilord.cyverse.calculator.model.service.domain.InvestmentIWrapperDTO;
import com.nilord.cyverse.calculator.util.profit.ProfitCalculationUtils;
import lombok.extern.slf4j.Slf4j;

@Component("ProfitCalculationUtils")
@Slf4j
public class ProfitCalculationUtilsImpl implements ProfitCalculationUtils {

  /** Init> Parameters should live in a data base **/
  private static final Integer MAX_SERVER = 30;
  private static final Integer MIN_HP = 100;

  private static final BigDecimal INITIAL_WIN_RATE = new BigDecimal("0.90");
  private static final BigDecimal RATE_DROP_ONE = new BigDecimal("0.02");
  private static final BigDecimal RATE_DROP_TWO = new BigDecimal("0.01");
  private static final Integer SERVER_RATE_CHANGE = 17;

  private static final Integer MONTH_DAYS = 30;

  private static final BigDecimal BACKDOOR_FEE = new BigDecimal("1");

  /** End> Parameters should live in a data base **/

  @Override
  public ProfitCalculatorResponseDTO getProfitCalculatorWrapper(ProfitCalculatorRequestDTO request)
      throws CyverseCalculationException {
    ProfitCalculatorResponseDTO response = new ProfitCalculatorResponseDTO();

    response.setServerId(this.getServerId(request.getHackingPower()));
    response.setWinRate(this.getWinRate(response.getServerId()));
    response.setSucessDaysPerMonth(this.getSucessDays(response.getWinRate()));
    response.setSucessMoneyRewardPerMonth(
        this.getMoneyRewardPerMoth(response.getSucessDaysPerMonth(), response.getServerId()));
    response.setInvestment(this.getTotalInvestment(request.getDivers(), response.getServerId()));
    response.setEstimatedProfits(this.getProfits(response.getInvestment().getExpense().getMonthly(),
        response.getSucessMoneyRewardPerMonth()));
    response.setServerAmount(this.getServerAmount(response.getServerId()));
    response.setSucess(true);

    return response;
  }

  @Override
  public Integer getServerId(Integer hackingPower) throws CyverseCalculationException {

    log.info("Init server id calculation for hacking power {}", hackingPower);

    if (hackingPower < MIN_HP) {
      throw new CyverseCalculationException("Hacking power must be greater than 100");
    }

    Integer response = hackingPower / MIN_HP;
    response = (response > MAX_SERVER) ? MAX_SERVER : response;

    log.info("End server id calculation. Server id response is {}", response);

    return response;
  }

  @Override
  public BigDecimal getWinRate(Integer serverId) throws CyverseCalculationException {

    log.info("Init win rate calculation for server id {}", serverId);

    if (serverId < 0 || serverId > MAX_SERVER) {
      throw new CyverseCalculationException("Invalid server id has been sent");
    }

    BigDecimal calculationServer = new BigDecimal(serverId - 1);
    BigDecimal serverVariation = RATE_DROP_ONE.multiply(calculationServer);

    BigDecimal response = INITIAL_WIN_RATE.subtract(serverVariation);

    // Check for special variation
    if (serverId >= SERVER_RATE_CHANGE) {
      calculationServer = new BigDecimal(serverId + 1 - SERVER_RATE_CHANGE);
      serverVariation = RATE_DROP_TWO.multiply(calculationServer);
      response = response.add(serverVariation);
    }

    log.info("End win rate calculation. Win rate is {}", response);

    return response;
  }

  @Override
  public Integer getSucessDays(BigDecimal winRate) throws CyverseCalculationException {

    log.info("Init sucessday calculation for winrate {}", winRate);

    if (winRate.compareTo(BigDecimal.ZERO) <= 0 || winRate.compareTo(INITIAL_WIN_RATE) > 0) {
      throw new CyverseCalculationException("Invalid winrate has been sent");
    }

    final BigDecimal response = winRate.multiply(new BigDecimal(MONTH_DAYS.toString()));

    log.info("End sucessday calculation. Sucess days value {}", response);

    return response.intValue();
  }

  @Override
  public BigDecimal getMoneyRewardPerMoth(Integer sucessDays, Integer serverId)
      throws CyverseCalculationException {

    log.info("Init money reward per month for server id {} / sucess days {}", serverId, sucessDays);

    final ServerEnum serverEnum = ServerEnum.getEnumFromId(serverId);

    if (ServerEnum.INVALID.equals(serverEnum) || sucessDays > MONTH_DAYS) {
      throw new CyverseCalculationException("Invalid server id or sucessDays has been sent");
    }

    final BigDecimal rewardPerDay = serverEnum.getValue();
    log.info("Server rewards for server {} is {}", serverId, rewardPerDay);

    final BigDecimal response = rewardPerDay.multiply(new BigDecimal(sucessDays));

    log.info("End money reward per month. Response is", response);

    return response;
  }

  @Override
  public TimeAmountWrapperDTO getBackDoorFee(Integer divers)
      throws CyverseCalculationException {
    final TimeAmountWrapperDTO response = new TimeAmountWrapperDTO();

    response.setMonthly(
        BACKDOOR_FEE.multiply(new BigDecimal(divers)).multiply(new BigDecimal(MONTH_DAYS)));
    this.completeTimeDetails(response);

    return response;
  }

  @Override
  public TimeAmountWrapperDTO getServerFee(Integer serverId)
      throws CyverseCalculationException {
    log.info("Init get server fee for server {}", serverId);

    final ServerEnum serverEnum = ServerEnum.getEnumFromId(serverId);

    if (ServerEnum.INVALID.equals(serverEnum)) {
      throw new CyverseCalculationException("Invalid server id has been sent");
    }

    final TimeAmountWrapperDTO response = new TimeAmountWrapperDTO();

    response.setMonthly(serverEnum.getValue().multiply(serverEnum.getServerFee())
        .multiply(new BigDecimal(MONTH_DAYS)));
    this.completeTimeDetails(response);

    log.info("End get server fee. Response is {}", response);

    return response;
  }

  @Override
  public InvestmentIWrapperDTO getTotalInvestment(Integer divers, Integer serverId)
      throws CyverseCalculationException {
    final InvestmentIWrapperDTO response = new InvestmentIWrapperDTO();

    response.setBackdoorFee(this.getBackDoorFee(divers));
    response.setServerFee(this.getServerFee(serverId));

    final TimeAmountWrapperDTO totalExpense = new TimeAmountWrapperDTO();
    totalExpense.setMonthly(
        response.getBackdoorFee().getMonthly().add(response.getServerFee().getMonthly()));
    this.completeTimeDetails(totalExpense);

    response.setExpense(totalExpense);

    return response;
  }

  @Override
  public TimeAmountWrapperDTO getProfits(BigDecimal totalInvestment, BigDecimal totalEarn)
      throws CyverseCalculationException {
    log.info("Init get profits for totalInvestment {} and totalEarn {}", totalInvestment,
        totalEarn);

    final TimeAmountWrapperDTO response = new TimeAmountWrapperDTO();

    response.setMonthly(totalEarn.subtract(totalInvestment));
    this.completeTimeDetails(response);

    return response;
  }

  @Override
  public BigDecimal getServerAmount(Integer serverId) throws CyverseCalculationException {
    log.info("Init get server amount for server {}", serverId);

    final ServerEnum serverEnum = ServerEnum.getEnumFromId(serverId);

    if (ServerEnum.INVALID.equals(serverEnum)) {
      throw new CyverseCalculationException("Invalid server id has been sent");
    }

    return serverEnum.getValue();
  }
  
  /*********************** PRIVATE METHODS ***********************/

  /**
   * Complete daily and weekly details
   * 
   * @param response
   */
  private void completeTimeDetails(TimeAmountWrapperDTO response) {
    response.setWeekly(response.getMonthly().multiply(new BigDecimal("7"))
        .divide(new BigDecimal(MONTH_DAYS), 2, RoundingMode.HALF_UP));
    response.setDaily(response.getMonthly().multiply(new BigDecimal("1"))
        .divide(new BigDecimal(MONTH_DAYS), 2, RoundingMode.HALF_UP));
  }

}
