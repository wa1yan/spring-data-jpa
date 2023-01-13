package com.waiyanhtet.spring.data.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.waiyanhtet.spring.data.jpa.config.JpaConfiguration;
import com.waiyanhtet.spring.data.jpa.entity.State;
import com.waiyanhtet.spring.data.jpa.entity.State.Type;
import com.waiyanhtet.spring.data.jpa.repo.StateRepo;

@TestMethodOrder(OrderAnnotation.class)
@SpringJUnitConfig(classes = JpaConfiguration.class)
public class CRUDTest {

	@Autowired
	private StateRepo stateRepo;

	@Order(1)
	@ParameterizedTest
	@CsvSource(delimiter = '\t', value = { 
			"Bago	Region	Lower	bago	495032	1",
			"Yangon	Region	Lower	Yangon	85948393	2",
			"Mandalay	State	Upper	Mandalay	9585438	3"
	})
	void save_test(String name, Type type, String region, String capital, int population, int id) {
		var state = new State(name, type, region, capital, population);
		var result = stateRepo.save(state);

		assertThat(result, hasProperty("id", is(id)));
	}
	
	@Order(2)
	@ParameterizedTest
	@CsvSource(delimiter = '\t', value = { 
			"1	Bago	Region	Lower	bago	495032",
	})
	void find_test(int id, String name, Type type, String region, String capital, int population ) {
		var result = stateRepo.findById(id).get();
		assertThat(result, allOf(
				hasProperty("id", is(id)),
				hasProperty("name", is(name)),
				hasProperty("type", is(type)),
				hasProperty("region", is(region)),
				hasProperty("capital", is(capital)),
				hasProperty("population", is(population))
				));
	}

	@Order(3)
	@ParameterizedTest
	@CsvSource(delimiter = '\t', value = { "1	Bago	Region	Lower	bago	595032" })
	void update_test(int id, String name, Type type, String region, String capital, int population) {
		var state = new State(id, name, type, region, capital, population);
		var result = stateRepo.save(state);

		assertThat(result, allOf(hasProperty("id", is(1)), hasProperty("population", is(595032))));

	}
	
	@Order(4)
	@ParameterizedTest
	@CsvSource(delimiter = '\t', value = { "2	Bago	Region	Lower	bago	595032	2" })
	void delete_by_entity_test(int id, String name, Type type, String region, String capital, int population, long remains) {
		var state = new State(id, name, type, region, capital, population);
		stateRepo.delete(state);
		assertEquals(remains, stateRepo.count());
	}
	
	@Order(5)
	@ParameterizedTest
	@CsvSource(delimiter = '\t', value = { "1	1" })
	void delete_by_id_test(int id, long remains) {
		stateRepo.deleteById(id);
		assertEquals(remains, stateRepo.count());
	}
	
	@Order(6)
	@Test
	void delete_all_test() {
		stateRepo.deleteAll();
		assertEquals(0, stateRepo.count());
	}

}
