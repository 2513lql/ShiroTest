package com.lql.chapter6;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by LQL on 2016/8/14.
 */
public class JdbcTemplateUtils {


    private static JdbcTemplate jdbcTemplate;

    public static JdbcTemplate jdbcTemplate(){
        if (jdbcTemplate == null){
            return createJdbcTemplate();
        }
        return jdbcTemplate;
    }

    private static JdbcTemplate createJdbcTemplate() {

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/shiro6");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        return new JdbcTemplate(dataSource);
    }


}
