package br.valtech.ecommerce.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product {


	@Id
	@NotNull(message = "The field sku cannot be empty!")
	private int sku;

	@NotNull(message = "The field brand cannot be empty!")
	private String brand;

	@NotNull(message = "The field description cannot be empty!")
	private String description;

	@NotNull(message = "The field price cannot be empty!")
	private BigDecimal price;

	@NotNull(message = "The field size cannot be empty!")
	@Size(min = 1, max = 1, message = "size meust be  [P] /  [M] / [G]")
	private String size;

	private String material;

//	@ManyToMany(mappedBy = "products")
//	List<Client> clients;
	
//	@ManyToMany(mappedBy = "products")
//	List<Client> clients;
	



}
