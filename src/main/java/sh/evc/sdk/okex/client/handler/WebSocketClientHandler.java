package sh.evc.sdk.okex.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.channel.*;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sh.evc.sdk.okex.client.task.MoniterTask;

import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/**
 * 客户端处理器
 *
 * @author winixi
 * @date 2018/5/17 9:35 PM
 */
public class WebSocketClientHandler extends SimpleChannelInboundHandler<Object> {

  private final static Logger logger = LoggerFactory.getLogger(WebSocketClientHandler.class);

  private final WebSocketClientHandshaker handshaker;
  private ChannelPromise handshakeFuture;
  private MoniterTask moniter;
  private WebSocketMsgHandler msgHandler;

  public WebSocketClientHandler(WebSocketClientHandshaker handshaker, WebSocketMsgHandler msgHandler, MoniterTask moniter) {
    this.handshaker = handshaker;
    this.msgHandler = msgHandler;
    this.moniter = moniter;
  }

  public ChannelFuture handshakeFuture() {
    return handshakeFuture;
  }

  @Override
  public void handlerAdded(ChannelHandlerContext ctx) {
    handshakeFuture = ctx.newPromise();
  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) {
    handshaker.handshake(ctx.channel());
  }

  @Override
  public void channelInactive(ChannelHandlerContext ctx) {
    logger.info("WebSocket Client disconnected!");
  }

  @Override
  public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
    Channel ch = ctx.channel();
    moniter.updateTime();
    if (!handshaker.isHandshakeComplete()) {
      handshaker.finishHandshake(ch, (FullHttpResponse) msg);
      logger.info("WebSocket Client connected!");
      handshakeFuture.setSuccess();
      return;
    }

    //判断是否是老的http协议返回
    if (msg instanceof FullHttpResponse) {
      FullHttpResponse response = (FullHttpResponse) msg;
      throw new IllegalStateException(
              "Unexpected FullHttpResponse (headers=" + response.headers().toString() +
                      ", content=" + response.content().toString(CharsetUtil.UTF_8) + ')');
    }

    //正确的数据处理
    WebSocketFrame frame = (WebSocketFrame) msg;
    if (frame instanceof TextWebSocketFrame) {//文本数据

      TextWebSocketFrame textFrame = (TextWebSocketFrame) frame;
      msgHandler.onReceive(textFrame.text());

    } else if (frame instanceof BinaryWebSocketFrame) {

      BinaryWebSocketFrame binaryFrame = (BinaryWebSocketFrame) frame;
      msgHandler.onReceive(decodeByteBuff(binaryFrame.content()));

    } else if (frame instanceof PongWebSocketFrame) {
      logger.info("WebSocket Client received pong");

    } else if (frame instanceof CloseWebSocketFrame) {
      logger.info("WebSocket Client received closing");
      ch.close();
    }
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    cause.printStackTrace();
    if (!handshakeFuture.isDone()) {
      handshakeFuture.setFailure(cause);
    }
    ctx.close();
  }

  /**
   * 解压缩的数据流
   *
   * @param buf
   * @return
   * @throws IOException
   * @throws DataFormatException
   */
  public String decodeByteBuff(ByteBuf buf) throws IOException, DataFormatException {
    byte[] temp = new byte[buf.readableBytes()];
    ByteBufInputStream bis = new ByteBufInputStream(buf);
    bis.read(temp);
    bis.close();
    Inflater decompresser = new Inflater(true);
    decompresser.setInput(temp, 0, temp.length);
    StringBuilder sb = new StringBuilder();
    byte[] result = new byte[1024];
    while (!decompresser.finished()) {
      int resultLength = decompresser.inflate(result);
      sb.append(new String(result, 0, resultLength, "UTF-8"));
    }
    decompresser.end();
    return sb.toString();
  }
}
