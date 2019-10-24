package com.dzg.project.configUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取Mybatis配置文件的方法
 */
public class MybatisGenPropertyUtil {

  private static final Logger logger = LoggerFactory.getLogger(MybatisGenPropertyUtil.class);
  private static Properties props;

  static {
    loadProps();
  }

  /**
   * 加载配置文件
   */
  synchronized static private void loadProps() {
    props = new Properties();
    InputStream in = null;
    try {
      in = MybatisGenPropertyUtil.class.getResourceAsStream("/config/mybatis-plus-gen.properties");
      props.load(in);
    } catch (Exception e) {
      logger.error("Load configuration file mybatis-plus-gen.properties failed", e);
    } finally {
      try {
        if (null != in) {
          in.close();
        }
      } catch (IOException e) {
        logger.error("File mybatis-plus-gen.properties  streams closes abnormality", e);
      }
    }
  }

  public static String getProperty(String key) {
    if (null == props) {
      loadProps();
    }
    return props.getProperty(key);
  }

  public static String getProperty(String key, String defaultValue) {
    if (null == props) {
      loadProps();
    }
    return props.getProperty(key, defaultValue);
  }
}
