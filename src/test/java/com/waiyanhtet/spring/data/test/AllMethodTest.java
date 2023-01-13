package com.waiyanhtet.spring.data.test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.waiyanhtet.spring.data.jpa.config.JpaConfiguration;
import com.waiyanhtet.spring.data.jpa.entity.State;
import com.waiyanhtet.spring.data.jpa.entity.State.Type;
import com.waiyanhtet.spring.data.jpa.repo.StateRepo;

@TestMethodOrder(OrderAnnotation.class)
@SpringJUnitConfig(classes = JpaConfiguration.class)
public class AllMethodTest {

	@Autowired
	private StateRepo stateRepo;

	@Order(1)
	@ParameterizedTest
	@MethodSource("saveAllParams")
	void save_all_test(List<State> states) {
		var result = stateRepo.saveAll(states);
		assertThat(result, allOf(notNullValue(), hasSize(2)));
	}
	
	@Order(2)
	@Test
	void find_all_test() {
		var result = stateRepo.findAll();
		assertThat(result, allOf(notNullValue(), hasSize(2)));
	}
	
	@Order(3)
	@ParameterizedTest
	@MethodSource("findAllParams")
	void find_all_test(List<Integer> ids) {
		var result = stateRepo.findAllById(ids);
		assertThat(result, allOf(notNullValue(), hasSize(2)));
	}
	
	@Order(4)
	@ParameterizedTest
	@MethodSource("deleteAllParams")
	void delete_all_test(List<State> states) {
		stateRepo.deleteAll(states);
		assertEquals(0, stateRepo.count());
	}

	static Stream<Arguments> saveAllParams() {
		return Stream.of(Arguments.of(List.of(
				new State("Yangon", Type.Region, "Lower", "Yangon", 8574930), 
				new State("Eainme", Type.Region, "Lower", "Eainme", 85947)
				)));
	}
	
	static Stream<Arguments> deleteAllParams() {
		return Stream.of(Arguments.of(List.of(
				new State(1, "Yangon", Type.Region, "Lower", "Yangon", 8574930), 
				new State(2, "Eainme", Type.Region, "Lower", "Eainme", 85947)
				)));
	}
	
	static Stream<Arguments> findAllParams() {
		return Stream.of(Arguments.of(List.of(1,2)));
	}
}
