package com.ternence.mybatis.reverse;

import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.generator.internal.NullProgressCallback;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * create by 陶江航 at 2017/10/21 23:25
 *
 * @version 1.0
 * @email taojianghang@xinzhentech.com
 * @description MyBatis的逆向工程的代码生成器
 */
public class MyBatisGeneratorTest {

    @Test
    public void generator() throws IOException, XMLParserException,
            InvalidConfigurationException, SQLException, InterruptedException {
        List<String> warnings = new ArrayList<>();
        //项目根路径不要有中文,我的有中文,所以使用绝对路径
        File configFile = new File("D:\\Work-Files\\shiro-permission-manager\\src\\test\\resources\\mybatis-generate-config.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }
}
