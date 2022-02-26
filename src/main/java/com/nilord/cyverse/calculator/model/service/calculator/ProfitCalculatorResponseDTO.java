package com.nilord.cyverse.calculator.model.service.calculator;

import java.io.Serializable;
import java.math.BigDecimal;
import com.nilord.cyverse.calculator.model.service.domain.TimeAmountWrapperDTO;
import com.nilord.cyverse.calculator.model.service.domain.InvestmentIWrapperDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Response entity for profit calculator propose")
public class ProfitCalculatorResponseDTO implements Serializable {
  
  private static final long serialVersionUID = -5078363076027051841L;

  @Schema(description = "Sucess calculation", required = false)
  private Boolean sucess;
  
  @Schema(description = "Server id", required = false)
  private Integer serverId;
  
  @Schema(description = "Winrate", required = false)
  private BigDecimal winRate;
  
  @Schema(description = "Server win amount", required = false)
  private BigDecimal serverAmount;
  
  @Schema(description = "Sucess days per month", required = false)
  private Integer sucessDaysPerMonth;
  
  @Schema(description = "Sucess money reward per month", required = false)
  private BigDecimal sucessMoneyRewardPerMonth;
  
  @Schema(description = "Investment information", required = false)
  private InvestmentIWrapperDTO investment; 
  
  @Schema(description = "Estimated profits", required = false)
  private TimeAmountWrapperDTO estimatedProfits;

}
