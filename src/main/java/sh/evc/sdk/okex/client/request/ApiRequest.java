package sh.evc.sdk.okex.client.request;

import sh.evc.sdk.okex.client.dict.RequestMethod;
import sh.evc.sdk.okex.client.response.ApiResponse;
import sh.evc.sdk.okex.client.util.JsonUtil;
import sh.evc.sdk.okex.client.util.StringUtil;

import java.util.Map;

/**
 * TOP请求接口。
 *
 * @author carver.gu
 * @since 1.0, Sep 12, 2009
 */
public abstract class ApiRequest<T extends ApiResponse> {

  /**
   * 获取所有的Key-Value形式的文本请求参数集合。其中：
   * <ul>
   * <li>Key: 请求参数名</li>
   * <li>Value: 请求参数值</li>
   * </ul>
   *
   * @return 文本请求参数集合
   */
  public abstract Map<String, String> getTextParams();

  /**
   * 获取接口地址
   *
   * @return
   */
  public abstract String getUri();

  /**
   * 获取请求方法
   *
   * @return
   */
  public abstract RequestMethod getMethod();

  /**
   * 获取返回对象类型
   *
   * @return
   */
  public abstract Class<T> getResponseClass();

  /**
   * url拼接所有参数
   *
   * @return
   */
  public String getQueryString() {
    if (getMethod() == RequestMethod.POST) {
      return "";
    }
    Map<String, String> params = getTextParams();
    if (params == null || params.isEmpty()) {
      return "";
    }
    StringBuffer sb = new StringBuffer();
    int i = 0;
    for (String key : params.keySet()) {
      String value = params.get(key);
      if (StringUtil.isEmpty(value)) {
        continue;
      }
      if (i > 0) {
        sb.append("&");
      }
      sb.append(key).append("=").append(params.get(key));
      i++;
    }
    return sb.toString();
  }

  /**
   * json化所有参数
   *
   * @return
   */
  public String getBody() {
    if (this.getMethod() == RequestMethod.GET) {
      return "";
    }
    Map<String, String> params = getTextParams();
    if (params == null || params.isEmpty()) {
      return "";
    }
    return JsonUtil.toJson(params);
  }
}
