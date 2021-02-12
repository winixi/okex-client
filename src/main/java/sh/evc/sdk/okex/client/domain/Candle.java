package sh.evc.sdk.okex.client.domain;

import java.util.Arrays;

/**
 * candle response
 *
 * @author winixi
 * @date 2020/12/27 11:32 AM
 */
public class Candle {

  private String instrumentId;
  private String[] candle;

  public String getInstrumentId() {
    return instrumentId;
  }

  public void setInstrumentId(String instrumentId) {
    this.instrumentId = instrumentId;
  }

  public String[] getCandle() {
    return candle;
  }

  public void setCandle(String[] candle) {
    this.candle = candle;
  }

  @Override
  public String toString() {
    return "CandleResponse{" +
            "instrumentId='" + instrumentId + '\'' +
            ", candle=" + Arrays.toString(candle) +
            '}';
  }
}
