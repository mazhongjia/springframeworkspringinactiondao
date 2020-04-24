package com.mzj.springframework.dao._02_JdbcTemplate.spittr.db;

import java.util.List;

import com.mzj.springframework.dao._02_JdbcTemplate.spittr.domain.Spitter;

/**
 * Repository interface with operations for {@link Spitter} persistence.
 * @author habuma
 */
public interface SpitterRepository {

  long count();
  
  Spitter save(Spitter spitter);
  
  Spitter findOne(long id);

  Spitter findByUsername(String username);

  List<Spitter> findAll();

}
