package com.mzj.springframework.dao._04_springdata_jpa.db;

import java.util.List;

import com.mzj.springframework.dao._04_springdata_jpa.vo.Spitter;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface with operations for {@link Spitter} persistence.
 * @author habuma
 */
public interface SpitterRepository extends JpaRepository<Spitter, Long> {

	/**
	 * 并不需要我们实现findByUsername()方法。方法签名已经告诉Spring Data JPA足够的信息来创建这个方法的实现了
	 * @param username
	 * @return
	 */
	Spitter findByUsername(String username);
	
	List<Spitter> findByUsernameOrFullNameLike(String username, String fullName);

}
