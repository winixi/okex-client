package sh.evc.sdk.okex.client.domain;

/**
 * ticker
 *
 * @author winixi
 * @date 2021/1/7 5:09 PM
 */
public class Ticker {

  /**
   * 合约名称，如BTC-USD-170310,BTC-USDT-191227
   */
  private String instrumentId;

  /**
   * 最新成交价
   */
  private String last;

  /**
   * 卖一价
   */
  private String bestAsk;

  /**
   * 买一价
   */
  private String bestBid;

  /**
   * 24小时最高价
   */
  private String high_24h;

  /**
   * 24小时最低价
   */
  private String low_24h;

  /**
   * 24小时成交量（按张数统计）
   */
  private String volume_24h;

  /**
   * 系统时间戳
   */
  private String timestamp;

  /**
   * 持仓量
   */
  private String openInterest;

  /**
   * 24小时开盘价
   */
  private String open_24h;

  /**
   * 成交量（按币统计）
   */
  private String volumeToken_24h;

  /**
   * 最新成交的数量
   */
  private String lastQty;

  /**
   * 卖一价对应的量
   */
  private String bestAskSize;

  /**
   * 买一价对应的数量
   */
  private String bestBidSize;

  public String getInstrumentId() {
    return instrumentId;
  }

  public void setInstrumentId(String instrumentId) {
    this.instrumentId = instrumentId;
  }

  public String getLast() {
    return last;
  }

  public void setLast(String last) {
    this.last = last;
  }

  public String getBestAsk() {
    return bestAsk;
  }

  public void setBestAsk(String bestAsk) {
    this.bestAsk = bestAsk;
  }

  public String getBestBid() {
    return bestBid;
  }

  public void setBestBid(String bestBid) {
    this.bestBid = bestBid;
  }

  public String getHigh_24h() {
    return high_24h;
  }

  public void setHigh_24h(String high_24h) {
    this.high_24h = high_24h;
  }

  public String getLow_24h() {
    return low_24h;
  }

  public void setLow_24h(String low_24h) {
    this.low_24h = low_24h;
  }

  public String getVolume_24h() {
    return volume_24h;
  }

  public void setVolume_24h(String volume_24h) {
    this.volume_24h = volume_24h;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  public String getOpenInterest() {
    return openInterest;
  }

  public void setOpenInterest(String openInterest) {
    this.openInterest = openInterest;
  }

  public String getOpen_24h() {
    return open_24h;
  }

  public void setOpen_24h(String open_24h) {
    this.open_24h = open_24h;
  }

  public String getVolumeToken_24h() {
    return volumeToken_24h;
  }

  public void setVolumeToken_24h(String volumeToken_24h) {
    this.volumeToken_24h = volumeToken_24h;
  }

  public String getLastQty() {
    return lastQty;
  }

  public void setLastQty(String lastQty) {
    this.lastQty = lastQty;
  }

  public String getBestAskSize() {
    return bestAskSize;
  }

  public void setBestAskSize(String bestAskSize) {
    this.bestAskSize = bestAskSize;
  }

  public String getBestBidSize() {
    return bestBidSize;
  }

  public void setBestBidSize(String bestBidSize) {
    this.bestBidSize = bestBidSize;
  }

  @Override
  public String toString() {
    return "Ticker{" +
            "instrumentId='" + instrumentId + '\'' +
            ", last='" + last + '\'' +
            ", bestAsk='" + bestAsk + '\'' +
            ", bestBid='" + bestBid + '\'' +
            ", high_24h='" + high_24h + '\'' +
            ", low_24h='" + low_24h + '\'' +
            ", volume_24h='" + volume_24h + '\'' +
            ", timestamp='" + timestamp + '\'' +
            ", openInterest='" + openInterest + '\'' +
            ", open_24h='" + open_24h + '\'' +
            ", volumeToken_24h='" + volumeToken_24h + '\'' +
            ", lastQty='" + lastQty + '\'' +
            ", bestAskSize='" + bestAskSize + '\'' +
            ", bestBidSize='" + bestBidSize + '\'' +
            '}';
  }
}
