package com.personalAssist.MindMap.repository;

import org.springframework.stereotype.Repository;

import com.personalAssist.MindMap.Model.Product;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

}
