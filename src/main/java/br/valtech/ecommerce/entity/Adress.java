package br.valtech.ecommerce.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Adress {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull(message = "The field state cannot be empty!")
	private String state;

	@NotNull(message = "The field city cannot be empty!")
	private String city;

	@NotNull(message = "The field street cannot be empty!")
	private String street;

	@NotNull(message = "The field streetNumber cannot be empty!")
	private int stretNumber;
	
	private String reference;
	
    @OneToOne(mappedBy = "address")
    @JsonIgnoreProperties("client")
    private Client client;

	
	
}
