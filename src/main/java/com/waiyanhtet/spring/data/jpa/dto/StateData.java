package com.waiyanhtet.spring.data.jpa.dto;

import com.waiyanhtet.spring.data.jpa.entity.State.Type;

public interface StateData {

	int getId();
	String getName();
	Type getType();
	String getRegion();
	String getCapital();
	String getPopulation();
	String getDistrictsName();
}
