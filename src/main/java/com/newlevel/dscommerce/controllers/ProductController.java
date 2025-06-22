package com.newlevel.dscommerce.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.newlevel.dscommerce.dto.ProductDTO;
import com.newlevel.dscommerce.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	
	@Autowired
	private ProductService productService;
	
	
	@GetMapping
	public  ResponseEntity< Page<ProductDTO>>findAll(Pageable pageable){
		Page<ProductDTO> lista = productService.findAll(pageable);
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{id}")
	public  ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
		ProductDTO result =  productService.findById(id);
		return ResponseEntity.ok(result);
	}
	
	@PostMapping
	public  ResponseEntity<ProductDTO>create(@Valid @RequestBody ProductDTO productDTO) { 
		ProductDTO product = productService.create(productDTO);
		URI uri=  ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(product.getId()).toUri();
		return ResponseEntity.created(uri).body(product); 
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<ProductDTO>update(@PathVariable Long id,@Valid  @RequestBody ProductDTO productDTO){
		ProductDTO updateProduct = productService.update(id, productDTO);
		return ResponseEntity.ok(updateProduct);
	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void>delete(@PathVariable Long id){
		productService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
	
	
	
	
	
	
	
}