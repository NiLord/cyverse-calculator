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
  SERVER1(1, "9.00", "0.06"),
  SERVER2(2, "11.00", "0.06"),
  SERVER3(3, "16.00", "0.06"),
  SERVER4(4, "21.00", "0.06"),
  SERVER5(5, "26.00", "0.06"),
  SERVER6(6, "32.00", "0.07"),
  SERVER7(7, "38.00", "0.07"),
  SERVER8(8, "45.00", "0.07"),
  SERVER9(9, "52.00", "0.07"),
  SERVER10(10, "59.00", "0.07"),
  SERVER11(11, "67.00", "0.08"),
  SERVER12(12, "76.00", "0.08"),
  SERVER13(13, "85.00", "0.08"),
  SERVER14(14, "95.00", "0.08"),
  SERVER15(15, "105.00", "0.08"),
  SERVER16(16, "120.00", "0.09"),
  SERVER17(17, "135.00", "0.09"),
  SERVER18(18, "150.00", "0.09"),
  SERVER19(19, "167.00", "0.09"),
  SERVER20(20, "185.00", "0.09"),
  SERVER21(21, "205.00", "0.10"),
  SERVER22(22, "225.00", "0.10"),
  SERVER23(23, "245.00", "0.10"),
  SERVER24(24, "265.00", "0.10"),
  SERVER25(25, "285.00", "0.10"),
  SERVER26(26, "310.00", "0.11"),
  SERVER27(27, "330.00", "0.11"),
  SERVER28(28, "350.00", "0.11"),
  SERVER29(29, "375.00", "0.11"),
  SERVER30(30, "400.00", "0.11"),
  INVALID(0, "0", "0");
  
  private Integer id;
  private BigDecimal value;
  private BigDecimal serverFee;
  
  ServerEnum(Integer id, String value, String serverFee) {
    this.id = id;
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
