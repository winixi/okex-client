package sh.evc.sdk.okex.client.dict;

/**
 * 方向
 *
 * @author winixi
 * @date 2021/1/4 5:05 PM
 */
public enum Direction {

  /**
   * 多
   */
  LONG("long"),

  /**
   * 空
   */
  SHORT("short");

  private String name;

  Direction(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
