package com.nilord.cyverse.calculator.util.profit.impl;

import java.math.BigDecimal;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.nilord.cyverse.calculator.error.CyverseCalculationException;
import com.nilord.cyverse.calculator.model.service.calculator.ProfitCalculatorRequestDTO;
import com.nilord.cyverse.calculator.model.service.calculator.ProfitCalculatorResponseDTO;
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

  private static final Map<Integer, BigDecimal> SERVER_WIN =
      Map.ofEntries(Map.entry(1, new BigDecimal("8.00")), Map.entry(2, new BigDecimal("10.00")),
          Map.entry(3, new BigDecimal("15.00")), Map.entry(4, new BigDecimal("20.00")),
          Map.entry(5, new BigDecimal("25.00")), Map.entry(6, new BigDecimal("31.00")),
          Map.entry(7, new BigDecimal("37.00")), Map.entry(8, new BigDecimal("44.00")),
          Map.entry(9, new BigDecimal("51.00")), Map.entry(10, new BigDecimal("58.00")),
          Map.entry(11, new BigDecimal("67.00")), Map.entry(12, new BigDecimal("76.00")),
          Map.entry(13, new BigDecimal("85.00")), Map.entry(14, new BigDecimal("95.00")),
          Map.entry(15, new BigDecimal("105.00")), Map.entry(16, new BigDecimal("120.00")),
          Map.entry(17, new BigDecimal("135.00")), Map.entry(18, new BigDecimal("150.00")),
          Map.entry(19, new BigDecimal("167.00")), Map.entry(20, new BigDecimal("185.00")),
          Map.entry(21, new BigDecimal("205.00")), Map.entry(22, new BigDecimal("225.00")),
          Map.entry(23, new BigDecimal("245.00")), Map.entry(24, new BigDecimal("265.00")),
          Map.entry(25, new BigDecimal("285.00")), Map.entry(26, new BigDecimal("310.00")),
          Map.entry(27, new BigDecimal("330.00")), Map.entry(28, new BigDecimal("350.00")),
          Map.entry(29, new BigDecimal("375.00")), Map.entry(30, new BigDecimal("400.00")));
  
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
      calculationServer = new BigDecimal(serverId - SERVER_RATE_CHANGE);
      serverVariation = RATE_DROP_TWO.multiply(calculationServer);
      response.add(serverVariation);
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

    BigDecimal response = winRate.multiply(new BigDecimal(MONTH_DAYS.toString()));

    log.info("End sucessday calculation. Sucess days value {}", response);

    return response.intValue();
  }

  @Override
  public BigDecimal getMoneyRewardPerMoth(Integer sucessDays, Integer serverId)
      throws CyverseCalculationException {

    log.info("Init money reward per month for server id {} / sucess days {}", serverId, sucessDays);

    if (!SERVER_WIN.containsKey(serverId) || sucessDays > MONTH_DAYS) {
      throw new CyverseCalculationException("Invalid server id or sucessDays has been sent");
    }

    BigDecimal rewardPerDay = SERVER_WIN.get(serverId);
    log.info("Server rewards for server {} is {}", serverId, rewardPerDay);

    BigDecimal response = rewardPerDay.multiply(new BigDecimal(sucessDays));

    log.info("End money reward per month. Response is", response);

    return response;
  }

}
