package com.waiyanhtet.spring.data.jpa.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class State implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, unique = true)
	private String name;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Type type;

	private String region;

	private String capital;

	private int population;

	@OneToMany(mappedBy = "state")
	private List<District> districts;

	public State() {
		super();
	}

	public State(String name, Type type, String region, String capital, int population) {
		super();
		this.name = name;
		this.type = type;
		this.region = region;
		this.capital = capital;
		this.population = population;
	}

	public State(int id, String name, Type type, String region, String capital, int population) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.region = region;
		this.capital = capital;
		this.population = population;
	}

	public enum Type {

		State("State"), Region("Region"), Union("Union Teritory");

		private String value;

		private Type(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	@Override
	public String toString() {
		return "State [id=" + id + ", name=" + name + ", type=" + type + ", region=" + region + ", capital=" + capital
				+ ", population=" + population + ", districts=" + districts + "]";
	}

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

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public List<District> getDistricts() {
		return districts;
	}

	public void setDistricts(List<District> districts) {
		this.districts = districts;
	}

}
