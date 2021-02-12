package sh.evc.sdk.okex.client.request;

import java.util.Arrays;

/**
 * op request
 *
 * @author winixi
 * @date 2020/12/27 11:25 AM
 */
public class OpRequest {

  private String op;
  private String[] args;

  public String getOp() {
    return op;
  }

  public void setOp(String op) {
    this.op = op;
  }

  public String[] getArgs() {
    return args;
  }

  public void setArgs(String[] args) {
    this.args = args;
  }

  @Override
  public String toString() {
    return "OpRequest{" +
            "op='" + op + '\'' +
            ", args=" + Arrays.toString(args) +
            '}';
  }
}
