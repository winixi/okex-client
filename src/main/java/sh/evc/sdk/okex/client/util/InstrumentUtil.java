package sh.evc.sdk.okex.client.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * instrument
 *
 * @author winixi
 * @date 2020/12/26 9:41 PM
 */
public class InstrumentUtil {

  private final static String DATE_FORMAT = "yyMMdd";

  private static boolean in(int value, Integer[] array) {
    for (int v : array) {
      if (v == value) {
        return true;
      }
    }
    return false;
  }

  public static void main(String args[]) {
    System.out.println(getCurrentQuarter());
    System.out.println(getNextQuarter());
  }

  /**
   * 获取下个季度标记
   *
   * @param symbol
   * @return
   */
  public static String getNextQuarterSymbol(String symbol) {
    return symbol + "-" + getNextQuarter();
  }

  /**
   * 获取当前季度
   *
   * @param symbol
   * @return
   */
  public static String getCurrentQuarterSymbol(String symbol) {
    return symbol + "-" + getCurrentQuarter();
  }

  /**
   * 获取当前季度
   *
   * @return
   */
  public static String getCurrentQuarter() {
    Calendar cal = Calendar.getInstance();
    //获取当前月份
    int month = cal.get(Calendar.MONTH);
    int _month = 2;
    if (in(month, new Integer[]{3, 4, 5})) {
      _month = 5;
    } else if (in(month, new Integer[]{6, 7, 8})) {
      _month = 8;
    } else if (in(month, new Integer[]{9, 10, 11})) {
      _month = 11;
    }
    int year = cal.get(Calendar.YEAR);

    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
    return sdf.format(getLastFriday(_month, year));
  }

  /**
   * 获取下个季度日期
   *
   * @return
   */
  public static String getNextQuarter() {
    Calendar cal = Calendar.getInstance();
    //获取当前月份
    int month = cal.get(Calendar.MONTH);
    int _month = 5;
    if (in(month, new Integer[]{3, 4, 5})) {
      _month = 8;
    } else if (in(month, new Integer[]{6, 7, 8})) {
      _month = 11;
    } else if (in(month, new Integer[]{9, 10, 11})) {
      _month = 2;
      //年份+1
      cal.add(Calendar.YEAR, 1);
    }
    int year = cal.get(Calendar.YEAR);

    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
    return sdf.format(getLastFriday(_month, year));
  }

  /**
   * 获取指定年月的最后一个星期五的日期
   *
   * @param month
   * @param year
   * @return
   */
  public static Date getLastFriday(int month, int year) {
    Calendar cal = Calendar.getInstance();
    cal.set(year, month + 1, 1);
    cal.add(Calendar.DAY_OF_MONTH, -(cal.get(Calendar.DAY_OF_WEEK) % 7 + 1));
    return cal.getTime();
  }
}
