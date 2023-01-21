package com.waiyanhtet.spring.data.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.waiyanhtet.spring.data.jpa.dto.StateDTO;
import com.waiyanhtet.spring.data.jpa.entity.District;

public interface DistrictRepoWithNativeQuery extends JpaRepository<District, Integer>{

	@Query( name = "getStateNameAndPopulationById", nativeQuery = true)
	StateDTO getStateNameAndPopulationById(Integer id);
}
