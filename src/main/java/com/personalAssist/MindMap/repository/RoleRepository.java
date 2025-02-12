package com.personalAssist.MindMap.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personalAssist.MindMap.Model.Role;
import com.personalAssist.MindMap.util.RoleType;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
	Optional<Role> findByName(RoleType USER);
	
	  // Check if a role exists by its name (RoleType)
    boolean existsByName(RoleType name);

    // Delete a role by its name (RoleType)
    void deleteByName(RoleType name);
}
