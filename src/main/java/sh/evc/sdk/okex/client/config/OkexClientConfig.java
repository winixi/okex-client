package sh.evc.sdk.okex.client.config;

/**
 * okex config
 *
 * @author winixi
 * @date 2020/12/26 7:41 PM
 */
public interface OkexClientConfig {

  /**
   * websocket api url
   *
   * @return
   */
  String getWebsocketUrl();

  /**
   * restful api url
   *
   * @return
   */
  String getRestfulUrl();

  /**
   * api key
   *
   * @return
   */
  String getApiKey();

  /**
   * secret key
   *
   * @return
   */
  String getSecretKey();

  /**
   * passphrase
   *
   * @return
   */
  String getPassphrase();

}
