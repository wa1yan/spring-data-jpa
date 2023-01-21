package com.waiyanhtet.spring.data.jpa.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.waiyanhtet.spring.data.jpa.entity.District;

public interface DistrictRepo extends JpaRepository<District, Integer> {

	List<District> findByNameLike(String name);
	List<District> findByStateName(String name);
}
