package sh.evc.sdk.okex.client;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sh.evc.sdk.okex.client.client.RestfulClient;
import sh.evc.sdk.okex.client.config.TestConfig;
import sh.evc.sdk.okex.client.dict.Direction;
import sh.evc.sdk.okex.client.dict.MarginMode;
import sh.evc.sdk.okex.client.dict.OrderDirection;
import sh.evc.sdk.okex.client.handler.RestfulResponseHandler;
import sh.evc.sdk.okex.client.handler.TestResponseHandler;
import sh.evc.sdk.okex.client.request.*;
import sh.evc.sdk.okex.client.response.*;
import sh.evc.sdk.okex.client.util.InstrumentUtil;

import java.io.IOException;

/**
 * restful client
 *
 * @author winixi
 * @date 2020/12/30 3:59 PM
 */
public class RestfulClientTest {

  private RestfulClient client;
  private String symbol = "EOS-USD";
  private String instrumentId;

  @Before
  public void before() throws IOException {
    TestConfig config = new TestConfig();
    RestfulResponseHandler handler = new TestResponseHandler();
    client = new RestfulClient(config, handler);
    instrumentId = InstrumentUtil.getNextQuarterSymbol(symbol);
  }

  @Test
  public void futurePosition() {
    FuturePositionRequest request = new FuturePositionRequest(instrumentId);
    FuturePositionResponse response = client.execute(request);
    System.out.println(response.toString());
    Assert.assertTrue(response.isResult());
  }

  @Test
  public void closePosition() {
    ClosePositionRequest request = new ClosePositionRequest(instrumentId, Direction.LONG);
    ClosePositionResponse response = client.execute(request);
    System.out.println(response.toString());
    Assert.assertTrue(response.isResult());
  }

  @Test
  public void marginMode() {
    MarginModeRequest request = new MarginModeRequest(symbol, MarginMode.fixed);
    MarginModeResponse response = client.execute(request);
    System.out.println(response.toString());
    Assert.assertTrue(response.isResult());
  }

  @Test
  public void orders() {
    FutureOrdersGetRequest request = new FutureOrdersGetRequest(instrumentId, "2", "", "", "");
    FutureOrdersGetResponse response = client.execute(request);
    System.out.println(response.toString());
    Assert.assertTrue(response.isResult());
  }

  @Test
  public void leverageRatio() {
    LeverageRatioRequest request = new LeverageRatioRequest(1, symbol, instrumentId, Direction.LONG);
    LeverageRatioResponse response = client.execute(request);
    System.out.println(response.toString());
    Assert.assertTrue(response.isResult());
  }

  @Test
  public void newOrder() {
    int size = 1;
    NewOrderRequest request = new NewOrderRequest(instrumentId, OrderDirection.OPEN_LONG, size);
    NewOrderResponse response = client.execute(request);
    System.out.println(response.toString());
    Assert.assertTrue(response.isResult());
  }

  @Test
  public void candles() {
    String start = "2021-01-01 00:00:00";
    String end = "2021-01-06 14:00:00";
    String grandularity = "3600";
    CandlesGetRequest request = new CandlesGetRequest(instrumentId, start, end, grandularity);
    CandlesGetResponse response = client.execute(request);
    System.out.println(response.toString());
    Assert.assertTrue(response.isResult());
  }
}
