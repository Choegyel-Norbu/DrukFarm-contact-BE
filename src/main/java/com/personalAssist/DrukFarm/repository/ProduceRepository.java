package com.personalAssist.DrukFarm.repository;

import org.springframework.stereotype.Repository;

import com.personalAssist.DrukFarm.Model.Produce;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface ProduceRepository extends JpaRepository<Produce, Long>{
	
	@Query("SELECT p FROM Produce p WHERE p.category = :category")
	List<Produce> searchByCategory(String category);

}
