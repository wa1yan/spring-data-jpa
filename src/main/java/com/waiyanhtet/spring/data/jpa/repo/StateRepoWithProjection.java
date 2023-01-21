package com.waiyanhtet.spring.data.jpa.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.waiyanhtet.spring.data.jpa.dto.StateData;
import com.waiyanhtet.spring.data.jpa.entity.State;

public interface StateRepoWithProjection extends JpaRepository<State, Integer> {

	List<StateData> findByRegion(String region);
	List<StateData> findByDistrictsName(String name);

}
