package sh.evc.sdk.okex.client.domain;

/**
 * 逐仓返回结构
 *
 * @author winixi
 * @date 2020/12/30 3:30 PM
 */
public class FutureHolding {

  /**
   * 多仓数量
   */
  private String longQty = "0";

  /**
   * 多仓可平仓数量
   */
  private String longAvailQty;

  /**
   * 多仓保证金
   */
  private String longMargig;

  /**
   * 多仓强平价格
   */
  private String longLiquiPrice;

  /**
   * 多仓盈亏比
   */
  private String longPnlRatio;

  /**
   * 开仓平均价
   */
  private String longAvgCost;

  /**
   * 结算基准价
   */
  private String longSettlementPrice;

  /**
   * 已实现盈余
   */
  private String realisedPnl;

  /**
   * 多仓杠杆倍数
   */
  private String longLeverage;

  /**
   * 空仓数量
   */
  private String shortQty = "0";

  /**
   * 空仓可平仓数量
   */
  private String shortAvailQty;

  /**
   * 空仓保证金
   */
  private String shortMargin;

  /**
   * 空仓强平价格
   */
  private String shortLiquiPrice;

  /**
   * 空仓盈亏比
   */
  private String shortPnlRatio;

  /**
   * 开仓平均价
   */
  private String shortAvgCost;

  /**
   * 结算基准价
   */
  private String shortSettlementPrice;

  /**
   * 空仓杠杆倍数
   */
  private String shortLeverage;

  /**
   * 合约ID，如BTC-USD-180213,BTC-USDT-191227
   */
  private String instrumentId;

  /**
   * 创建时间
   */
  private String createdAt;

  /**
   * 最近一次加减仓的更新时间
   */
  private String updatedAt;

  /**
   * 空仓保证金率
   */
  private String shortMarginRatio;

  /**
   * 空仓维持保证金率
   */
  private String shortMaintMarginRatio;

  /**
   * 空仓收益
   */
  private String shortPnl;

  /**
   * 空仓未实现盈亏
   */
  private String shortUnrealisedPnl;

  /**
   * 空仓已结算收益
   */
  private String shortSettledPnl;

  /**
   * 多仓保证金率
   */
  private String longMarginRatio;

  /**
   * 多仓维持保证金率
   */
  private String longMaintMarginRatio;

  /**
   * 多仓收益
   */
  private String longPnl;

  /**
   * 多仓未实现盈亏
   */
  private String longUnrealisedPnl;

  /**
   * 多仓已结算收益
   */
  private String longSettledPnl;

  /**
   * 最新成交价
   */
  private String last;

  public String getLongQty() {
    return longQty;
  }

  public void setLongQty(String longQty) {
    this.longQty = longQty;
  }

  public String getLongAvailQty() {
    return longAvailQty;
  }

  public void setLongAvailQty(String longAvailQty) {
    this.longAvailQty = longAvailQty;
  }

  public String getLongMargig() {
    return longMargig;
  }

  public void setLongMargig(String longMargig) {
    this.longMargig = longMargig;
  }

  public String getLongLiquiPrice() {
    return longLiquiPrice;
  }

  public void setLongLiquiPrice(String longLiquiPrice) {
    this.longLiquiPrice = longLiquiPrice;
  }

  public String getLongPnlRatio() {
    return longPnlRatio;
  }

  public void setLongPnlRatio(String longPnlRatio) {
    this.longPnlRatio = longPnlRatio;
  }

  public String getLongAvgCost() {
    return longAvgCost;
  }

  public void setLongAvgCost(String longAvgCost) {
    this.longAvgCost = longAvgCost;
  }

  public String getLongSettlementPrice() {
    return longSettlementPrice;
  }

  public void setLongSettlementPrice(String longSettlementPrice) {
    this.longSettlementPrice = longSettlementPrice;
  }

  public String getRealisedPnl() {
    return realisedPnl;
  }

  public void setRealisedPnl(String realisedPnl) {
    this.realisedPnl = realisedPnl;
  }

  public String getLongLeverage() {
    return longLeverage;
  }

  public void setLongLeverage(String longLeverage) {
    this.longLeverage = longLeverage;
  }

  public String getShortQty() {
    return shortQty;
  }

  public void setShortQty(String shortQty) {
    this.shortQty = shortQty;
  }

  public String getShortAvailQty() {
    return shortAvailQty;
  }

  public void setShortAvailQty(String shortAvailQty) {
    this.shortAvailQty = shortAvailQty;
  }

  public String getShortMargin() {
    return shortMargin;
  }

  public void setShortMargin(String shortMargin) {
    this.shortMargin = shortMargin;
  }

  public String getShortLiquiPrice() {
    return shortLiquiPrice;
  }

  public void setShortLiquiPrice(String shortLiquiPrice) {
    this.shortLiquiPrice = shortLiquiPrice;
  }

  public String getShortPnlRatio() {
    return shortPnlRatio;
  }

  public void setShortPnlRatio(String shortPnlRatio) {
    this.shortPnlRatio = shortPnlRatio;
  }

