package com.dzg.project.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.config.GlobalConfig.DbConfig;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;


/**
 * MybatisPlus配置类
 *
 * @author dingzhenggang
 * @since V1.0 2019-05-06
 */
@Configuration
@EnableTransactionManagement
public class MybatisPlusConfig {

  private static final String mapperLocations = "classpath:mapper/*Mapper.xml";

  @Resource
  private DataSource dataSource;

  /**
   * 创建全局配置
   */
  @Bean
  public GlobalConfig globalConfig() {
    // 全局配置文件
    GlobalConfig globalConfig = new GlobalConfig();
    DbConfig dbConfig = new DbConfig();
    // 默认为自增
    dbConfig.setIdType(IdType.AUTO);
    // 手动指定db 的类型, 这里是mariadb
    dbConfig.setDbType(DbType.MARIADB);
    globalConfig.setDbConfig(dbConfig);
    return globalConfig;
  }

  @Bean
  public MybatisSqlSessionFactoryBean sqlSessionFactory(GlobalConfig globalConfig) {
    MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
    sqlSessionFactory.setDataSource(dataSource);
    sqlSessionFactory.setGlobalConfig(globalConfig);

    MybatisConfiguration configuration = new MybatisConfiguration();
    // 源码里面如果有configuration, 不会注入BaseMapper里面的方法, 所以这里要这样写
    configuration.setUseGeneratedKeys(true);
    configuration.setUseColumnLabel(true);
    configuration.setMapUnderscoreToCamelCase(true);
    sqlSessionFactory.setConfiguration(configuration);
    // 设置xml扫描路径
    ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    try {
      //sqlSessionFactory.setMapperLocations(resolver.getResources(mapperLocations));
      return sqlSessionFactory;
    } catch (Exception e) {
      throw new RuntimeException("sqlSessionFactory init fail", e);
    }
  }
}

