package sh.evc.sdk.okex.client.domain;

/**
 * 合约订单
 *
 * @author winixi
 * @date 2020/12/31 4:41 PM
 */
public class FutureOrder {

  /**
   * 合约ID，如BTC-USD-180213 ,BTC-USDT-191227
   */
  private String instrumentId;

  /**
   * 用户设置的订单ID
   */
  private String clientOid;

  /**
   * 委托数量
   */
  private String size;

  /**
   * 委托时间
   */
  private String timestamp;

  /**
   * 成交数量
   */
  private String filledQty;

  /**
   * 手续费
   */
  private String fee;

  /**
   * 订单ID
   */
  private String orderId;

  /**
   * 委托价格
   */
  private String price;

  /**
   * 成交均价
   */
  private String priceAvg;

  /**
   * 订单类型
   * <p>
   * 1:开多
   * 2:开空
   * 3:平多
   * 4:平空
   */
  private String type;

  /**
   * 合约面值
   */
  private String contractVal;

  /**
   * 杠杆倍数，1-100的数值
   */
  private String leverage;

  /**
   * 0：普通委托
   * 1：只做Maker（Post only）
   * 2：全部成交或立即取消（FOK）
   * 3：立即成交并取消剩余（IOC）
   * 4：市价委托
   */
  private String orderType;

  /**
   * 平仓已实现盈亏
   */
  private String pnl;

  /**
   * 订单状态
   * <p>
   * -2：失败
   * -1：撤单成功
   * 0：等待成交
   * 1：部分成交
   * 2：完全成交
   * 3：下单中
   * 4：撤单中
   */
  private String state;

  public String getInstrumentId() {
    return instrumentId;
  }

  public void setInstrumentId(String instrumentId) {
    this.instrumentId = instrumentId;
  }

  public String getClientOid() {
    return clientOid;
  }

  public void setClientOid(String clientOid) {
    this.clientOid = clientOid;
  }

  public String getSize() {
    return size;
  }

  public void setSize(String size) {
    this.size = size;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  public String getFilledQty() {
    return filledQty;
  }

  public void setFilledQty(String filledQty) {
    this.filledQty = filledQty;
  }

  public String getFee() {
    return fee;
  }

  public void setFee(String fee) {
    this.fee = fee;
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getPriceAvg() {
    return priceAvg;
  }

  public void setPriceAvg(String priceAvg) {
    this.priceAvg = priceAvg;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getContractVal() {
    return contractVal;
  }

  public void setContractVal(String contractVal) {
    this.contractVal = contractVal;
  }

  public String getLeverage() {
    return leverage;
  }

  public void setLeverage(String leverage) {
    this.leverage = leverage;
  }

  public String getOrderType() {
    return orderType;
  }

  public void setOrderType(String orderType) {
    this.orderType = orderType;
  }

  public String getPnl() {
    return pnl;
  }

  public void setPnl(String pnl) {
    this.pnl = pnl;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  @Override
  public String toString() {
    return "FutureOrder{" +
            "instrumentId='" + instrumentId + '\'' +
            ", clientOid='" + clientOid + '\'' +
            ", size='" + size + '\'' +
            ", timestamp='" + timestamp + '\'' +
            ", filledQty='" + filledQty + '\'' +
            ", fee='" + fee + '\'' +
            ", orderId='" + orderId + '\'' +
            ", price='" + price + '\'' +
            ", priceAvg='" + priceAvg + '\'' +
            ", type='" + type + '\'' +
            ", contractVal='" + contractVal + '\'' +
            ", leverage='" + leverage + '\'' +
            ", orderType='" + orderType + '\'' +
            ", pnl='" + pnl + '\'' +
            ", state='" + state + '\'' +
            '}';
  }
}