  public String getShortAvgCost() {
    return shortAvgCost;
  }

  public void setShortAvgCost(String shortAvgCost) {
    this.shortAvgCost = shortAvgCost;
  }

  public String getShortSettlementPrice() {
    return shortSettlementPrice;
  }

  public void setShortSettlementPrice(String shortSettlementPrice) {
    this.shortSettlementPrice = shortSettlementPrice;
  }

  public String getShortLeverage() {
    return shortLeverage;
  }

  public void setShortLeverage(String shortLeverage) {
    this.shortLeverage = shortLeverage;
  }

  public String getInstrumentId() {
    return instrumentId;
  }

  public void setInstrumentId(String instrumentId) {
    this.instrumentId = instrumentId;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }

  public String getShortMarginRatio() {
    return shortMarginRatio;
  }

  public void setShortMarginRatio(String shortMarginRatio) {
    this.shortMarginRatio = shortMarginRatio;
  }

  public String getShortMaintMarginRatio() {
    return shortMaintMarginRatio;
  }

  public void setShortMaintMarginRatio(String shortMaintMarginRatio) {
    this.shortMaintMarginRatio = shortMaintMarginRatio;
  }

  public String getShortPnl() {
    return shortPnl;
  }

  public void setShortPnl(String shortPnl) {
    this.shortPnl = shortPnl;
  }

  public String getShortUnrealisedPnl() {
    return shortUnrealisedPnl;
  }

  public void setShortUnrealisedPnl(String shortUnrealisedPnl) {
    this.shortUnrealisedPnl = shortUnrealisedPnl;
  }

  public String getShortSettledPnl() {
    return shortSettledPnl;
  }

  public void setShortSettledPnl(String shortSettledPnl) {
    this.shortSettledPnl = shortSettledPnl;
  }

  public String getLongMarginRatio() {
    return longMarginRatio;
  }

  public void setLongMarginRatio(String longMarginRatio) {
    this.longMarginRatio = longMarginRatio;
  }

  public String getLongMaintMarginRatio() {
    return longMaintMarginRatio;
  }

  public void setLongMaintMarginRatio(String longMaintMarginRatio) {
    this.longMaintMarginRatio = longMaintMarginRatio;
  }

  public String getLongPnl() {
    return longPnl;
  }

  public void setLongPnl(String longPnl) {
    this.longPnl = longPnl;
  }

  public String getLongUnrealisedPnl() {
    return longUnrealisedPnl;
  }

  public void setLongUnrealisedPnl(String longUnrealisedPnl) {
    this.longUnrealisedPnl = longUnrealisedPnl;
  }

  public String getLongSettledPnl() {
    return longSettledPnl;
  }

  public void setLongSettledPnl(String longSettledPnl) {
    this.longSettledPnl = longSettledPnl;
  }

  public String getLast() {
    return last;
  }

  public void setLast(String last) {
    this.last = last;
  }

  @Override
  public String toString() {
    return "FuturesHolding{" +
            "longQty='" + longQty + '\'' +
            ", longAvailQty='" + longAvailQty + '\'' +
            ", longMargig='" + longMargig + '\'' +
            ", longLiquiPrice='" + longLiquiPrice + '\'' +
            ", longPnlRatio='" + longPnlRatio + '\'' +
            ", longAvgCost='" + longAvgCost + '\'' +
            ", longSettlementPrice='" + longSettlementPrice + '\'' +
            ", realisedPnl='" + realisedPnl + '\'' +
            ", longLeverage='" + longLeverage + '\'' +
            ", shortQty='" + shortQty + '\'' +
            ", shortAvailQty='" + shortAvailQty + '\'' +
            ", shortMargin='" + shortMargin + '\'' +
            ", shortLiquiPrice='" + shortLiquiPrice + '\'' +
            ", shortPnlRatio='" + shortPnlRatio + '\'' +
            ", shortAvgCost='" + shortAvgCost + '\'' +
            ", shortSettlementPrice='" + shortSettlementPrice + '\'' +
            ", shortLeverage='" + shortLeverage + '\'' +
            ", instrumentId='" + instrumentId + '\'' +
            ", createdAt='" + createdAt + '\'' +
            ", updatedAt='" + updatedAt + '\'' +
            ", shortMarginRatio='" + shortMarginRatio + '\'' +
            ", shortMaintMarginRatio='" + shortMaintMarginRatio + '\'' +
            ", shortPnl='" + shortPnl + '\'' +
            ", shortUnrealisedPnl='" + shortUnrealisedPnl + '\'' +
            ", shortSettledPnl='" + shortSettledPnl + '\'' +
            ", longMarginRatio='" + longMarginRatio + '\'' +
            ", longMaintMarginRatio='" + longMaintMarginRatio + '\'' +
            ", longPnl='" + longPnl + '\'' +
            ", longUnrealisedPnl='" + longUnrealisedPnl + '\'' +
            ", longSettledPnl='" + longSettledPnl + '\'' +
            ", last='" + last + '\'' +
            '}';
  }
}
