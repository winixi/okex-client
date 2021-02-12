package sh.evc.sdk.okex.client.handler;

import sh.evc.sdk.okex.client.response.ApiResponse;

/**
 * restful response handler
 *
 * @author winixi
 * @date 2021/1/8 1:42 PM
 */
public interface RestfulResponseHandler {

  /**
   * 用来做日志或数据记录
   *
   * @param response
   */
  void append(ApiResponse response);
}
