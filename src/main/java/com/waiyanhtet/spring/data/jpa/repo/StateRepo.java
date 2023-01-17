package com.waiyanhtet.spring.data.jpa.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.waiyanhtet.spring.data.jpa.entity.State;

public interface StateRepo extends JpaRepository<State, Integer>{

	State findByName(String name);
	
	List<State> findByRegion(String region);
	
	List<State> findByNameContains(String name);
	
}
