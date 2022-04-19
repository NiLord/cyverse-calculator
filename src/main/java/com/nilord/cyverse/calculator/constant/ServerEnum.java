package com.nilord.cyverse.calculator.constant;

import java.math.BigDecimal;
import lombok.Getter;

/**
 * Servers constants
 * 
 * @param id
 * @param name
 */
@Getter
public enum ServerEnum {
  SERVER1(1, "0.88", "9.00", "0.06"),
  SERVER2(2, "0.86", "11.00", "0.06"),
  SERVER3(3, "0.84", "16.00", "0.06"),
  SERVER4(4, "0.82", "21.00", "0.06"),
  SERVER5(5, "0.80", "26.00", "0.06"),
  SERVER6(6, "0.78", "32.00", "0.07"),
  SERVER7(7, "0.76", "38.00", "0.07"),
  SERVER8(8, "0.74", "45.00", "0.07"),
  SERVER9(9, "0.72", "52.00", "0.07"),
  SERVER10(10, "0.70", "59.00", "0.07"),
  SERVER11(11, "0.68", "67.00", "0.08"),
  SERVER12(12, "0.66", "76.00", "0.08"),
  SERVER13(13, "0.64", "85.00", "0.08"),
  SERVER14(14, "0.62", "95.00", "0.08"),
  SERVER15(15, "0.61", "105.00", "0.08"),
  SERVER16(16, "0.60", "120.00", "0.09"),
  SERVER17(17, "0.60", "135.00", "0.09"),
  SERVER18(18, "0.59", "150.00", "0.09"),
  SERVER19(19, "0.59", "167.00", "0.09"),
  SERVER20(20, "0.58", "185.00", "0.09"),
  SERVER21(21, "0.58", "205.00", "0.10"),
  SERVER22(22, "0.57", "225.00", "0.10"),
  SERVER23(23, "0.57", "245.00", "0.10"),
  SERVER24(24, "0.56", "265.00", "0.10"),
  SERVER25(25, "0.56", "285.00", "0.10"),
  SERVER26(26, "0.55", "310.00", "0.11"),
  SERVER27(27, "0.55", "330.00", "0.11"),
  SERVER28(28, "0.54", "350.00", "0.11"),
  SERVER29(29, "0.54", "375.00", "0.11"),
  SERVER30(30, "0.53", "400.00", "0.11"),
  INVALID(0, "0", "0", "0");
  
  private Integer id;
  private BigDecimal winRate;
  private BigDecimal value;
  private BigDecimal serverFee;
  
  ServerEnum(Integer id, String winRate, String value, String serverFee) {
    this.id = id;
    this.winRate = new BigDecimal(winRate);
    this.value = new BigDecimal(value);
    this.serverFee = new BigDecimal(serverFee);
  }
  
  /**
   * Find the serverEnum using its id
   * @param id server id
   * @return INVALID in case is not found or server enum
   */
  public static ServerEnum getEnumFromId(Integer id) {
    ServerEnum response = ServerEnum.INVALID;

    for (ServerEnum server : ServerEnum.values()) {
      if (server.getId() == id) {
        return server;
      }
    }

    return response;
  }

}
