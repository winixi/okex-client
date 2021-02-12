package sh.evc.sdk.okex.client.response;

import sh.evc.sdk.okex.client.dict.MarginMode;
import sh.evc.sdk.okex.client.domain.FutureHolding;

import java.util.List;

/**
 * 合约持仓
 *
 * @author winixi
 * @date 2020/12/30 3:33 PM
 */
public class FuturePositionResponse extends ApiResponse {

  /**
   * 仓位数组
   */
  private List<FutureHolding> holding;

  /**
   * 仓位类型
   */
  private MarginMode marginMode;

  public List<FutureHolding> getHolding() {
    return holding;
  }

  public void setHolding(List<FutureHolding> holding) {
    this.holding = holding;
  }

  public MarginMode getMarginModel() {
    return marginMode;
  }

  public void setMarginModel(MarginMode marginMode) {
    this.marginMode = marginMode;
  }

  @Override
  public String toString() {
    return "FuturesPositionResponse{" +
            "holding=" + holding +
            ", marginModel=" + marginMode +
            "} " + super.toString();
  }
}
