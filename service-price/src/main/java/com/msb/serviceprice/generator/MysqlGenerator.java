package com.msb.serviceprice.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class MysqlGenerator {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://127.0.0.1:3306/service-price?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai",
                "root","123456")
                .globalConfig(builder -> {
                    builder.author("child").fileOverride().outputDir("D:\\Java\\Onlie-taxi-public\\service-price\\src\\main\\java");
                })
                .packageConfig(builder -> {
                    builder.parent("com.msb.serviceprice").pathInfo(Collections.singletonMap(OutputFile.mapper,
                            "D:\\Java\\Onlie-taxi-public\\service-price\\src\\main\\java\\com\\msb\\serviceprice\\mapper"));
                })
                .strategyConfig(builder -> {
                    builder.addInclude("price_rule");
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
