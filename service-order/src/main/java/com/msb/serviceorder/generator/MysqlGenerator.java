package com.msb.serviceorder.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class MysqlGenerator {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://127.0.0.1:3306/service-order?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai",
                "root","123456")
                .globalConfig(builder -> {
                    builder.author("child").fileOverride().outputDir("D:\\Java\\Onlie-taxi-public\\service-order\\src\\main\\java");
                })
                .packageConfig(builder -> {
                    builder.parent("com.msb.serviceorder").pathInfo(Collections.singletonMap(OutputFile.mapper,
                            "D:\\Java\\Onlie-taxi-public\\service-driver-user\\src\\main\\java\\com\\msb\\serviceorder\\mapper"));
                })
                .strategyConfig(builder -> {
                    builder.addInclude("order_info");
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
