package com.personalAssist.DrukFarm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.personalAssist.DrukFarm.Model.Role;
import com.personalAssist.DrukFarm.util.RoleType;

import jakarta.transaction.Transactional;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByName(RoleType useRole);

	boolean existsByName(RoleType name);

	void deleteByName(RoleType name);

	@Query(value = "SELECT r.* FROM roles r " + "JOIN user_roles ur ON r.id = ur.role_id "
			+ "WHERE ur.user_id = :userId", nativeQuery = true)
	List<Role> findRolesByUserId(@Param("userId") Long userId);
	
	@Transactional
	@Modifying
    @Query(value = "DELETE FROM user_roles WHERE user_id = :userId", nativeQuery = true)
	void removeRole(@Param("userId") Long userId);

}
