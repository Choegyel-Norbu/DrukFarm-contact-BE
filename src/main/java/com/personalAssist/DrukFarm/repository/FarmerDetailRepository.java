package com.personalAssist.DrukFarm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.personalAssist.DrukFarm.Model.BuyerDetail;
import com.personalAssist.DrukFarm.Model.FarmerDetail;

@Repository
public interface FarmerDetailRepository extends JpaRepository<FarmerDetail, Long>{
	
	@Query("SELECT f FROM FarmerDetail f where user.id= :id")
	FarmerDetail fetchFarmerDetailByUserID(Long id);
	
}
