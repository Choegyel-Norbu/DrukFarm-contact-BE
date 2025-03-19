package com.personalAssist.DrukFarm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.personalAssist.DrukFarm.Model.DP;

import jakarta.transaction.Transactional;

@Repository
public interface DpRepository extends JpaRepository<DP, Long> {

	@Query("SELECT d.fileName from DP d where d.user.id = :id")
	String fetchFileName(Long id);

	@Modifying
	@Query("UPDATE DP d SET fileName = :fileName, filePath = :filePath WHERE d.user.id = :id")
	void updateDP(@Param("id") Long id, @Param("fileName") String fileName, @Param("filePath") String filePath);

	@Modifying
	@Transactional
	@Query("DELETE FROM DP d WHERE d.user.id = :id")
	void deleteDP(@Param("id") Long id);

}
