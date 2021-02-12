package sh.evc.sdk.okex.client.handler;

import sh.evc.sdk.okex.client.response.ApiResponse;

/**
 * test response handler
 *
 * @author winixi
 * @date 2021/1/8 1:52 PM
 */
public class TestResponseHandler implements RestfulResponseHandler {

  @Override
  public void append(ApiResponse response) {
    System.out.println(response.toString());
  }
}
