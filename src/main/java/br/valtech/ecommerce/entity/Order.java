package br.valtech.ecommerce.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

//@Entity
//@Data
//public class Order{
////	
////	@Id
////	private long id;
////	
////	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
////	private Date orderDate;
////	
////	@ManyToOne
////	@JoinColumn(name="client_id")
////	private Client client;
////	
////	private List<Product> cart;
////	
////	private BigDecimal shipping;
////	
//	
//}
