package com.nilord.cyverse.calculator.model.service.calculator;

import java.math.BigDecimal;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Response entity for profit calculator propose")
public class ProfitCalculatorResponseDTO {
  
  private Boolean sucess;
  
  private Integer serverId;
  
  private BigDecimal winRate;
  
  private Integer sucessDaysPerMonth;
  
  private BigDecimal sucessMoneyRewardPerMonth;

}
