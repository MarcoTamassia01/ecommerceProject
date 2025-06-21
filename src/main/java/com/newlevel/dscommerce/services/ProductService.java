package com.newlevel.dscommerce.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.newlevel.dscommerce.controllers.repositories.ProductRepository;
import com.newlevel.dscommerce.dto.ProductDTO;
import com.newlevel.dscommerce.entities.Product;
import com.newlevel.dscommerce.services.exceptions.DataBaseException;
import com.newlevel.dscommerce.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	
	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		Product entity = productRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Não foi possivel encontrar produto com id: "+ id));
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
		
		try {
			Product product = productRepository.getReferenceById(id);
			copyDtoToEntity(productReceived,product);
			Product entitySaved =productRepository.save(product);
			return new ProductDTO(entitySaved);
			
			
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Entidade não encontrada no banco de dados!");
		}
	
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		if(!productRepository.existsById(id)) {
			throw new ResourceNotFoundException("Não foi possivel excluir, produto com id: "+ id + " não encontrado!");
		}
		try {
			productRepository.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataBaseException("O sistema informa que a deleção não pode ser feita porque viola a integridade referencial dos dados");
		}
	}
	
	
	
	
	
	private void copyDtoToEntity(ProductDTO productReceived, Product product) {	
		product.setName(productReceived.getName());
		product.setPrice(productReceived.getPrice());
		product.setDescription(productReceived.getDescription());
		product.setImgUrl(productReceived.getImgUrl());
	}
}
