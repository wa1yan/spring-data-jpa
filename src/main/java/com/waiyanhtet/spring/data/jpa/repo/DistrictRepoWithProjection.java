package com.waiyanhtet.spring.data.jpa.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.waiyanhtet.spring.data.jpa.dto.DistrictData;
import com.waiyanhtet.spring.data.jpa.entity.District;

public interface DistrictRepoWithProjection extends JpaRepository<District, Integer>{

	List<DistrictData> findByNameLike(String name);
	List<DistrictData> findByStateName(String name);
	
}
