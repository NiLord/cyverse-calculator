package com.nilord.cyverse.calculator.model.service.domain;

import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Entity for investment monthly information")
public class InvestmentIWrapperDTO implements Serializable {
  
  private static final long serialVersionUID = 5229691231613547980L;

  @Schema(description = "Backdoor fee amount", example = "49.75", required = false)
  private TimeAmountWrapperDTO backdoorFee;
  
  @Schema(description = "Server fee amount", example = "300.25", required = false)
  private TimeAmountWrapperDTO serverFee;
  
  @Schema(description = "Total amount", example = "350.00", required = false)
  private TimeAmountWrapperDTO expense;

}
