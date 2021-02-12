package sh.evc.sdk.okex.client.request;

import sh.evc.sdk.okex.client.dict.OrderDirection;
import sh.evc.sdk.okex.client.dict.RequestMethod;
import sh.evc.sdk.okex.client.response.NewOrderResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * 下单
 *
 * @author winixi
 * @date 2021/1/2 2:59 PM
 */
public class NewOrderRequest extends ApiRequest<NewOrderResponse> {

  /**
   * 由您设置的订单ID来识别您的订单,格式必须字母开头+数字（区分大小写，例如：A123、a123）或者 纯字母（区分大小写，例如：abc、Abc），1-32位字符 （不能重复）
   */
  private String clientOid;

  /**
   * 合约ID，如BTC-USD-180213 ,BTC-USDT-191227
   */
  private String instrumentId;

  /**
   * 1:开多 OPEN_LONG
   * 2:开空 OPEN_SHORT
   * 3:平多 CLOSE_LONG
   * 4:平空 CLOSE_SHORT
   */
  private OrderDirection type;

  /**
   * 0：普通委托（order type不填或填0都是普通委托）
   * 1：只做Maker（Post only）
   * 2：全部成交或立即取消（FOK）
   * 3：立即成交并取消剩余（IOC）
   * 4：市价委托
   */
  private String orderType;

  /**
   * 委托价格
   */
  private String price;

  /**
   * 买入或卖出合约的数量（以张计数）
   */
  private Integer size;

  /**
   * 是否以对手价下单(0:不是; 1:是)，默认为0，当取值为1时，price字段无效。当以对手价下单，order_type只能选择0（普通委托）
   */
  private String matchPrice;

  public NewOrderRequest(String clientOid, String instrumentId, OrderDirection type, String orderType, String price, Integer size, String matchPrice) {
    this.clientOid = clientOid;
    this.instrumentId = instrumentId;
    this.type = type;
    this.orderType = orderType;
    this.price = price;
    this.size = size;
    this.matchPrice = matchPrice;
  }

  /**
   * 对手价下单
   *
   * @param instrumentId
   * @param type
   * @param size
   */
  public NewOrderRequest(String instrumentId, OrderDirection type, Integer size) {
    this.clientOid = "";
    this.instrumentId = instrumentId;
    this.type = type;
    this.orderType = "0";
    this.price = "";
    this.size = size;
    this.matchPrice = "1";
  }

  @Override
  public Map<String, String> getTextParams() {
    Map<String, String> params = new HashMap<>(7);
    params.put("client_oid", clientOid);
    params.put("instrument_id", instrumentId);
    params.put("type", type.getValue());
    params.put("order_type", orderType);
    params.put("price", price);
    params.put("size", String.valueOf(size));
    params.put("match_price", matchPrice);
    return params;
  }

  @Override
  public String getUri() {
    return "/api/futures/v3/order";
  }

  @Override
  public RequestMethod getMethod() {
    return RequestMethod.POST;
  }

  @Override
  public Class<NewOrderResponse> getResponseClass() {
    return NewOrderResponse.class;
  }
}
