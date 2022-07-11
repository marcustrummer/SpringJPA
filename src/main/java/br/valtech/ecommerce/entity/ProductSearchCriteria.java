package br.valtech.ecommerce.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductSearchCriteria {

	private String brand;
	
	private String description;
	
	private Integer priceMin;
	
	private Integer priceMax;
	
	private List<String> materials;
	
	private List<String> sizes;

	public ProductSearchCriteria(String brand, String description, Integer priceMin, Integer priceMax,
			List<String> materials, List<String> sizes) {
		super();
		this.brand = brand;
		this.description = description;
		this.priceMin = priceMin;
		this.priceMax = priceMax;
		this.materials = materials;
		this.sizes = sizes;
	}


	
	
}
