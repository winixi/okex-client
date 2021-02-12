package sh.evc.sdk.okex.client.request;

import sh.evc.sdk.okex.client.dict.RequestMethod;
import sh.evc.sdk.okex.client.response.FuturePositionResponse;

import java.util.Map;

/**
 * 合约仓位查询
 *
 * @author winixi
 * @date 2020/12/30 3:49 PM
 */
public class FuturePositionRequest extends ApiRequest<FuturePositionResponse> {

  /**
   * 产品
   */
  private String instrumentId;

  public FuturePositionRequest(String instrumentId) {
    this.instrumentId = instrumentId;
  }

  @Override
  public Map<String, String> getTextParams() {
    return null;
  }

  @Override
  public String getUri() {
    return "/api/futures/v3/" + instrumentId + "/position";
  }

  @Override
  public RequestMethod getMethod() {
    return RequestMethod.GET;
  }

  @Override
  public Class<FuturePositionResponse> getResponseClass() {
    return FuturePositionResponse.class;
  }
}
