package com.waiyanhtet.spring.data.jpa.repo.custom.generic;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import jakarta.persistence.EntityManager;

public class BaseRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

	private EntityManager entityManager;
	
	public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityManager = entityManager;
	}

	@Override
	public List<T> search(String jpql, Map<String, Object> params) {
		var query = entityManager.createQuery(jpql,getDomainClass());
		for(var p : params.entrySet()) {
			query.setParameter(p.getKey(), p.getValue());
		}
		return query.getResultList();
	}



}
