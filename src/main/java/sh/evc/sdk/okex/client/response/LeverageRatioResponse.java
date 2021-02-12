package sh.evc.sdk.okex.client.response;

/**
 * leverage ratio
 *
 * @author winixi
 * @date 2021/1/2 1:23 PM
 */
public class LeverageRatioResponse extends ApiResponse {

  /**
   * 账户类型
   * 逐仓：fixed
   * 全仓：crossed
   */
  private String marginMode;

  public String getMarginMode() {
    return marginMode;
  }

  public void setMarginMode(String marginMode) {
    this.marginMode = marginMode;
  }
}
