package sh.evc.sdk.okex.client.response;

import sh.evc.sdk.okex.client.domain.FutureOrder;

import java.util.List;

/**
 * 合约订单获取
 *
 * @author winixi
 * @date 2020/12/31 4:39 PM
 */
public class FutureOrdersGetResponse extends ApiResponse {

  private List<FutureOrder> orderInfo;

  public List<FutureOrder> getOrderInfo() {
    return orderInfo;
  }

  public void setOrderInfo(List<FutureOrder> orderInfo) {
    this.orderInfo = orderInfo;
  }

  @Override
  public String toString() {
    return "FutureOrdersGetResponse{" +
            "orderInfo=" + orderInfo +
            "} " + super.toString();
  }
}
