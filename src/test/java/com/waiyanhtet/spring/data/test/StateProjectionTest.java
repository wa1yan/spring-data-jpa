package com.waiyanhtet.spring.data.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.waiyanhtet.spring.data.jpa.config.JpaConfiguration;
import com.waiyanhtet.spring.data.jpa.repo.StateRepo;

import jakarta.transaction.Transactional;

@SpringJUnitConfig(classes = JpaConfiguration.class)
public class StateProjectionTest {
	
	@Autowired
	private StateRepo stateRepo;
	
	@Disabled
	@ParameterizedTest
	@CsvSource(value = {
			"Lower, 3"
	})
	void projection_test(String region, int size) {
		var result = stateRepo.findByRegion(region);
		assertThat(result.size(), is(size));
	}
	
	@ParameterizedTest
	@CsvSource(value = {
			"Hlaing, 1"
	})
	void projection_from_child_test(String name, int size) {
		var result = stateRepo.findByDistrictsName(name);
		result.forEach(s -> System.out.println(s.getName()));
		assertThat(result.size(), is(size));
	}
	
	@ParameterizedTest
	@CsvSource(value = {
			"2, 1"
	})
	void projection_from_child_test(int id, int size) {
		var result = stateRepo.findById(id);
		assertThat(result.size(), is(size));
	}

}
