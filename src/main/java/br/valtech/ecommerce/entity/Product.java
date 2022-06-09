package br.valtech.ecommerce.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Product {


	@Id
	@NotNull(message = "The field sku cannot be empty!")
	private int sku;

	@NotNull(message = "The field brand cannot be empty!")
	private String brand;

	@NotNull(message = "The field description cannot be empty!")
	private String description;

	@NotNull(message = "The field price cannot be empty!")
	private double price;

	@NotNull(message = "The field size cannot be empty!")
	private double size;

	private double material;

//	@ManyToMany(mappedBy = "products")
//	List<Client> clients;
	
//	@ManyToMany(mappedBy = "products")
//	List<Client> clients;
	

	// GETTERS AND SETTERS

	public int getSku() {
		return sku;
	}

//	public List<Client> getClients() {
//		return clients;
//	}
//
//	public void setClients(List<Client> clients) {
//		this.clients = clients;
//	}



	public String getBrand() {
		return brand;
	}

	public String getDescription() {
		return description;
	}

	public double getPrice() {
		return price;
	}

	public double getSize() {
		return size;
	}

	public double getMaterial() {
		return material;
	}

	public void setSku(int sku) {
		this.sku = sku;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public void setMaterial(double material) {
		this.material = material;
	}

}
