package br.valtech.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductPage {
	
	private int pageNumber = 0;
	
	private int pageSize = 3;
	
	private String sortBy = "brand";

}
