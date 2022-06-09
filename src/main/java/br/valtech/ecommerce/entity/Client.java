package br.valtech.ecommerce.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Client {


	@Id
	@NotNull(message = "The field cpf cannot be empty!")
	@Size(min = 11, max = 11)
	private String cpf;

	@NotNull(message = "The field name cannot be empty!")
	@Size(min = 3, max = 100, message = "The field name must have between 3 -> 100 characters")
	private String name;

	@NotNull(message = "The field birth_Date cannot be empty!")
	@Column(name = "birth_date")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDate;

	@NotNull(message = "The field address cannot be empty!")
	private String address;

	@NotNull(message = "The field maritalStatus cannot be empty!")
	private String maritalStatus;

	private String commercialAddress;

//	@ManyToMany(cascade = CascadeType.ALL)
//	@JoinTable(name = "client_products", joinColumns = @JoinColumn(name = "client_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"))
//	List<Product> products;
	

//	@ManyToMany(cascade = CascadeType.ALL)
//	@JoinTable(name = "client_products", joinColumns = @JoinColumn(name = "client_id", referencedColumnName = "cpf"), inverseJoinColumns = @JoinColumn(name = "project_id", referencedColumnName = "sku"))
//	List<Product> products;
	
	

	// GETTERS AND SETTERS

	public String getCpf() {
		return cpf;
	}

//	public List<Product> getProducts() {
//		return products;
//	}
//
//	public void setProducts(List<Product> products) {
//		this.products = products;
//	}



	public String getName() {
		return name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public String getAddress() {
		return address;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public String getCommercialAddress() {
		return commercialAddress;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDataNascimento(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public void setCommercialAddress(String commercialAddress) {
		this.commercialAddress = commercialAddress;
	}

}
