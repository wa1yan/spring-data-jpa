package com.waiyanhtet.spring.data.jpa.repo.custom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.waiyanhtet.spring.data.jpa.entity.State;
import com.waiyanhtet.spring.data.jpa.repo.custom.one.StateCustomRepo;

@Repository
public interface StateRepoOne extends JpaRepository<State, Integer>, StateCustomRepo {

}
