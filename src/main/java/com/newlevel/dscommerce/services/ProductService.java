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
		copyDtoToEntity(productDTO,entity) ;
		entity = productRepository.save(entity);
		return new ProductDTO(entity);
				
	}
	
	@Transactional
	public ProductDTO update(Long id, ProductDTO productReceived) {
		
		Product product = productRepository.findById(id).get();
		copyDtoToEntity(productReceived,product);
		Product entitySaved =productRepository.save(product);
		return new ProductDTO(entitySaved);
	
	}

	@Transactional
	public void delete(Long id) {
		productRepository.deleteById(id);
	}
	
	
	
	
	
	private void copyDtoToEntity(ProductDTO productReceived, Product product) {	
		product.setName(productReceived.getName());
		product.setPrice(productReceived.getPrice());
		product.setDescription(productReceived.getDescription());
		product.setImgUrl(productReceived.getImgUrl());
	}
}
