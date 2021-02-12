package sh.evc.sdk.okex.client.response;

import sh.evc.sdk.okex.client.dict.RequestMethod;

import java.util.Date;

/**
 * 请求抽象类
 *
 * @author winixi
 * @date 2020/12/30 3:33 PM
 */
public abstract class ApiResponse {

  /**
   * 结果
   */
  private boolean result = true;

  /**
   * 错误消息
   */
  private String message;

  /**
   * 错误码
   */
  private Integer code;

  /**
   * 请求时间
   */
  private Date requestTime;

  /**
   * 请求路径
   */
  private String path;

  /**
   * 请求参数
   */
  private String requestBody;

  /**
   * 请求类型
   */
  private RequestMethod method;

  /**
   * 返回时间
   */
  private Date responseTime;

  /**
   * 返回内容
   */
  private String responseBody;

  public boolean isResult() {
    return result;
  }

  public void setResult(boolean result) {
    this.result = result;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public Date getRequestTime() {
    return requestTime;
  }

  public void setRequestTime(Date requestTime) {
    this.requestTime = requestTime;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public String getRequestBody() {
    return requestBody;
  }

  public void setRequestBody(String requestBody) {
    this.requestBody = requestBody;
  }

  public RequestMethod getMethod() {
    return method;
  }

  public void setMethod(RequestMethod method) {
    this.method = method;
  }

  public Date getResponseTime() {
    return responseTime;
  }

  public void setResponseTime(Date responseTime) {
    this.responseTime = responseTime;
  }

  public String getResponseBody() {
    return responseBody;
  }

  public void setResponseBody(String responseBody) {
    this.responseBody = responseBody;
  }

  @Override
  public String toString() {
    return "ApiResponse{" +
            "result=" + result +
            ", message='" + message + '\'' +
            ", code=" + code +
            ", requestTime=" + requestTime +
            ", url='" + path + '\'' +
            ", requestBody='" + requestBody + '\'' +
            ", method=" + method +
            ", responseTime=" + responseTime +
            ", responseBody='" + responseBody + '\'' +
            '}';
  }
}
