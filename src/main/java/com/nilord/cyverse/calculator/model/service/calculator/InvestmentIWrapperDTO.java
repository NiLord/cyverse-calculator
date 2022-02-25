package com.nilord.cyverse.calculator.model.service.calculator;

import java.math.BigDecimal;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Request entity for investment monthly information")
public class InvestmentIWrapperDTO {
  
  @Schema(description = "Backdoor fee monthly amount", example = "49.75", required = false)
  private BigDecimal backdoorFeeMonthly;
  
  @Schema(description = "Server fee monthly amount", example = "300.25", required = false)
  private BigDecimal serverFeeMonthly;
  
  @Schema(description = "Total amount", example = "350.00", required = false)
  private BigDecimal montlyExpense;

}
