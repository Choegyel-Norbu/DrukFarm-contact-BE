package com.personalAssist.DrukFarm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personalAssist.DrukFarm.Model.TransporterDetail;

@Repository
public interface TransporterDetailRepository extends JpaRepository<TransporterDetail, Long>{

}
