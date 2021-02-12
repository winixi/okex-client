package sh.evc.sdk.okex.client.request;

import sh.evc.sdk.okex.client.dict.Direction;
import sh.evc.sdk.okex.client.dict.RequestMethod;
import sh.evc.sdk.okex.client.response.ClosePositionResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * 市价平仓
 *
 * @author winixi
 * @date 2020/12/31 1:49 PM
 */
public class ClosePositionRequest extends ApiRequest<ClosePositionResponse> {

  /**
   * 合约id
   */
  private String instrumentId;

  /**
   * 平仓方向
   * long:平多
   * short:平空
   */
  private Direction direction;

  public ClosePositionRequest(String instrumentId, Direction direction) {
    this.instrumentId = instrumentId;
    this.direction = direction;
  }

  @Override
  public Map<String, String> getTextParams() {
    Map<String, String> params = new HashMap<>(2);
    params.put("instrument_id", instrumentId);
    params.put("direction", direction.getName());
    return params;
  }

  @Override
  public String getUri() {
    return "/api/futures/v3/close_position";
  }

  @Override
  public RequestMethod getMethod() {
    return RequestMethod.POST;
  }

  @Override
  public Class<ClosePositionResponse> getResponseClass() {
    return ClosePositionResponse.class;
  }
}
