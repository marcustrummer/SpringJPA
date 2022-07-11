package br.valtech.ecommerce.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {

	PENDING(1, "Pending"),
	COMPLETED(2, "Completed");
	
	private int cod;
	private String description;
	
	private OrderStatus(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}


	//recebe o cod e retorna um obj cliente já instanciado
	//Static = deve ser possível executar essa operação mesmo sem istanciar objs
	public static OrderStatus toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		for (OrderStatus x : OrderStatus.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido!! Cód: " + cod + "Não existe!!");
	}
	
}
