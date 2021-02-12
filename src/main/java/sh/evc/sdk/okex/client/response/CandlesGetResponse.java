package sh.evc.sdk.okex.client.response;

import java.util.List;

/**
 * k线数据下载
 *
 * @author winixi
 * @date 2021/1/6 2:07 PM
 */
public class CandlesGetResponse extends ApiResponse {

  /**
   * timestamp	String	开始时间
   * open	String	开盘价格
   * high	String	最高价格
   * low	String	最低价格
   * close	String	收盘价格
   * volume	String	交易量（张）
   * currency_volume	String	按币种折算的交易量
   */
  private List<String[]> array;

  public List<String[]> getArray() {
    return array;
  }

  public void setArray(List<String[]> array) {
    this.array = array;
  }

  @Override
  public String toString() {
    return "CandlesGetResponse{" +
            "array=" + array +
            "} " + super.toString();
  }
}
