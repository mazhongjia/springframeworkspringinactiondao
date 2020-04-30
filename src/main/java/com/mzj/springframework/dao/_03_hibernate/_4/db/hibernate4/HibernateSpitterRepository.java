package com.mzj.springframework.dao._03_hibernate._4.db.hibernate4;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.mzj.springframework.dao._03_hibernate._4.db.SpitterRepository;
import com.mzj.springframework.dao._03_hibernate._4.vo.Spitter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * @Repository注解：会做两件事情。
 * 1、首先，@Repository是Spring的另一种构造性注 解，它能够像其他注解一样被Spring的组件扫描所扫描到。这样就不必明确声明HibernateSpitterRepository bean了，只要这个 Repository类在组件扫描所涵盖的包中即可
 * 2、捕获平台相关的异常，然后使用Spring统一非检查型异常的形式重新抛出，但是需要配置一个bean，见JavaConfig的：2.1....
 */
@Repository
public class HibernateSpitterRepository implements SpitterRepository {

	private SessionFactory sessionFactory;

	/**
	 * 1、注入SessionFactory
	 *
	 * 通过@Inject注 解让Spring自动将一个SessionFactory注入 到HibernateSpitterRepository的sessionFactory属性
	 *
	 * @param sessionFactory
	 */
	@Inject
	public HibernateSpitterRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 2、从SessionFactory中获取当前的Session
	 *
	 * 获取当前事务的Session
	 * @return
	 */
	private Session currentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public long count() {
		return findAll().size();
	}

	/**
	 * 3、使用当前的Session
	 * @param spitter
	 * @return
	 */
	public Spitter save(Spitter spitter) {
		Serializable id = currentSession().save(spitter);  //<co id="co_UseCurrentSession"/>
		return new Spitter((Long) id, 
				spitter.getUsername(), 
				spitter.getPassword(), 
				spitter.getFullName(), 
				spitter.getEmail(), 
				spitter.isUpdateByEmail());
	}

	public Spitter findOne(long id) {
		return (Spitter) currentSession().get(Spitter.class, id); 
	}

	public Spitter findByUsername(String username) {		
		return (Spitter) currentSession() 
				.createCriteria(Spitter.class) 
				.add(Restrictions.eq("username", username))
				.list().get(0);
	}

	public List<Spitter> findAll() {
		return (List<Spitter>) currentSession() 
				.createCriteria(Spitter.class).list(); 
	}
	
}
