package com.waiyanhtet.spring.data.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.waiyanhtet.spring.data.jpa.config.JpaConfiguration;
import com.waiyanhtet.spring.data.jpa.entity.State.Type;
import com.waiyanhtet.spring.data.jpa.service.StateService;

@SpringJUnitConfig(classes = JpaConfiguration.class)
public class CustomGenericRepoTest {

	@Autowired
	private StateService stateService;
	
	//@Transactional
	@ParameterizedTest
	@CsvSource(value = {
			",,,4",
			",Lower,,3",
			",,Region,3",
			"Ya, , ,2"
			
	})
	void test(String name, String region, Type type, int size) {
		
		var result = stateService.search(name, region, type);
		assertThat(result, hasSize(size));
	}
}
