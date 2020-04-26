package com.mzj.springframework.dao._01_dataSource._03_JDBCDriverDataSource;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * @Auther: mazhongjia
 * @Date: 2020/4/20 13:07
 * @Version: 1.0
 */
@Configuration
public class DataSourceConfiguration {

    /**
     * 基于JDBC驱动的数据源
     *
     * @return
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.h2.Driver");
        ds.setUrl("jdbc:h2:tcp://localhost/~/spitter");
        ds.setUsername("sa");
        ds.setPassword("");
        return ds;
    }
}
