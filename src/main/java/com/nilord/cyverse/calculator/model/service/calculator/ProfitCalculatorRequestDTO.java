  package com.nilord.cyverse.calculator.model.service.calculator;

import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Request entity for profit calculator propose")
public class ProfitCalculatorRequestDTO implements Serializable {
  
  private static final long serialVersionUID = -2904182010009527700L;
  
  @Schema(description = "Divers amount", example = "16", required = true)
  private Integer divers;
  
  @Schema(description = "Pods amount", example = "1400", required = true)
  private Integer hackingPower;
  
}
