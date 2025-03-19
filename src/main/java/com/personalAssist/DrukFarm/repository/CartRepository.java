package com.personalAssist.DrukFarm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personalAssist.DrukFarm.Model.Cart;
import com.personalAssist.DrukFarm.Model.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{
    Cart findByUser(User user);

}
