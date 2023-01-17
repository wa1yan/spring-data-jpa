package com.waiyanhtet.spring.data.jpa.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.waiyanhtet.spring.data.jpa.entity.State;

public interface StateRepo extends JpaRepository<State, Integer>{
	
	State deleteByName(String name);

	State findByName(String name);
	
	State deleteByRegion(String region);
	
	List<State> findByRegion(String region);

	List<State> findByNameLike(String name);
	
	List<State> findByNameContains(String name);

	//State updateByName(String name);
	
}
