package com.waiyanhtet.spring.data.test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.waiyanhtet.spring.data.jpa.config.JpaConfiguration;
import com.waiyanhtet.spring.data.jpa.repo.DistrictRepoWithNativeQuery;

@SpringJUnitConfig(classes = JpaConfiguration.class)
public class DistrictNativeProjectionTest {

	@Autowired
	private DistrictRepoWithNativeQuery districtRepo;

	@ParameterizedTest
	@CsvSource(value = { "1, Yangon, 1257483" })
	void projection_test(int id, String name, int population) {
		var result = districtRepo.getStateNameAndPopulationById(id);
		System.out.println(result);
	}
}
