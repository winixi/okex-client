package sh.evc.sdk.okex.client.client;

import org.json.JSONArray;
import org.json.JSONTokener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sh.evc.sdk.okex.client.config.OkexClientConfig;
import sh.evc.sdk.okex.client.dict.RequestMethod;
import sh.evc.sdk.okex.client.exception.APIException;
import sh.evc.sdk.okex.client.handler.RestfulResponseHandler;
import sh.evc.sdk.okex.client.request.ApiRequest;
import sh.evc.sdk.okex.client.response.ApiResponse;
import sh.evc.sdk.okex.client.util.*;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 期货服务接口
 *
 * @author winixi
 * @date 2018/5/2 11:20 AM
 */
public class RestfulClient {

  private final static Logger logger = LoggerFactory.getLogger(RestfulClient.class);
  private final OkexClientConfig config;
  private final RestfulResponseHandler handler;
  private final HttpManager httpManager = new HttpManager();

  public RestfulClient(OkexClientConfig config, RestfulResponseHandler handler) {
    this.config = config;
    this.handler = handler;
  }

  /**
   * 执行请求
   *
   * @param request
   * @param <T>
   * @return
   */
  public <T extends ApiResponse> T execute(ApiRequest<T> request) {
    String timestamp = DateUtil.getUnixTime();
    String body = request.getBody();
    String queryString = request.getQueryString();
    String sign = this.sign(timestamp, request.getMethod().name(), request.getUri(), queryString, body);
    httpManager.setHeaders(this.getOkexHeaders(timestamp, sign));

    String res = null;
    String url = config.getRestfulUrl();
    Date requestTime = new Date();
    try {
      if (request.getMethod() == RequestMethod.POST) {
        res = httpManager.requestHttpPost(url, request.getUri(), body);
      } else {
        res = httpManager.requestHttpGet(url, request.getUri(), queryString);
      }
    } catch (IOException e) {
      logger.error("请求接口错误：{}", e.getMessage());
    }

    //检查res的json，如果是jsonArray则加上节点，让变成jsonObject
    Object json = new JSONTokener(res).nextValue();
    if (json instanceof JSONArray) {
      res = "{\"array\":" + res + "}";
    }

    T response = JsonUtil.toObject(res, request.getResponseClass());
    response.setPath(getPath(request.getUri(), queryString));
    response.setRequestTime(requestTime);
    response.setRequestBody(body);
    response.setMethod(request.getMethod());
    response.setResponseTime(new Date());
    response.setResponseBody(res);

    handler.append(response);
    return response;
  }

  /**
   * 获取完整请求地址
   *
   * @param uri
   * @param queryString
   * @return
   */
  private String getPath(String uri, String queryString) {
    if (StringUtil.isEmpty(queryString)) {
      return uri;
    }
    return uri + "?" + queryString;
  }

  /**
   * 签名
   *
   * @param timestamp
   * @param method
   * @param requestUri
   * @param queryString
   * @param body
   * @return
   */
  private String sign(String timestamp, String method, String requestUri, String queryString, String body) {
    final String sign;
    try {
      sign = HmacSHA256Base64Utils.sign(timestamp, method, requestUri, queryString, body, config.getSecretKey());
    } catch (final IOException e) {
      throw new APIException("Request get body io exception.", e);
    } catch (final CloneNotSupportedException e) {
      throw new APIException("Hmac SHA256 Base64 Signature clone not supported exception.", e);
    } catch (final InvalidKeyException e) {
      throw new APIException("Hmac SHA256 Base64 Signature invalid key exception.", e);
    }
    return sign;
  }

  /**
   * 生成okex header
   *
   * @param timestamp
   * @param sign
   * @return
   */
  private Map<String, String> getOkexHeaders(String timestamp, String sign) {
    Map<String, String> map = new HashMap<>(4);
    map.put("OK-ACCESS-KEY", config.getApiKey());
    map.put("OK-ACCESS-SIGN", sign);
    map.put("OK-ACCESS-TIMESTAMP", timestamp);
    map.put("OK-ACCESS-PASSPHRASE", config.getPassphrase());
    return map;
  }
}
