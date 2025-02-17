package com.personalAssist.DrukFarm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personalAssist.DrukFarm.Model.BuyerDetail;

@Repository
public interface BuyerDetailRepository extends JpaRepository<BuyerDetail, Long>{

}
