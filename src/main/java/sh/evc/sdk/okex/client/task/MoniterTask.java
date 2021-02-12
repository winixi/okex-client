package sh.evc.sdk.okex.client.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sh.evc.sdk.okex.client.client.WebSocketClient;
import sh.evc.sdk.okex.client.util.DateUtil;

import java.util.Date;
import java.util.TimerTask;

/**
 * 监视器
 *
 * @author winixi
 * @date 2018/5/17 8:42 PM
 */
public class MoniterTask extends TimerTask {

  private final static Logger logger = LoggerFactory.getLogger(MoniterTask.class);

  /**
   * 启动日期
   */
  private final String today;

  private long activeTime = System.currentTimeMillis();
  private int overTime = 6000;

  private WebSocketClient client;

  public MoniterTask(WebSocketClient client) {
    this.client = client;
    logger.debug("TimerTask is starting.... ");
    today = DateUtil.dd.format(new Date());
  }

  @Override
  public void run() {
    if (System.currentTimeMillis() - activeTime > overTime
            || !DateUtil.dd.format(new Date()).equals(today)) {
      client.setStatus(false);

      logger.info("Moniter reconnect....... ");
      client.reConnect();
    }
    client.sentPing();
    logger.debug("Moniter ping data sent.... ");
  }

  public void updateTime() {
    logger.debug("startTime is update");
    activeTime = System.currentTimeMillis();
  }
}
