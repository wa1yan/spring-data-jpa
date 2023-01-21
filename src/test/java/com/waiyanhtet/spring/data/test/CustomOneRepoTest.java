package com.waiyanhtet.spring.data.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.waiyanhtet.spring.data.jpa.config.JpaConfiguration;
import com.waiyanhtet.spring.data.jpa.entity.State.Type;
import com.waiyanhtet.spring.data.jpa.repo.custom.StateRepoOne;

@SpringJUnitConfig(classes = JpaConfiguration.class)
public class CustomOneRepoTest {

	@Autowired
	private StateRepoOne stateRepoOne;
	
	//@Transactional
	@ParameterizedTest
	@CsvSource(value = {
			",,,4",
			",Lower,,3",
			",,Region,3",
			"Ya, , ,2"
			
	})
	void test(String name, String region, Type type, int size) {
		
		var result = stateRepoOne.search(name, type, region);
		assertThat(result, hasSize(size));
	}
}
