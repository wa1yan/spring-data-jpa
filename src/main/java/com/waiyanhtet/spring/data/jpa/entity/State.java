package com.waiyanhtet.spring.data.jpa.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
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
				+ ", population=" + population + "]";
	}

}
