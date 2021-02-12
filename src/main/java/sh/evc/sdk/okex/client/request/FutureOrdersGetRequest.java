package sh.evc.sdk.okex.client.request;

import sh.evc.sdk.okex.client.dict.RequestMethod;
import sh.evc.sdk.okex.client.response.FutureOrdersGetResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * 合约订单获取请求
 *
 * @author winixi
 * @date 2020/12/31 4:50 PM
 */
public class FutureOrdersGetRequest extends ApiRequest<FutureOrdersGetResponse> {

  /**
   * 合约ID，如BTC-USD-180213 ,BTC-USDT-191227
   */
  private String instrumentId;

  /**
   * 订单状态
   * <p>
   * -2:失败
   * -1:撤单成功
   * 0:等待成交
   * 1:部分成交
   * 2:完全成交
   * 3:下单中
   * 4:撤单中
   * 6: 未完成（等待成交+部分成交）
   * 7:已完成（撤单成功+完全成交）
   */
  private String state;

  /**
   * 请求此id之前(更旧的数据)的分页内容，传的值为对应接口的order_id
   */
  private String after;

  /**
   * 请求此id之后(更新的数据)的分页内容，传的值为对应接口的order_id；
   */
  private String before;

  /**
   * 分页返回的结果集数量，最大为100，不填默认返回100条
   */
  private String limit;

  public FutureOrdersGetRequest(String instrumentId, String state, String after, String before, String limit) {
    this.instrumentId = instrumentId;
    this.state = state;
    this.after = after;
    this.before = before;
    this.limit = limit;
  }

  @Override
  public Map<String, String> getTextParams() {
    Map<String, String> params = new HashMap<>(5);
    params.put("instrument_id", instrumentId);
    params.put("state", state);
    params.put("after", after);
    params.put("before", before);
    params.put("limit", limit);
    return params;
  }

  @Override
  public String getUri() {
    return "/api/futures/v3/orders/" + instrumentId;
  }

  @Override
  public RequestMethod getMethod() {
    return RequestMethod.GET;
  }

  @Override
  public Class<FutureOrdersGetResponse> getResponseClass() {
    return FutureOrdersGetResponse.class;
  }
}
