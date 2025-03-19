package com.personalAssist.DrukFarm.repository;

import org.springframework.stereotype.Repository;

import com.personalAssist.DrukFarm.Model.Produce;
import com.personalAssist.DrukFarm.dto.ProduceDetailWithFarmerDetailDTO;
import com.personalAssist.DrukFarm.dto.ProducePaginatedDTO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface ProduceRepository extends JpaRepository<Produce, Long> {

	@Query("SELECT p FROM Produce p WHERE p.category = :category")
	List<Produce> searchByCategory(String category);

//	@Query("SELECT p FROM Produce p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
	@Query("SELECT new com.personalAssist.DrukFarm.dto.ProducePaginatedDTO(p, u.id) FROM Produce p LEFT JOIN p.farmerDetail f LEFT JOIN f.user u "
			+ " WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
	Page<ProducePaginatedDTO> searchWithName(@Param("keyword") String keyword, Pageable pageable);

	@Query("SELECT DISTINCT p FROM Produce p LEFT JOIN FETCH p.images")
	Page<Produce> fetchPaginatedProduces(Pageable pageable);
	
	@Query("SELECT new com.personalAssist.DrukFarm.dto.ProducePaginatedDTO(p, u.id) FROM Produce p LEFT JOIN p.farmerDetail f LEFT JOIN f.user u")
	Page<ProducePaginatedDTO> fetchPaginatedProduce(Pageable pageable);

	@Query("SELECT new com.personalAssist.DrukFarm.dto.ProduceDetailWithFarmerDetailDTO(f.farmName, f.farmSize, u.userName, p.quantityAvailable, p.delivery, p.harvestDate)" +
	"FROM FarmerDetail f LEFT JOIN f.availableProduce p LEFT JOIN f.user u WHERE p.id = :prodId ")
	ProduceDetailWithFarmerDetailDTO getProductFarmerDetail(@Param("prodId") Long prodId);
	
	@Query("SELECT p FROM Produce p LEFT JOIN p.farmerDetail f LEFT JOIN f.user u WHERE u.id =:userId")
	List<Produce> fetchProduceByFarmerId(@Param("userId") Long userId);
	
	@Query(value = "SELECT COUNT(*) from produce_table pt inner Join famer_table ft on pt.farmer_id = ft.id\n"
			+ "where ft.user_id = :userId", nativeQuery = true)
	int getProduceByFarmerCount(@Param("userId") Long userId);
}
