package com.personalAssist.MindMap.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.personalAssist.MindMap.Model.UserServiceModal;

@Repository
public interface UserServiceRepository extends JpaRepository<UserServiceModal, Long> {

	Optional<UserServiceModal> findByServiceName(String name);

	@Query("SELECT s FROM UserServiceModal s JOIN s.users u WHERE u.id = :userId")
	List<UserServiceModal> findServicesByUserId(@Param("userId") Long userId);

}
