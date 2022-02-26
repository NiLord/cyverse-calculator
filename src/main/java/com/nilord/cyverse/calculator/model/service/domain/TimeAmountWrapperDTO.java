package com.nilord.cyverse.calculator.model.service.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Entity for investment monthly information")
public class TimeAmountWrapperDTO implements Serializable {

  private static final long serialVersionUID = 3054389933212836439L;
  
  @Schema(description = "Estimated monthly profit", example = "300.00", required = false)
  private BigDecimal monthly;
  
  @Schema(description = "Estimated weekly profit", example = "100.00", required = false)
  private BigDecimal weekly;
  
  @Schema(description = "Estimated daily profit", example = "10.00", required = false)
  private BigDecimal daily;

}
