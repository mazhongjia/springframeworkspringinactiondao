package com.mzj.springframework.dao._04_springdata_jpa.config;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@EnableJpaRepositories("com.mzj.springframework.dao._04_springdata_jpa")
public class SpringDataJpaConfig {
  
  @Bean
  public DataSource dataSource() {
    return new EmbeddedDatabaseBuilder()
        .addScript("classpath:/spittr/db/springdataJPA/schema.sql")
        .addScript("classpath:/spittr/db/springdataJPA/test-data.sql")
        .build();
  }
  
//  @Bean
//  public JpaTransactionManager transactionManager() {
//    return new JpaTransactionManager(); // does this need an emf???
//  }
//
//  @Bean
//  public HibernateJpaVendorAdapter jpaVendorAdapter() {
//    HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
//    adapter.setDatabase(Database.H2);
//    adapter.setShowSql(false);
//    adapter.setGenerateDdl(true);
//    return adapter;
//  }
//
//  @Bean
//  public Object emf() {
//    LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
//    emf.setDataSource(dataSource());
//    emf.setPersistenceUnitName("spitter");
//    emf.setJpaVendorAdapter(jpaVendorAdapter());
//    return emf;
//  }
  
}
