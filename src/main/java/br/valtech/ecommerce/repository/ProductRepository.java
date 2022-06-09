package br.valtech.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.valtech.ecommerce.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	public Product findBySku(int sku);
}
