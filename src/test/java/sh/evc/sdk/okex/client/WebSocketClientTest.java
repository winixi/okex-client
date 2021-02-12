package sh.evc.sdk.okex.client;

import org.junit.Before;
import org.junit.Test;
import sh.evc.sdk.okex.client.client.WebSocketClient;
import sh.evc.sdk.okex.client.config.TestConfig;
import sh.evc.sdk.okex.client.handler.TestMsgHandler;

import java.io.IOException;

/**
 * websocket 测试
 *
 * @author winixi
 * @date 2018/5/17 12:31 PM
 */
public class WebSocketClientTest {

  // 订阅消息处理类,用于处理WebSocket服务返回的消息
  private WebSocketClient webSocketClient;

  @Before
  public void init() throws IOException {
    TestMsgHandler msgHandler = new TestMsgHandler();
    TestConfig config = new TestConfig();
    webSocketClient = new WebSocketClient(config, msgHandler);
  }

  @Test
  public void test() {

    //启动客户端
    webSocketClient.start();

    //添加订阅1分钟k线数据
    webSocketClient.addChannel("futures/candle60s:EOS-USD-210326");
//    webSocketClient.addChannel("ok_sub_futureusd_eos_depth_this_week_20");

//    webSocketEventHelper.login();

//    webSocketClient.addChannel("ok_sub_futureusd_positions");

    //String symbol, String contractType, double price, int amount, int type, double matchPrice, int leverRate
//    webSocketEventHelper.futureTrade(Symbol.eos_usd.toString(), ContractType.quarter.toString(), 13.8, 1, 2, 0, 10);


    //为保证测试方法不停，需要让线程延迟
    try {
      Thread.sleep(10000000);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


}
