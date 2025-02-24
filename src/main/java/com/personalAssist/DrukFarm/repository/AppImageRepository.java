package com.personalAssist.DrukFarm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.personalAssist.DrukFarm.Model.AppImage;

@Repository
public interface AppImageRepository extends JpaRepository<AppImage, Long>{
	
	@Query("SELECT a FROM AppImage a WHERE a.produce.id = :id")
	List<AppImage> fetchImageName(Long id);
}
