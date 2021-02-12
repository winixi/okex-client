package sh.evc.sdk.okex.client.response;

/**
 * table response
 *
 * @author winixi
 * @date 2020/12/27 11:21 AM
 */
public class TableResponse<T> {

  private String table;
  private String action;
  private T data;

  public String getTable() {
    return table;
  }

  public void setTable(String table) {
    this.table = table;
  }

  public String getAction() {
    return action;
  }

  public void setAction(String action) {
    this.action = action;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  @Override
  public String toString() {
    return "TableResponse{" +
            "table='" + table + '\'' +
            ", action='" + action + '\'' +
            ", data=" + data +
            '}';
  }
}
