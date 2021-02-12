package sh.evc.sdk.okex.client.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * date util
 *
 * @author winixi
 * @date 2020/12/26 9:46 PM
 */
public class DateUtil {

  private final static Logger logger = LoggerFactory.getLogger(DateUtil.class);
  public final static SimpleDateFormat dd = new SimpleDateFormat("dd");
  public final static SimpleDateFormat ISO8601 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S'Z'");
  public final static SimpleDateFormat UTC = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  /**
   * 转换日期格式
   *
   * @param timestamp
   * @return
   */
  public static Date getDateTime(String timestamp) {
    ISO8601.setTimeZone(TimeZone.getTimeZone("UTC"));
    try {
      return ISO8601.parse(timestamp);
    } catch (ParseException e) {
      e.printStackTrace();
      logger.error("时间转换错误 -> " + timestamp);
      return null;
    }
  }

  /**
   * UNIX timestamp ISO 8601 rule eg: 2018-02-03T05:34:14.110Z
   */
  public static String getUnixTime() {
    return new Date(System.currentTimeMillis()).toInstant().toString();
  }

  /**
   * 转换
   *
   * @param datetime
   * @return
   */
  public static String getUnixTime(String datetime) {
    if (StringUtil.isEmpty(datetime)) {
      return "";
    }
    Date date = null;
    try {
      date = UTC.parse(datetime);
    } catch (ParseException e) {
      e.printStackTrace();
      logger.error("日期转化错误{}", datetime);
    }
    return date.toInstant().toString();
  }
}
