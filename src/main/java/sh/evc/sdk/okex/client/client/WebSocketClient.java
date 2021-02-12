package sh.evc.sdk.okex.client.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sh.evc.sdk.okex.client.config.OkexClientConfig;
import sh.evc.sdk.okex.client.handler.WebSocketClientHandler;
import sh.evc.sdk.okex.client.handler.WebSocketMsgHandler;
import sh.evc.sdk.okex.client.task.MoniterTask;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;

/**
 * 使用netty的websocket客户端
 *
 * @author winixi
 * @date 2018/5/16 2:35 PM
 */
public class WebSocketClient {

  private final static Logger logger = LoggerFactory.getLogger(WebSocketClient.class);

  private WebSocketMsgHandler webSocketMsgHandler;
  private MoniterTask moniterTask;
  private EventLoopGroup eventLoopGroup = null;
  private Bootstrap bootstrap = null;
  private Channel channel = null;
  private ChannelFuture future = null;
  private boolean isAlive = false;
  private OkexClientConfig config;

  private Set<String> subscribChannel = new HashSet<>();

  /**
   * 指定消息处理器
   *
   * @param msgHandler
   */
  public WebSocketClient(OkexClientConfig config, WebSocketMsgHandler msgHandler) {
    this.config = config;
    this.webSocketMsgHandler = msgHandler;
  }

  /**
   * 添加通道
   *
   * @param channel
   */
  public void addChannel(String channel) {
    if (channel == null) {
      return;
    }
    String str = "{\"op\": \"subscribe\", \"args\":[\"" + channel + "\"]}";
    this.sendMessage(str);
    subscribChannel.add(channel);
  }

  /**
   * 删除通道
   *
   * @param channel
   */
  public void removeChannel(String channel) {
    if (channel == null) {
      return;
    }
    String str = "{\"op\": \"unsubscribe\", \"args\":[\"" + channel + "\"]}";
    this.sendMessage(str);
    subscribChannel.remove(channel);
  }

  /**
   * 开始
   */
  public void start() {
    if (config.getWebsocketUrl() == null) {
      logger.error("WebSocketClient start error  url can not be null");
      return;
    }
    if (webSocketMsgHandler == null) {
      logger.error("WebSocketClient start error  WebSocketService can not be null");
      return;
    }
    //5s检查一次连接状态，断开自动重连
    moniterTask = new MoniterTask(this);
    this.connect();
    new Timer().schedule(moniterTask, 1000, 5000);
  }

  /**
   * 设置状态
   *
   * @param flag
   */
  public void setStatus(boolean flag) {
    this.isAlive = flag;
  }

  /**
   * 连接
   */
  public void connect() {
    try {
      final URI uri = new URI(config.getWebsocketUrl());
      eventLoopGroup = new NioEventLoopGroup(1);
      bootstrap = new Bootstrap();
      final SslContext sslCtx = SslContextBuilder.forClient().build();
      final WebSocketClientHandler handler = new WebSocketClientHandler(
              WebSocketClientHandshakerFactory.newHandshaker(
                      uri, WebSocketVersion.V13, null, false, new DefaultHttpHeaders(), Integer.MAX_VALUE), webSocketMsgHandler, moniterTask);

      bootstrap.group(eventLoopGroup).option(ChannelOption.TCP_NODELAY, true)
              .channel(NioSocketChannel.class)
              .handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) {
                  ChannelPipeline p = ch.pipeline();
                  p.addLast(sslCtx.newHandler(ch.alloc(), uri.getHost(), uri.getPort()));
                  p.addLast(new HttpClientCodec(), new HttpObjectAggregator(8192), handler);
                }
              });

      future = bootstrap.connect(uri.getHost(), uri.getPort());
      future.addListener((ChannelFutureListener) future -> {
      });
      channel = future.sync().channel();
      handler.handshakeFuture().sync();

      this.setStatus(true);
    } catch (Exception e) {
      logger.error("WebSocketClient start error ", e);
      eventLoopGroup.shutdownGracefully();
      this.setStatus(false);
    }
  }

  /**
   * 发送消息
   *
   * @param message
   */
  public void sendMessage(String message) {
    if (!isAlive) {
      logger.error("WebSocket is not Alive addChannel error");
    }
    channel.writeAndFlush(new TextWebSocketFrame(message));
  }

  /**
   * 发送心跳
   */
  public void sentPing() {
    String dataMsg = "ping";
    this.sendMessage(dataMsg);
  }

  /**
   * 重新连接
   */
  public void reConnect() {
    try {
      this.eventLoopGroup.shutdownGracefully();
      this.eventLoopGroup = null;
      this.connect();
      if (future.isSuccess()) {
        this.setStatus(true);
        this.sentPing();
        for (String channel : subscribChannel) {
          this.addChannel(channel);
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}

