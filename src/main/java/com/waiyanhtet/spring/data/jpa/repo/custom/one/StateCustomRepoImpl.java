package com.waiyanhtet.spring.data.jpa.repo.custom.one;

import java.util.HashMap;
import java.util.List;

import org.springframework.util.StringUtils;

import com.waiyanhtet.spring.data.jpa.entity.State;
import com.waiyanhtet.spring.data.jpa.entity.State.Type;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class StateCustomRepoImpl implements StateCustomRepo {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<State> search(String name, Type type, String region) {
		var sb = new StringBuffer();
		sb.append("select s from State s where 1=1");
		var params = new HashMap<String, Object>();
		
		if(StringUtils.hasLength(name)) {
			sb.append(" and s.name like :name");
			params.put("name", name.concat("%"));
		}
		if(StringUtils.hasLength(region)) {
			sb.append(" and s.region = :region");
			params.put("region", region);
		}
		if(null != type) {
			sb.append(" and s.type = :type");
			params.put("type", type);
		}
		var query = em.createQuery(sb.toString(), State.class);
		
		for(var param : params.entrySet()) {
			query.setParameter(param.getKey(), param.getValue());
		}
		
		return query.getResultList();
	}

}
