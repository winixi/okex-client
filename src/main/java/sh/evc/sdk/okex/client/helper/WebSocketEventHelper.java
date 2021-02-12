package sh.evc.sdk.okex.client.helper;

import sh.evc.sdk.okex.client.client.WebSocketClient;
import sh.evc.sdk.okex.client.config.OkexClientConfig;
import sh.evc.sdk.okex.client.util.MD5Util;

import java.util.HashMap;
import java.util.Map;

/**
 * 添加通道助手
 *
 * @author winixi
 * @date 2018/5/17 10:42 PM
 */
public class WebSocketEventHelper {

  private OkexClientConfig config;
  private WebSocketClient client;

  public WebSocketEventHelper(OkexClientConfig config, WebSocketClient client) {
    this.config = config;
    this.client = client;
  }

  /**
   * 登录
   * <p>
   * 个人信息推送，个人数据有变化时会自动推送，其它旧的个人数据订阅类型可不订阅，
   * 如:ok_sub_futureusd_trades,ok_sub_futureusd_userinfo,ok_sub_futureusd_positions
   *
   * @return
   */
  public void login() {
    Map<String, String> preMap = new HashMap<>();
    preMap.put("api_key", config.getApiKey());
    String preStr = MD5Util.createLinkString(preMap);
    preStr = preStr + "&secret_key=" + config.getSecretKey();
    String signStr = MD5Util.getMD5String(preStr);
    preMap.put("sign", signStr);
    String params = MD5Util.getParams(preMap);
    StringBuilder sb = new StringBuilder("{\"event\": \"login\",\"parameters\": ").append(params).append("}");
    client.sendMessage(sb.toString());
  }

  /**
   * 取消合约订单
   *
   * @param symbol
   * @param orderId
   * @param contractType
   */
  public void cancelFutureOrder(String symbol, long orderId, String contractType) {
    Map<String, String> preMap = new HashMap<>();
    preMap.put("api_key", config.getApiKey());
    preMap.put("symbol", symbol);
    preMap.put("order_id", String.valueOf(orderId));
    preMap.put("contract_type", contractType);
    String preStr = MD5Util.createLinkString(preMap);
    preStr = preStr + "&secret_key=" + config.getSecretKey();
    String signStr = MD5Util.getMD5String(preStr);
    preMap.put("sign", signStr);
    String params = MD5Util.getParams(preMap);
    StringBuilder tradeStr = new StringBuilder("{'event': 'addChannel','channel': 'ok_futuresusd_cancel_order','parameters': ").append(params).append("}");
    client.sendMessage(tradeStr.toString());
  }

  /**
   * 取消现货交易
   *
   * @param symbol
   * @param orderId
   */
  public void cancelOrder(String symbol, long orderId) {
    Map<String, String> preMap = new HashMap<>();
    preMap.put("api_key", config.getApiKey());
    preMap.put("symbol", symbol);
    preMap.put("order_id", String.valueOf(orderId));
    String preStr = MD5Util.createLinkString(preMap);
    StringBuilder preBuilder = new StringBuilder(preStr);
    preBuilder.append("&secret_key=").append(config.getSecretKey());
    String signStr = MD5Util.getMD5String(preBuilder.toString());
    preMap.put("sign", signStr);
    String params = MD5Util.getParams(preMap);
    String channel = "ok_spotusd_cancel_order";
    StringBuilder tradeStr = new StringBuilder("{'event':'addChannel', 'channel':'" + channel + "', 'parameters':").append(params).append("}");
    client.sendMessage(tradeStr.toString());
  }

  /**
   * 合约交易数据
   */
  public void futureRealtrades() {
    StringBuilder preStr = new StringBuilder("api_key=");
    preStr.append(config.getApiKey()).append("&secret_key=").append(config.getSecretKey());
    String signStr = MD5Util.getMD5String(preStr.toString());
    StringBuilder tradeStr = new StringBuilder("{'event':'addChannel','channel':'ok_sub_futureusd_trades','parameters':{'api_key':'").append(config.getApiKey()).append("','sign':'").append(signStr)
            .append("'},'binary':'true'}");
    client.sendMessage(tradeStr.toString());
  }

