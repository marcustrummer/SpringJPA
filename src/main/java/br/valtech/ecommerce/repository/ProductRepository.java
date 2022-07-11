package br.valtech.ecommerce.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.valtech.ecommerce.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

	public Optional<Product> findBySku(int sku);

	public List<Product> findByBrand(String brand);
	
	public Page<Product> findBySize(String size, Pageable pageable);
	
	public Page<Product> findBySizeOrSize(String size, String size2, Pageable pageable);
	
	public Page<Product> findByMaterial(String material, Pageable pageable);
	
	public Page<Product> findByDescription(String description, Pageable pageable);
	
	public Page<Product> findByMaterialOrMaterial(String material, String material2, Pageable pageable);
	
	public Page<Product> findByPriceBetween(BigDecimal start, BigDecimal end, Pageable pageable);
	
	public Page<Product> findByBrand(String brand, Pageable pageable);
	//Page<Warning> findByTitleContaining(String title, Pageable pageable);

	public void deleteBySku(int sku);

}
