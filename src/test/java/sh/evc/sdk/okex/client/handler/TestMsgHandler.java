package sh.evc.sdk.okex.client.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 接收消息处理器
 *
 * @author winixi
 * @date 2020/12/30 4:00 PM
 */
public class TestMsgHandler implements WebSocketMsgHandler {

  private final static Logger logger = LoggerFactory.getLogger(TestMsgHandler.class);

  /**
   * 接收到信息后的处理
   *
   * @param msg
   */
  @Override
  public void onReceive(String msg) {
    logger.info("WebSocket Client received message: " + msg);
  }
}
