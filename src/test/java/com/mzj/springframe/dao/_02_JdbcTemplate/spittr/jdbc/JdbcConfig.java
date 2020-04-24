package com.mzj.springframe.dao._02_JdbcTemplate.spittr.jdbc;

import javax.sql.DataSource;

import com.mzj.springframework.dao._02_JdbcTemplate.spittr.db.SpitterRepository;
import com.mzj.springframework.dao._02_JdbcTemplate.spittr.db.SpittleRepository;
import com.mzj.springframework.dao._02_JdbcTemplate.spittr.db.jdbc.JdbcSpitterRepository;
import com.mzj.springframework.dao._02_JdbcTemplate.spittr.db.jdbc.JdbcSpittleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class JdbcConfig {

  @Bean
  public DataSource dataSource() {
    return new EmbeddedDatabaseBuilder()
      .setType(EmbeddedDatabaseType.H2)
      .addScripts("classpath:spittr/db/jdbc/schema.sql", "classpath:spittr/db/jdbc/test-data.sql")
      .build();
  }
  
  @Bean
  public JdbcTemplate jdbcTemplate(DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }
  
  @Bean
  public SpitterRepository spitterRepository(JdbcTemplate jdbcTemplate) {
    return new JdbcSpitterRepository(jdbcTemplate);
  }

  @Bean
  public SpittleRepository spittleRepository(JdbcTemplate jdbcTemplate) {
    return new JdbcSpittleRepository(jdbcTemplate);
  }
  
  @Bean
  public PlatformTransactionManager transactionManager(DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }

}
