package br.valtech.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.valtech.ecommerce.entity.Product;
import br.valtech.ecommerce.entity.ProductPage;
import br.valtech.ecommerce.entity.ProductSearchCriteria;
import br.valtech.ecommerce.repository.ProductCriteriaRepository;
import br.valtech.ecommerce.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private final ProductRepository repository;
	@Autowired
	private final ProductCriteriaRepository productCriteriaRepository;
	
	
	public ProductService(ProductRepository productRepository, ProductCriteriaRepository productCriteriaRepository) {
		this.productCriteriaRepository = productCriteriaRepository;
		this.repository = productRepository;
	}
	
	
	public Page<Product> getProducts(ProductPage productPage, ProductSearchCriteria productSearchCriteria){
		return productCriteriaRepository.findAllWithFilter(productPage, productSearchCriteria);
	}
	
	

	public List<Product> findAllProducts() {
		return repository.findAll();
	}

	public Optional<Product> findProducttBySku(int sku) {
		return repository.findBySku(sku);
	}



	public Page<Product> findProductBySize(String size, Pageable pageable) {
		return repository.findBySize(size, pageable);
	}

	public Page<Product> findProductBySizeOrSize(String size, String size2, Pageable pageable) {
		return repository.findBySizeOrSize(size, size2, pageable);
	}

	public Page<Product> findProductByMaterial(String material, Pageable pageable) {
		return repository.findByMaterial(material, pageable);
	}

	public Page<Product> findProductByMaterialOrMaterial(String material, String material2, Pageable pageable) {
		return repository.findByMaterialOrMaterial(material, material2, pageable);
	}

	public Page<Product> findProductByDescription(String description, Pageable pageable) {
		return repository.findByDescription(description, pageable);
	}

	public Page<Product> findByBrand(String brand, Pageable pageable) {
		return repository.findByBrand(brand, pageable);
	}

	public Optional<Product> createProduct(Product product) {
		if (repository.findBySku(product.getSku()).isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product already exists!", null);
		}
		return Optional.of(repository.save(product));
	}

	public Optional<Product> updateProduct(Product product) {
		if (repository.findBySku(product.getSku()).isPresent()) {
			return Optional.of(repository.save(product));
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found!", null);
		}
	}

	public void deleteProductBySku(int sku) {
		repository.deleteBySku(sku);
	}

}
