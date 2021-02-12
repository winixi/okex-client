package sh.evc.sdk.okex.client.response;

/**
 * 市价平仓
 *
 * @author winixi
 * @date 2020/12/31 1:46 PM
 */
public class ClosePositionResponse extends ApiResponse {

  /**
   * 合约id
   */
  private String instrumentId;

  /**
   * 平仓方向
   * long:平多
   * short:平空
   */
  private String direction;

  public String getInstrumentId() {
    return instrumentId;
  }

  public void setInstrumentId(String instrumentId) {
    this.instrumentId = instrumentId;
  }

  public String getDirection() {
    return direction;
  }

  public void setDirection(String direction) {
    this.direction = direction;
  }

  @Override
  public String toString() {
    return "ClosePositionResponse{" +
            "instrumentId='" + instrumentId + '\'' +
            ", direction='" + direction + '\'' +
            "} " + super.toString();
  }
}
