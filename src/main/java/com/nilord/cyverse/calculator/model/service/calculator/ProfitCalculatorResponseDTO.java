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
  
  @Schema(description = "Sucess calculation", required = false)
  private Boolean sucess;
  
  @Schema(description = "Server id", required = false)
  private Integer serverId;
  
  @Schema(description = "Winrate", required = false)
  private BigDecimal winRate;
  
  @Schema(description = "Sucess days per month", required = false)
  private Integer sucessDaysPerMonth;
  
  @Schema(description = "Sucess money reward per month", required = false)
  private BigDecimal sucessMoneyRewardPerMonth;
  
  @Schema(description = "Investment information", required = false)
  private InvestmentIWrapperDTO investment; 
  
  @Schema(description = "Monthly profits", required = false)
  private BigDecimal monthlyProfits;

}
