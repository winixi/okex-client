package sh.evc.sdk.okex.client.handler;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * 消息服务
 *
 * @author winixi
 * @date 2018/5/17 9:28 PM
 */
public interface WebSocketMsgHandler {

  /**
   * 接收到消息
   *
   * @param msg
   */
  void onReceive(String msg) throws JsonProcessingException;
}
