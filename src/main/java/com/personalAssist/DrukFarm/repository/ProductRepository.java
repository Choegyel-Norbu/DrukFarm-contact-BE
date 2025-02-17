package com.personalAssist.DrukFarm.repository;

import org.springframework.stereotype.Repository;

import com.personalAssist.DrukFarm.Model.Product;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

}
