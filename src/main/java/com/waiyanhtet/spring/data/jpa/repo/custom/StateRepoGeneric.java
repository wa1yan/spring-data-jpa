package com.waiyanhtet.spring.data.jpa.repo.custom;

import org.springframework.stereotype.Repository;

import com.waiyanhtet.spring.data.jpa.entity.State;
import com.waiyanhtet.spring.data.jpa.repo.custom.generic.BaseRepository;

@Repository
public interface StateRepoGeneric extends BaseRepository<State, Integer> {

}
