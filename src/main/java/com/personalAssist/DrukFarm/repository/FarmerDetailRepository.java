package com.personalAssist.DrukFarm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personalAssist.DrukFarm.Model.BuyerDetail;
import com.personalAssist.DrukFarm.Model.FarmerDetail;

@Repository
public interface FarmerDetailRepository extends JpaRepository<FarmerDetail, Long>{

}
