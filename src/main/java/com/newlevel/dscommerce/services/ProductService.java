package com.newlevel.dscommerce.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	@Transactional(readOnly = true)
	public Page<ProductDTO> findAll(Pageable pageable){
		Page<Product> result = productRepository.findAll(pageable);
		return result.map(x -> new ProductDTO(x));
	}
	
	
	@Transactional
	public ProductDTO create(ProductDTO productDTO) {
			
		Product entity = new Product();
		entity.setName(productDTO.getName());
		entity.setPrice(productDTO.getPrice());
		entity.setDescription(productDTO.getDescription());
		entity.setImgUrl(productDTO.getImgUrl());
		
		entity = productRepository.save(entity);
		
		return new ProductDTO(entity);
				
	}
	
	

}
