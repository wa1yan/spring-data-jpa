package com.waiyanhtet.spring.data.jpa.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.waiyanhtet.spring.data.jpa.entity.State;
import com.waiyanhtet.spring.data.jpa.entity.State.Type;
import com.waiyanhtet.spring.data.jpa.repo.custom.StateRepoGeneric;

@Transactional(readOnly = true)
@Service
public class StateService {
	
	@Autowired
	private StateRepoGeneric stateRepo;
	
	public List<State> search(String name, String region, Type type) {
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
		
		return stateRepo.search(sb.toString(), params);
	}

}
