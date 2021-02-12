package sh.evc.sdk.okex.client.request;

import sh.evc.sdk.okex.client.dict.RequestMethod;
import sh.evc.sdk.okex.client.response.CandlesGetResponse;
import sh.evc.sdk.okex.client.util.DateUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * candles get request
 *
 * @author winixi
 * @date 2021/1/6 2:09 PM
 */
public class CandlesGetRequest extends ApiRequest<CandlesGetResponse> {

  /**
   * 合约ID，如BTC-USD-180213,BTC-USDT-191227
   */
  private String instrumentId;

  /**
   * 开始时间（ISO 8601标准，例如：2018-06-20T02:31:00Z）
   */
  private String start;

  /**
   * 结束时间（ISO 8601标准，例如：2018-06-20T02:31:00Z）
   */
  private String end;

  /**
   * 时间粒度，以秒为单位，默认值60。如[60/180/300/900/1800/3600/7200/14400/21600/43200/86400/604800]
   */
  private String granularity;

  public CandlesGetRequest(String instrumentId, String start, String end, String granularity) {
    this.instrumentId = instrumentId;
    this.start = start;
    this.end = end;
    this.granularity = granularity;
  }

  @Override
  public Map<String, String> getTextParams() {
    Map<String, String> params = new HashMap<>();
    params.put("start", DateUtil.getUnixTime(start));
    params.put("end", DateUtil.getUnixTime(end));
    params.put("granularity", granularity);
    return params;
  }

  @Override
  public String getUri() {
    return "/api/futures/v3/instruments/" + instrumentId + "/candles";
  }

  @Override
  public RequestMethod getMethod() {
    return RequestMethod.GET;
  }

  @Override
  public Class<CandlesGetResponse> getResponseClass() {
    return CandlesGetResponse.class;
  }
}
