package sh.evc.sdk.okex.client.config;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * 测试配置
 *
 * @author winixi
 * @date 2020/12/30 4:00 PM
 */
public class TestConfig implements OkexClientConfig {

  private String websocketUrl;
  private String restfulUrl;
  private String apiKey;
  private String secretKey;
  private String passphrase;

  public TestConfig() throws IOException {
    InputStream in = this.getClass().getResourceAsStream("/config.properties");
    InputStreamReader inputStreamReader = new InputStreamReader(in, "UTF-8");
    Properties props = new Properties();
    props.load(inputStreamReader);
    websocketUrl = props.getProperty("websocketUrl");
    restfulUrl = props.getProperty("restfulUrl");
    apiKey = props.getProperty("apiKey");
    secretKey = props.getProperty("secretKey");
    passphrase = props.getProperty("passphrase");
  }

  @Override
  public String getWebsocketUrl() {
    return websocketUrl;
  }

  @Override
  public String getRestfulUrl() {
    return restfulUrl;
  }

  @Override
  public String getApiKey() {
    return apiKey;
  }

  @Override
  public String getSecretKey() {
    return secretKey;
  }

  @Override
  public String getPassphrase() {
    return passphrase;
  }

}
