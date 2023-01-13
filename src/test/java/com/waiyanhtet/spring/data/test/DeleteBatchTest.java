package com.waiyanhtet.spring.data.test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasSize;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.waiyanhtet.spring.data.jpa.entity.State;
import com.waiyanhtet.spring.data.jpa.entity.State.Type;
import com.waiyanhtet.spring.data.jpa.repo.StateRepo;

public class DeleteBatchTest {

	@Autowired
	private StateRepo stateRepo;

	@Order(1)
	@ParameterizedTest
	@MethodSource("saveAllParams")
	void save_all_test(List<State> states) {
		var result = stateRepo.saveAll(states);
		assertThat(result, allOf(notNullValue(), hasSize(2)));
	}
	
	static Stream<Arguments> saveAllParams() {
		return Stream.of(Arguments.of(List.of(
				new State("Yangon", Type.Region, "Lower", "Yangon", 8574930), 
				new State("Eainme", Type.Region, "Lower", "Eainme", 85947)
				)));
	}
}
