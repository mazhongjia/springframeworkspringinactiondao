package com.mzj.springframework.dao._03_hibernate._4;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
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
     * 嵌入式数据源
     *
     * @return
     */
    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder edb = new EmbeddedDatabaseBuilder();
        edb.setType(EmbeddedDatabaseType.H2);
        edb.addScript("spittr/db/hibernate4/schema.sql");
        edb.addScript("spittr/db/hibernate4/test-data.sql");
        EmbeddedDatabase embeddedDatabase = edb.build();
        return embeddedDatabase;
    }

    /**
     * 2.1、补充通过模板的方式（使用HibernateTemplate）实现时异常转换功能：
     *
     * 使用上下文Session时：只需在Spring应用上下文中添加一 个PersistenceExceptionTranslationPostProcessor bean即可实现添加异常转换功能
     * 原理：
     * PersistenceExceptionTranslationPostProcessor是一个
     * bean 后置处理器（bean post-processor），它会在所有拥
     * 有@Repository注解的类上添加一个通知器（advisor），这样就会
     * 捕获任何平台相关的异常并以Spring非检查型数据访问异常的形式重
     * 新抛出。
     *
     * @return
     */
    @Bean
    public BeanPostProcessor persistenceTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource){
        LocalSessionFactoryBean sfb = new LocalSessionFactoryBean();
        sfb.setDataSource(dataSource);
       //1、//这里不再列出Hibernate配置文件，而是使用packagesToScan属性告诉Spring扫 描一个或多个包以查找某些类，这些类通过注解的方式表明要使用 Hibernate进行持久化
        //2、这些类可以使用的注解包括JPA的@Entity 或@MappedSuperclass以及Hibernate的@Entity
        sfb.setPackagesToScan(new String[]{"com.habuma.spittr.domain"});
        Properties properties = new Properties();
        properties.setProperty("dialect","org.hibernate.dialect.H2Dialect");//dataSource和hibernateProperties属性都声明了从哪里获取数据库连接以及要使用哪一种数据库
        sfb.setHibernateProperties(properties);
        return sfb;
    }
}
