package sh.evc.sdk.okex.client.util;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 封装HTTP get post请求，简化发送http请求
 *
 * @author winixi
 * @date 2018/5/3 8:46 AM
 */
public class HttpManager {

  /**
   * 代理服务器
   */
  private String proxyHost;
  private int proxyPort;

  /**
   * 额外的header
   */
  private Map<String, String> headers;

  private static HttpClient client;
  private static long startTime = System.currentTimeMillis();
  private static RequestConfig config;

  public static PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();

  private static ConnectionKeepAliveStrategy keepAliveStrat = new DefaultConnectionKeepAliveStrategy() {
    @Override
    public long getKeepAliveDuration(
            HttpResponse response,
            HttpContext context) {
      long keepAlive = super.getKeepAliveDuration(response, context);

      if (keepAlive == -1) {
        keepAlive = 5000;
      }
      return keepAlive;
    }
  };

  public HttpManager() {
    client = HttpClients.custom().setConnectionManager(cm).setKeepAliveStrategy(keepAliveStrat).build();
    config = buildConfig();
  }

  public HttpManager(String proxyHost, int proxyPort) {
    this.proxyHost = proxyHost;
    this.proxyPort = proxyPort;
    client = HttpClients.custom().setConnectionManager(cm).setKeepAliveStrategy(keepAliveStrat).build();
    config = buildConfig();
  }

  /**
   * 设置headers
   *
   * @param headers
   */
  public void setHeaders(Map<String, String> headers) {
    this.headers = headers;
  }

  public static void IdleConnectionMonitor() {
    if (System.currentTimeMillis() - startTime > 30000) {
      startTime = System.currentTimeMillis();
      cm.closeExpiredConnections();
      cm.closeIdleConnections(30, TimeUnit.SECONDS);
    }
  }

  private RequestConfig buildConfig() {
    //基本配置
    RequestConfig.Builder builder = RequestConfig.custom()
            .setSocketTimeout(20000)
            .setConnectTimeout(20000)
            .setConnectionRequestTimeout(20000);

    //是否有代理
    if (!StringUtil.isEmpty(proxyHost) && proxyPort > 0) {
      builder.setProxy(new HttpHost(proxyHost, Integer.valueOf(proxyPort), "http"));
    }

    //创建配置
    return builder.build();
  }

  private HttpPost httpPostMethod(String url) {
    return new HttpPost(url);
  }

  private HttpRequestBase httpGetMethod(String url) {
    return new HttpGet(url);
  }

  public String requestHttpGet(String url, String method, Map<String, String> params) throws IOException {
    String paramsString = getParamsString(params);
    return requestHttpGet(url, method, paramsString);
  }

  /**
   * get请求
   *
   * @param urlPrex
   * @param url
   * @param requestString
   * @return
   * @throws IOException
   */
  public String requestHttpGet(String urlPrex, String url, String requestString) throws IOException {
    if (requestString != null && !requestString.equals("")) {
      if (url.endsWith("?")) {
        url = url + requestString;
      } else {
        url = url + "?" + requestString;
      }
    }
    return requestHttpGet(urlPrex, url);
  }

  /**
   * get
   *
   * @param urlPrex
   * @param url
   * @return
   * @throws IOException
   */
  public String requestHttpGet(String urlPrex, String url) throws IOException {
    IdleConnectionMonitor();
    url = urlPrex + url;
    HttpRequestBase request = this.httpGetMethod(url);
    request.setConfig(config);
    setHeaders(request);
    HttpResponse response = client.execute(request);
    HttpEntity entity = response.getEntity();
    return this.getResponseData(entity);
  }

  /**
   * post请求，参数用json
   *
   * @param urlPrex
   * @param url
   * @param json
   * @return
   * @throws IOException
   */
  public String requestHttpPost(String urlPrex, String url, String json) throws IOException {
    IdleConnectionMonitor();
    url = urlPrex + url;
    HttpPost post = this.httpPostMethod(url);
    StringEntity stringEntity = new StringEntity(json, StandardCharsets.UTF_8);
    stringEntity.setContentType("application/json");
    post.setEntity(stringEntity);
    post.setConfig(config);
    setHeaders(post);
    HttpResponse response = client.execute(post);
    HttpEntity entity = response.getEntity();
    return this.getResponseData(entity);
  }

  /**
   * post请求，参数用form
   *
   * @param urlPrex
   * @param url
   * @param params
   * @return
   * @throws IOException
   */
  public String requestHttpPost(String urlPrex, String url, Map<String, String> params) throws IOException {
    IdleConnectionMonitor();
    url = urlPrex + url;
    HttpPost post = this.httpPostMethod(url);
    List<NameValuePair> valuePairs = this.convertMap2PostParams(params);
    UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(valuePairs, StandardCharsets.UTF_8);
    post.setEntity(formEntity);
    post.setConfig(config);
    setHeaders(post);
    HttpResponse response = client.execute(post);
    HttpEntity entity = response.getEntity();
    return this.getResponseData(entity);
  }

  /**
   * 返回字符串数据
   *
   * @param entity
   * @return
   * @throws IOException
   */
  private String getResponseData(HttpEntity entity) throws IOException {
    if (entity == null) {
      return "";
    }
    InputStream is = null;
    String responseData = "";
    try {
      is = entity.getContent();
      responseData = IOUtils.toString(is, StandardCharsets.UTF_8);
    } finally {
      if (is != null) {
        is.close();
      }
    }
    return responseData;
  }

  /**
   * 转换post提交参数
   *
   * @param params
   * @return
   */
  private List<NameValuePair> convertMap2PostParams(Map<String, String> params) {
    List<String> keys = new ArrayList<>(params.keySet());
    if (keys.isEmpty()) {
      return null;
    }
    int keySize = keys.size();
    List<NameValuePair> data = new LinkedList<>();
    for (int i = 0; i < keySize; i++) {
      String key = keys.get(i);
      String value = params.get(key);
      data.add(new BasicNameValuePair(key, value));
    }
    return data;
  }

  /**
   * 设置请求头
   *
   * @param request
   */
  private void setHeaders(HttpRequestBase request) {
    request.addHeader("Accept", "application/json");
    request.addHeader("Content-Type", "application/json; charset=UTF-8");
    request.addHeader("Accept-Language", "zh-cn");
    request.addHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.0.3) Gecko/2008092417 Firefox/3.0.3");
    request.addHeader("Keep-Alive", "300");
    request.addHeader("Connection", "Keep-Alive");
    request.addHeader("Cache-Control", "no-cache");

    //拼接上秘钥，密码，签名和时间戳
    if (headers != null) {
      for (String key : headers.keySet()) {
        request.addHeader(key, headers.get(key));
      }
    }
  }

  /**
   * 生成get参数字符串
   *
   * @param params
   * @return
   */
  private String getParamsString(Map<String, String> params) {
    if (params == null) {
      return "";
    }
    StringBuilder sb = new StringBuilder();
    int count = 0;
    for (String key : params.keySet()) {
      String value = params.get(key);
      if (value != null) {
        if (count > 0) {
          sb.append("&");
        }
        sb.append(key).append("=").append(value);
        count++;
      }
    }
    return sb.toString();
  }

}

