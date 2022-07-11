package br.valtech.ecommerce.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;



@Entity
@Data
@Getter
@Setter
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
    @OneToOne(cascade=CascadeType.ALL)
	@JsonIgnoreProperties("client")
    @JoinColumn(name = "address_id")
	private Adress address;

	@NotNull(message = "The field maritalStatus cannot be empty!")
	private String maritalStatus;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "secondary_address_id")
    @JsonIgnoreProperties("client")
	private Adress commercialAddress;
    
    private boolean clientStatus = true;
    
    
    public void setClientStatus(boolean status) {
    	this.clientStatus = status;
    }
  

//	@ManyToMany(cascade = CascadeType.ALL)
//	@JoinTable(name = "client_products", joinColumns = @JoinColumn(name = "client_id", referencedColumnName = "cpf"), inverseJoinColumns = @JoinColumn(name = "project_id", referencedColumnName = "sku"))
//	List<Product> products;


	
	
}
