package com.personalAssist.DrukFarm.repository;

import org.springframework.stereotype.Repository;

import com.personalAssist.DrukFarm.Model.Produce;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProduceRepository extends JpaRepository<Produce, Long>{

}
