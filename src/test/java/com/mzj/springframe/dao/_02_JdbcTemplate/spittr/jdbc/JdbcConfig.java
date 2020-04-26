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

  /**
   * 数据源bean（这里是测试数据源（嵌入式数据源））
   * @return
   */
  @Bean
  public DataSource dataSource() {
    return new EmbeddedDatabaseBuilder()
      .setType(EmbeddedDatabaseType.H2)
      .addScripts("classpath:spittr/db/jdbc/schema.sql", "classpath:spittr/db/jdbc/test-data.sql")
      .build();
  }

  /**
   * JdbcTemplate bean，依赖【数据源bean】
   * @param dataSource
   * @return
   */
  @Bean
  public JdbcTemplate jdbcTemplate(DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }

  /**
   * Repository1 bean（DAO），依赖JdbcTemplate
   * @param jdbcTemplate
   * @return
   */
  @Bean
  public SpitterRepository spitterRepository(JdbcTemplate jdbcTemplate) {
    return new JdbcSpitterRepository(jdbcTemplate);
  }

  /**
   * Repository2 bean（DAO），依赖JdbcTemplate
   * @param jdbcTemplate
   * @return
   */
  @Bean
  public SpittleRepository spittleRepository(JdbcTemplate jdbcTemplate) {
    return new JdbcSpittleRepository(jdbcTemplate);
  }
  
  @Bean
  public PlatformTransactionManager transactionManager(DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }

}
