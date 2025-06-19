package com.newlevel.dscommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.newlevel.dscommerce.controllers.repositories.ProductRepository;
import com.newlevel.dscommerce.dto.ProductDTO;
import com.newlevel.dscommerce.entities.Product;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	
	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		Product entity = productRepository.findById(id).get();
		return new ProductDTO(entity);
	}
	
	

}
