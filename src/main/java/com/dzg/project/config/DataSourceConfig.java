package com.dzg.project.config;

import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 数据库Bean配置
 *
 * @author dingzhenggang
 * @since V1.0 2019-05-06
 */
@Configuration
public class DataSourceConfig {

  private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

  @Bean(name = "dataSource")
  @ConfigurationProperties(prefix = "spring.datasource.hikari")
  public DataSource dataSource() {
    logger.info("DataSource Create...");
    return DataSourceBuilder.create().type(HikariDataSource.class).build();
  }
}
