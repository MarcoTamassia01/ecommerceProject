package com.newlevel.dscommerce.controllers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.newlevel.dscommerce.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
