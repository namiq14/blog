package com.myproject.blog.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.web.context.support.ServletContextParameterFactoryBean;

import com.myproject.blog.dao.UserRepository;
import com.myproject.blog.model.User;

@Repository("userRepository")
public class UserRepositoryJpaImpl implements UserRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<User> findAll() {
		return entityManager.createQuery("from User", User.class).getResultList();
	}

	@Override
	public User findById(Long id) {
		return entityManager.find(User.class, id);
	}

	@Override
	public List<User> findByLastName(String lastName) {
		return entityManager
				.createQuery("from User where lastName = :lastName", User.class)
				.setParameter("lastName", lastName)
				.getResultList();
	}

	@Override
	public void create(User user) {
		entityManager.persist(user);
	}

	@Override
	public User update(User user) {
		return entityManager.merge(user);
	}

	@Override
	public void delete(Long id) {
		entityManager.remove(entityManager.getReference(User.class, id));
	}

	@Override
	public User findByUname(String username) {
		return entityManager
				.createQuery("from User where username = :username", User.class)
				.setParameter("username", username)
				.getSingleResult();
	}
}



