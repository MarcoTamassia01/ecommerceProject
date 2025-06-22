package com.newlevel.dscommerce.dto;

import com.newlevel.dscommerce.entities.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProductDTO {
	
	private Long id;
	
	@Size(min = 3,max = 80, message = "Nome precisa conter entre 3 a 80 caracteres")
	@NotBlank(message = "Campo requirido" )
	private String name;
	
	@Size(min = 10,message = "O campo descrição deve conter no minimo 10 caracteres")
	@NotBlank(message = "Campo requirido" )
	private String description; 
	
	@Positive(message = "O preço deve ser positivo")
	private Double price;
	private String imgUrl;
	
	public ProductDTO() {
		
	}

	public ProductDTO(Product entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.description = entity.getDescription();
		this.price = entity.getPrice();
		this.imgUrl = entity.getImgUrl();
	}


	public Long getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public String getDescription() {
		return description;
	}


	public Double getPrice() {
		return price;
	}


	public String getImgUrl() {
		return imgUrl;
	}
	 

}
