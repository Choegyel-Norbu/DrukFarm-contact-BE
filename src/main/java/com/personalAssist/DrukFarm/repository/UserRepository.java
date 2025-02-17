package com.personalAssist.DrukFarm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personalAssist.DrukFarm.Model.User;
import com.personalAssist.DrukFarm.util.RoleType;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    User findByEmail(String email);
    
    boolean existsByEmail(String email);
    
    User findByPhone(String phone);
   

}
