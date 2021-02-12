package sh.evc.sdk.okex.client.response;

/**
 * new order
 *
 * @author winixi
 * @date 2021/1/2 2:53 PM
 */
public class NewOrderResponse extends ApiResponse {

  /**
   * 订单ID，下单失败时，此字段值为-1
   */
  private String orderId;

  /**
   * 由您设置的订单ID来识别您的订单
   */
  private String clientOid;

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public String getClientOid() {
    return clientOid;
  }

  public void setClientOid(String clientOid) {
    this.clientOid = clientOid;
  }

  @Override
  public String toString() {
    return "NewOrderResponse{" +
            "orderId='" + orderId + '\'' +
            ", clientOid='" + clientOid + '\'' +
            "} " + super.toString();
  }
}
