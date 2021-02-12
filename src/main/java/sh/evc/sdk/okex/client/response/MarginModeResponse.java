package sh.evc.sdk.okex.client.response;

import sh.evc.sdk.okex.client.dict.MarginMode;

/**
 * 账户模式返回
 *
 * @author winixi
 * @date 2020/12/31 9:48 AM
 */
public class MarginModeResponse extends ApiResponse {

  /**
   * 账户模式
   */
  private MarginMode marginMode;

  /**
   * 标的
   */
  private String underlying;

  public MarginMode getMarginMode() {
    return marginMode;
  }

  public void setMarginMode(MarginMode marginMode) {
    this.marginMode = marginMode;
  }

  public String getUnderlying() {
    return underlying;
  }

  public void setUnderlying(String underlying) {
    this.underlying = underlying;
  }

  @Override
  public String toString() {
    return "MarginModeResponse{" +
            "marginMode=" + marginMode +
            ", underlying='" + underlying + '\'' +
            "} " + super.toString();
  }
}
