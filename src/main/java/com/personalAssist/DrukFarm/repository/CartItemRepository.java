package com.personalAssist.DrukFarm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.personalAssist.DrukFarm.Model.CartItem;
import com.personalAssist.DrukFarm.dto.CartItemDTO;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

	@Query(value = "SELECT COUNT(*) \n" + "	FROM cart_items ci \n" + "	INNER JOIN cart c ON c.id = ci.cart_id \n"
			+ "	WHERE c.user_id = :userId", nativeQuery = true)
	int getCartCount(@Param("userId") Long userId);

	@Query("SELECT new com.personalAssist.DrukFarm.dto.CartItemDTO(p, ci.id) " + "FROM Produce p "
			+ "LEFT JOIN CartItem ci ON ci.produce.id = p.id " + "LEFT JOIN Cart c ON c.id = ci.cart.id "
			+ "WHERE c.user.id = :userId")
	List<CartItemDTO> getCartItem(@Param("userId") Long userId);

}
