package sh.evc.sdk.okex.client.request;

import sh.evc.sdk.okex.client.dict.MarginMode;
import sh.evc.sdk.okex.client.dict.RequestMethod;
import sh.evc.sdk.okex.client.response.MarginModeResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * 设置账户模式请求
 *
 * @author winixi
 * @date 2020/12/31 9:47 AM
 */
public class MarginModeRequest extends ApiRequest<MarginModeResponse> {

  /**
   * 标的指数
   */
  private String underlying;

  /**
   * 账户模式
   */
  private MarginMode marginMode;

  public MarginModeRequest(String underlying, MarginMode marginMode) {
    this.underlying = underlying;
    this.marginMode = marginMode;
  }

  @Override
  public Map<String, String> getTextParams() {
    Map<String, String> params = new HashMap<>(2);
    params.put("underlying", underlying);
    params.put("margin_mode", marginMode.name());
    return params;
  }

  @Override
  public String getUri() {
    return "/api/futures/v3/accounts/margin_mode";
  }

  @Override
  public RequestMethod getMethod() {
    return RequestMethod.POST;
  }

  @Override
  public Class<MarginModeResponse> getResponseClass() {
    return MarginModeResponse.class;
  }
}
