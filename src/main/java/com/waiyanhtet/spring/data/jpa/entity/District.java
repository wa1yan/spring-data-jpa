package com.waiyanhtet.spring.data.jpa.entity;

import java.io.Serializable;

import com.waiyanhtet.spring.data.jpa.dto.StateDTO;

import jakarta.persistence.Column;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.SqlResultSetMapping;
import jakarta.persistence.Table;

@Entity
@Table(name = "district")
@SqlResultSetMapping(
		name = "resultToStateDTO",
		classes = {
				@ConstructorResult(targetClass = StateDTO.class, 
						columns = { 
								@ColumnResult(name = "name"),
								@ColumnResult(name = "population", type= Integer.class)
				})
				
		}
)
@NamedNativeQuery(
		name = "getStateNameAndPopulationById",
		resultSetMapping = "resultToStateDTO",
		query = """
				select s.name as name, s.population population
				from district d left join state s on d.state_id = s.id
				where d.id = ?1
				"""
)
public class District implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@ManyToOne
	private State state;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public District() {
		super();
	}

	public District(int id, String name, State state) {
		super();
		this.id = id;
		this.name = name;
		this.state = state;
	}

	public District(String name, State state) {
		super();
		this.name = name;
		this.state = state;
	}
	
	
}
