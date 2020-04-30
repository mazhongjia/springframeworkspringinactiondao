package com.mzj.springframework.dao._03_hibernate._4;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @Auther: mazhongjia
 * @Date: 2020/4/30 12:49
 * @Version: 1.0
 */
@Configuration
public class JavaConfig {

    /**
     * DBCP数据源
     *
     * @return
     */
    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:tcp://localhost/~/spitter");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        dataSource.setInitialSize(5);
        dataSource.setMaxActive(10);
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource){
        LocalSessionFactoryBean sfb = new LocalSessionFactoryBean();
        sfb.setDataSource(dataSource);
       //1、//这里不再列出Hibernate配置文件，而是使用packagesToScan属性告诉Spring扫 描一个或多个包以查找某些类，这些类通过注解的方式表明要使用 Hibernate进行持久化
        //2、这些类可以使用的注解包括JPA的@Entity 或@MappedSuperclass以及Hibernate的@Entity
        sfb.setPackagesToScan(new String[]{"com.habuma.spittr.domain"});
        Properties properties = new Properties();
        properties.setProperty("dialect","org.hibernate.dialect.H2Dialect");
        sfb.setHibernateProperties(properties);
        return sfb;
    }
}
