package sh.evc.sdk.okex.client.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * json util
 *
 * @author winixi
 * @date 2020/12/27 11:47 AM
 */
public class JsonUtil {

  private final static Logger logger = LoggerFactory.getLogger(JsonUtil.class);
  private final static ObjectMapper mapper = new ObjectMapper();

  static {
    //忽略不存在的字段
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    //下划线转驼峰
    mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
  }

  /**
   * 转换成对象
   *
   * @param msg
   * @param reference
   * @param <T>
   * @return
   */
  public static <T> T toObject(String msg, TypeReference<T> reference) {
    try {
      return mapper.readValue(msg, reference);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      logger.error("json转换失败 -> " + msg);
    }
    return null;
  }

  /**
   * 转换成对象
   *
   * @param msg
   * @param valueType
   * @param <T>
   * @return
   */
  public static <T> T toObject(String msg, Class<T> valueType) {
    try {
      return mapper.readValue(msg, valueType);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      logger.error("json转换失败 -> " + msg);
    }
    return null;
  }

  /**
   * 转换成json
   *
   * @param object
   * @return
   */
  public static String toJson(Object object) {
    try {
      return mapper.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      logger.error("object转换失败 -> " + object.toString());
    }
    return null;
  }
}
