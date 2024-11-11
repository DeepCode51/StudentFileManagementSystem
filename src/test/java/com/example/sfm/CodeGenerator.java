package com.example.sfm;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.example.sfm.base.pojo.BaseEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author wym
 * @date 2020/9/18
 */
public class CodeGenerator {

    /**
     * 读取控制台内容
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

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("lihai");
        gc.setOpen(false);
        gc.setDateType(DateType.TIME_PACK);
        gc.setMapperName("%sMapper");
        gc.setServiceName("%sService");
        gc.setIdType(IdType.ASSIGN_UUID);
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        /**
         * 数据源配置
         */
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        // 数据库类型,默认MYSQL
        dataSourceConfig.setDbType(DbType.MYSQL);
        //自定义数据类型转换
        dataSourceConfig.setTypeConvert(new MySqlTypeConvert());
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/sfm_dw?useUnicode=true&amp&characterEncoding=utf-8&serverTimezone=GMT%2b8");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("123456");
        mpg.setDataSource(dataSourceConfig);

        /**
         * 包配置
         */
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.example.sfm");
        pc.setEntity("pojo");
        pc.setService("service");
        pc.setServiceImpl(null);
        pc.setController("controller");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);

        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setRestControllerStyle(false);
        strategy.setEntityLombokModel(true);
        strategy.setTablePrefix("sfm_");
        strategy.setSuperEntityClass(BaseEntity.class);
        strategy.setSuperEntityColumns(new String[]{"id", "create_date", "create_by", "update_date", "update_by", "del_flag"});
        mpg.setStrategy(strategy);


        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null)
                .setEntity(null)
                .setMapper(null)
                .setService(null)
                .setServiceImpl(null)
                .setController(null);
        mpg.setTemplate(templateConfig);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 当前项目路径
        // 实体类文件输出
        focList.add(new FileOutConfig("templates/flt/sfmEntity.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/src/main/java/com/example/sfm/pojo/SFM" + tableInfo.getEntityName() + StringPool.DOT_JAVA;
            }
        });

        // service类文件输出
        focList.add(new FileOutConfig("templates/flt/sfmService.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/src/main/java/com/example/sfm/service/SFM" + tableInfo.getServiceName() + StringPool.DOT_JAVA;
            }
        });

        // mapper接口文件输出
        focList.add(new FileOutConfig("templates/flt/sfmMapper.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/src/main/java/com/example/sfm/mapper/SFM" + tableInfo.getMapperName() + StringPool.DOT_JAVA;
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        mpg.execute();
    }

}
