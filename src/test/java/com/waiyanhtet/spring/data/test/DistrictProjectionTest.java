package com.waiyanhtet.spring.data.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.waiyanhtet.spring.data.jpa.config.JpaConfiguration;
import com.waiyanhtet.spring.data.jpa.repo.DistrictRepo;

@SpringJUnitConfig(classes = JpaConfiguration.class)
public class DistrictProjectionTest {

	@Autowired
	private DistrictRepo districtRepo;
	
	@Disabled
	@ParameterizedTest
	@CsvSource(value = {
			"Hlaing%, 2"
	})
	void projection_test(String name, int size) {
		var result = districtRepo.findByNameLike(name);
		assertThat(result.size(), is(size));
	}
	
	
	@ParameterizedTest
	@CsvSource(value = {
			"Yangon, 4"
	})
	void projection_from_child_test(String name, int size) {
		var result = districtRepo.findByStateName(name);
		result.forEach(s -> System.out.println(s.getName()));
		assertThat(result.size(), is(size));
	}
}