  /**
   * 合约下单
   *
   * @param symbol
   * @param contractType
   * @param price
   * @param amount
   * @param type
   * @param matchPrice
   * @param leverRate
   */
  public void futureTrade(String symbol, String contractType, double price, int amount, int type, double matchPrice, int leverRate) {
    Map<String, String> preMap = new HashMap<>();
    // 待签名字符串
    preMap.put("api_key", config.getApiKey());
    preMap.put("symbol", symbol);
    preMap.put("contract_type", contractType);
    preMap.put("price", String.valueOf(price));
    preMap.put("amount", String.valueOf(amount));
    preMap.put("type", String.valueOf(type));
    preMap.put("match_price", String.valueOf(matchPrice));
    preMap.put("lever_rate", String.valueOf(leverRate));
    String preStr = MD5Util.createLinkString(preMap);
    preStr = preStr + "&secret_key=" + config.getSecretKey();
    // 签名
    String signStr = MD5Util.getMD5String(preStr);
    // 参数
    preMap.put("sign", signStr);
    String params = MD5Util.getParams(preMap);
    // 交易json
    StringBuilder tradeStr = new StringBuilder("{\"event\": \"addChannel\",\"channel\":\"ok_futuresusd_trade\",\"parameters\":").append(params).append("}");
    client.sendMessage(tradeStr.toString());
  }

  /**
   * 现货查询账户信息
   */
  public void getUserInfo() {
    StringBuilder preStr = new StringBuilder("api_key=");
    preStr.append(config.getApiKey()).append("&secret_key=").append(config.getSecretKey());
    String signStr = MD5Util.getMD5String(preStr.toString());
    String channel = "ok_spotusd_userinfo";
    StringBuilder tradeStr = new StringBuilder(
            "{'event':'addChannel','channel':'").append(channel)
            .append("','parameters':{'api_key':'").append(config.getApiKey())
            .append("','sign':'").append(signStr)
            .append("'},'binary':'true'}");
    client.sendMessage(tradeStr.toString());
  }

  /**
   * 现货交易数据
   */
  public void realTrades() {
    StringBuilder preStr = new StringBuilder("api_key=");
    preStr.append(config.getApiKey()).append("&secret_key=").append(config.getSecretKey());
    String signStr = MD5Util.getMD5String(preStr.toString());
    String channel = "ok_sub_spotusd_trades";
    StringBuilder tradeStr = new StringBuilder(
            "{'event':'addChannel','channel':'" + channel
                    + "','parameters':{'api_key':'").append(config.getApiKey())
            .append("','sign':'").append(signStr)
            .append("'},'binary':'true'}");
    client.sendMessage(tradeStr.toString());
  }

  /**
   * 现货交易下单
   *
   * @param symbol
   * @param price
   * @param amount
   * @param type
   */
  public void spotTrade(String symbol, String price, String amount, String type) {
    Map<String, String> signPreMap = new HashMap<>();
    signPreMap.put("api_key", config.getApiKey());
    signPreMap.put("symbol", symbol);
    if (price != null) {
      signPreMap.put("price", price);
    }
    if (amount != null) {
      signPreMap.put("amount", amount);
    }
    signPreMap.put("type", type);
    String preStr = MD5Util.createLinkString(signPreMap);
    StringBuilder preBuilder = new StringBuilder(preStr);
    preBuilder.append("&secret_key=").append(config.getSecretKey());
    String signStr = MD5Util.getMD5String(preBuilder.toString());
    String channel = "ok_spotusd_trade";
    StringBuilder tradeStr = new StringBuilder("{'event':'addChannel','channel':'" + channel + "','parameters':");
    signPreMap.put("sign", signStr);
    String params = MD5Util.getParams(signPreMap);
    tradeStr.append(params).append("}");
    client.sendMessage(tradeStr.toString());
  }
}
