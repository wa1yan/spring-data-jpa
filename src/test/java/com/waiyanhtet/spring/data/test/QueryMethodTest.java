package com.waiyanhtet.spring.data.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.waiyanhtet.spring.data.jpa.config.JpaConfiguration;
import com.waiyanhtet.spring.data.jpa.repo.StateRepo;

@SpringJUnitConfig(classes = JpaConfiguration.class)
public class QueryMethodTest {
	
	@Autowired
	private StateRepo stateRepo;

	@Disabled
	@ParameterizedTest
	@CsvSource(value = {"""
			Ayeyarwady, Pathein
			"""})
	void find_by_name_test(String name, String city) {
		var state = stateRepo.findByName(name);
		assertThat(state, allOf(
				hasProperty("name", is(name)),
				hasProperty("capital", is(city))
		));
	}
	
	@Disabled
	@ParameterizedTest
	@CsvSource(value = {"""
			Lower, 4
			"""})
	void find_by_region_test(String region, int size) {
		var state = stateRepo.findByRegion(region);
		assertThat(state, hasSize(size));
	}
	
	@Disabled
	@ParameterizedTest
	@CsvSource(value = {"""
			Ya, 2
			"""})
	void find_by_name_like_test(String name, int size) {
		var state = stateRepo.findByNameLike(name.concat("%"));
		assertThat(state, hasSize(size));
	}
}
