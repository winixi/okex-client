package sh.evc.sdk.okex.client.request;

import sh.evc.sdk.okex.client.dict.Direction;
import sh.evc.sdk.okex.client.dict.RequestMethod;
import sh.evc.sdk.okex.client.response.LeverageRatioResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * leverage ratio
 *
 * @author winixi
 * @date 2021/1/2 2:37 PM
 */
public class LeverageRatioRequest extends ApiRequest<LeverageRatioResponse> {

  /**
   * 要设定的杠杆倍数，填写1-100的数值
   */
  private Integer leverage;

  /**
   * 标的指数，如：BTC-USD,BTC-USDT
   */
  private String underlying;

  /**
   * 逐仓参数
   * 合约ID，如BTC-USD-180213 ,BTC-USDT-191227
   */
  private String instrumentId;

  /**
   * 逐仓参数
   * 开仓方向 long（做多）或short（做空）
   */
  private Direction direction;

  public LeverageRatioRequest(Integer leverage, String underlying, String instrumentId, Direction direction) {
    this.leverage = leverage;
    this.underlying = underlying;
    this.instrumentId = instrumentId;
    this.direction = direction;
  }

  @Override
  public Map<String, String> getTextParams() {
    Map<String, String> params = new HashMap<>(3);
    params.put("leverage", String.valueOf(leverage));
    params.put("instrument_id", instrumentId);
    params.put("direction", direction.getName());
    return params;
  }

  @Override
  public String getUri() {
    return "/api/futures/v3/accounts/" + underlying + "/leverage";
  }

  @Override
  public RequestMethod getMethod() {
    return RequestMethod.POST;
  }

  @Override
  public Class<LeverageRatioResponse> getResponseClass() {
    return LeverageRatioResponse.class;
  }
}
