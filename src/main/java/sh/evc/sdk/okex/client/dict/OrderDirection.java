package sh.evc.sdk.okex.client.dict;

/**
 * 下单方式
 *
 * @author winixi
 * @date 2021/1/4 5:11 PM
 */
public enum OrderDirection {

  /**
   * 开多
   */
  OPEN_LONG("1"),

  /**
   * 开空
   */
  OPEN_SHORT("2"),

  /**
   * 平多
   */
  CLOSE_LONG("3"),

  /**
   * 平空
   */
  CLOSE_SHORT("4");

  private String value;

  OrderDirection(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public static OrderDirection getByValue(String value) {
    for (OrderDirection direction : OrderDirection.values()) {
      if (direction.getValue().equals(value)) {
        return direction;
      }
    }
    return null;
  }
}
