package com.dzg.project.utils;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.dzg.project.configUtils.MybatisGenPropertyUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Mybatis-Plus代码生成器
 *
 * @author dingzhenggang
 */
public class MyBatisPlusGenerator {

  /**
   * 模块名称
   */
  public static String moduleName = MybatisGenPropertyUtil.getProperty("module.name");

  /**
   * 表名称，多个用,隔开
   */
  public static String tableName = MybatisGenPropertyUtil.getProperty("table.name");

  /**
   * 代码基本路径
   */
  public static String codeBasePath = MybatisGenPropertyUtil.getProperty("code.base.path");

  /**
   * 代码author
   */
  public static String codeBaseAuthor = MybatisGenPropertyUtil.getProperty("code.base.author");

  /**
   * 代码具体路径
   */
  public static String codeDetailPath = MybatisGenPropertyUtil.getProperty("code.detail.path");

  /**
   * 代码mapper路径
   */
  public static String codeDetailMapper = MybatisGenPropertyUtil.getProperty("code.detail.mapper");

  /**
   * 代码mapper路径
   */
  public static String sqlKey = MybatisGenPropertyUtil.getProperty("mysql.detail.key");

  /**
   * 数据库相关
   */
  public static String dataSourceUrl = MybatisGenPropertyUtil.getProperty("data.source.url");
  public static String dataSourceDriver = MybatisGenPropertyUtil.getProperty("data.source.driver");
  public static String dataSourceUsername = MybatisGenPropertyUtil.getProperty("data.source.username");
  public static String dataSourcePassword = MybatisGenPropertyUtil.getProperty("data.source.password");


  /**
   * 主要main方法
   *
   * @param args
   */
  public static void main(String[] args) {
    // 代码生成器
    AutoGenerator mpg = new AutoGenerator();

    // 全局配置
    GlobalConfig gc = new GlobalConfig();
    String projectPath = System.getProperty("user.dir");
    gc.setOutputDir(projectPath + codeBasePath);
    gc.setAuthor(codeBaseAuthor);
    gc.setOpen(false);
    gc.setSwagger2(true);
    gc.setIdType(IdType.AUTO);
    gc.setOpen(false);
    gc.setMapperName("%sMapper");
    gc.setXmlName("%sMapper");
    gc.setServiceName("%sService");
    gc.setServiceImplName("%sServiceImpl");
    gc.setControllerName("%sController");
    mpg.setGlobalConfig(gc);

    // 数据源配置
    DataSourceConfig dsc = new DataSourceConfig();
    dsc.setUrl(dataSourceUrl);
    dsc.setDriverName(dataSourceDriver);
    dsc.setUsername(dataSourceUsername);
    dsc.setPassword(dataSourcePassword);
    dsc.setDbType(DbType.MARIADB);
    mpg.setDataSource(dsc);

    // 包配置
    PackageConfig pc = new PackageConfig();
    pc.setModuleName(moduleName);
    //pc.setModuleName(scanner("模块名"));
    pc.setParent(codeDetailPath);
    pc.setMapper(codeDetailMapper);
    mpg.setPackageInfo(pc);

    // 自定义配置
    InjectionConfig cfg = new InjectionConfig() {
      @Override
      public void initMap() {
      }
    };

    // 如果模板引擎是 freemarker
    String templatePath = "/templates/mapper.xml.ftl";
    // 如果模板引擎是 velocity
    // String templatePath = "/templates/mapper.xml.vm";

    // 自定义输出配置
    List<FileOutConfig> focList = new ArrayList<>();
    // 自定义配置会被优先输出
    focList.add(new FileOutConfig(templatePath) {
      @Override
      public String outputFile(TableInfo tableInfo) {
        // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
        return projectPath + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
      }
    });
    cfg.setFileOutConfigList(focList);
    mpg.setCfg(cfg);

    // 配置模板
    TemplateConfig templateConfig = new TemplateConfig();

    // 配置自定义输出模板
    //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
    // templateConfig.setEntity("templates/entity2.java");
    // templateConfig.setService();
    // templateConfig.setController();

    templateConfig.setXml(null);
    mpg.setTemplate(templateConfig);

    // 策略配置
    StrategyConfig strategy = new StrategyConfig();
    strategy.setNaming(NamingStrategy.underline_to_camel);
    strategy.setColumnNaming(NamingStrategy.underline_to_camel);
    strategy.setEntityLombokModel(true);
    strategy.setRestControllerStyle(true);
    strategy.setEntityTableFieldAnnotationEnable(true);
    strategy.setEntitySerialVersionUID(true);
    strategy.setSuperEntityColumns(sqlKey);
    // 公共父类
    // 写于父类中的公共字段
    strategy.setSuperEntityColumns("id");
    strategy.setInclude(tableName.split(","));
    //strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
    strategy.setControllerMappingHyphenStyle(true);
    strategy.setTablePrefix(pc.getModuleName() + "_");
    mpg.setStrategy(strategy);
    mpg.setTemplateEngine(new FreemarkerTemplateEngine());
    mpg.execute();
  }

  /**
   * <p>
   * 读取控制台内容
   * </p>
   */
  public static String scanner(String tip) {
    Scanner scanner = new Scanner(System.in);
    StringBuilder help = new StringBuilder();
    help.append("请输入" + tip + "：");
    System.out.println(help.toString());
    if (scanner.hasNext()) {
      String ipt = scanner.next();
      if (StringUtils.isNotEmpty(ipt)) {
        return ipt;
      }
    }
    throw new MybatisPlusException("请输入正确的" + tip + "！");
  }
}
