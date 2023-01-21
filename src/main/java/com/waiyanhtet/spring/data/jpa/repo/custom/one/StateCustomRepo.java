package com.waiyanhtet.spring.data.jpa.repo.custom.one;

import java.util.List;

import com.waiyanhtet.spring.data.jpa.entity.State;
import com.waiyanhtet.spring.data.jpa.entity.State.Type;

public interface StateCustomRepo {

	List<State> search(String name, Type type, String region);
}
