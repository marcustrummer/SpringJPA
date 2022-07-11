package br.valtech.ecommerce.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.valtech.ecommerce.entity.Product;
import br.valtech.ecommerce.entity.ProductPage;
import br.valtech.ecommerce.entity.ProductSearchCriteria;
import br.valtech.ecommerce.service.ProductService;
import net.bytebuddy.implementation.bytecode.constant.DefaultValue;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

	@Autowired
	private ProductService service;

	@GetMapping("/search")
	public ResponseEntity<Page<Product>> getProducts(ProductPage pb, ProductSearchCriteria psc){
		return new ResponseEntity<>(service.getProducts(pb, psc),HttpStatus.OK);
	}
	
	@GetMapping("/searchee")
	public ResponseEntity<Page<Product>> getAllProductes(
			@RequestParam(value = "pageNumber", defaultValue = "0")Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "0")Integer PageSize,
			@RequestParam(value = "brand", required = false)String brand,
			@RequestParam(value = "description", required = false)String description,
			@RequestParam(value = "size", required = false)List<String> sizes,
			@RequestParam(value = "size", required = false)List<String> materials
			){
		
		ProductPage pp = new ProductPage();
		ProductSearchCriteria psc = new ProductSearchCriteria(brand, description, pageNumber, PageSize, materials, sizes);
		
		Page<Product> pageProduct = service.getProducts(pp, psc);
		
		if(pageProduct.isEmpty() || pageProduct == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
		}
		return new ResponseEntity<> ((pageProduct), HttpStatus.OK);
	}
	
	
	
	
	
	@GetMapping("/all")
	public ResponseEntity<List<Product>> getAllProducts() {
		return ResponseEntity.ok(service.findAllProducts());
	}

	@GetMapping("/sku/{sku}")
	public ResponseEntity<Product> getBySku(@PathVariable int sku) {
		return service.findProducttBySku(sku).map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@GetMapping("/brand/{brand}")
	public ResponseEntity<Map<String, Object>> findByBrande(@PathVariable String brand,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
		try {
			List<Product> products = new ArrayList<Product>();
			Pageable paging = PageRequest.of(page, size);

			Page<Product> pageTuts = service.findByBrand(brand, paging);
			products = pageTuts.getContent();

			Map<String, Object> response = new HashMap<>();
			response.put("products", products);
			response.put("currentPage", pageTuts.getNumber());
			response.put("totalItems", pageTuts.getTotalElements());
			response.put("totalPages", pageTuts.getTotalPages());

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/size/{size}")
	public ResponseEntity<Map<String, Object>> getBySize(@PathVariable String size,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size1) {
		try {
			List<Product> products = new ArrayList<Product>();
			Pageable paging = PageRequest.of(page, size1);

			Page<Product> pageTuts = service.findProductBySize(size, paging);
			products = pageTuts.getContent();

			Map<String, Object> response = new HashMap<>();
			response.put("products", products);
			response.put("currentPage", pageTuts.getNumber());
			response.put("totalItems", pageTuts.getTotalElements());
			response.put("totalPages", pageTuts.getTotalPages());

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/size/{size}/orSize/{size2}")
	public ResponseEntity<Map<String, Object>> getBySizeOrSize(@PathVariable String size, @PathVariable String size2,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size0) {
		try {
			List<Product> products = new ArrayList<Product>();
			Pageable paging = PageRequest.of(page, size0);

			Page<Product> pageTuts = service.findProductBySizeOrSize(size, size2, paging);
			products = pageTuts.getContent();

			Map<String, Object> response = new HashMap<>();
			response.put("products", products);
			response.put("currentPage", pageTuts.getNumber());
			response.put("totalItems", pageTuts.getTotalElements());
			response.put("totalPages", pageTuts.getTotalPages());

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/material/{material}")
	public ResponseEntity<Map<String, Object>> getByMaterial(@PathVariable String material,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
		try {

			List<Product> products = new ArrayList<Product>();
			Pageable paging = PageRequest.of(page, size);

			Page<Product> pageTuts = service.findProductByMaterial(material, paging);
			products = pageTuts.getContent();

			Map<String, Object> response = new HashMap<>();
			response.put("products", products);
			response.put("currentPage", pageTuts.getNumber());
			response.put("totalItems", pageTuts.getTotalElements());
			response.put("totalPages", pageTuts.getTotalPages());

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/material/{material}/orMaterial/{material2}")
	public ResponseEntity<Map<String, Object>> getByMaterialOrMaterial(@PathVariable String material,
			@PathVariable String material2, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "3") int size) {
		try {

			List<Product> products = new ArrayList<Product>();
			Pageable paging = PageRequest.of(page, size);

			Page<Product> pageTuts = service.findProductByMaterialOrMaterial(material, material2, paging);
			products = pageTuts.getContent();

			Map<String, Object> response = new HashMap<>();
			response.put("products", products);
			response.put("currentPage", pageTuts.getNumber());
			response.put("totalItems", pageTuts.getTotalElements());
			response.put("totalPages", pageTuts.getTotalPages());

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/description/{description}")
	public ResponseEntity<Map<String, Object>> getBydescription(@PathVariable String description,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
		try {

			List<Product> products = new ArrayList<Product>();
			Pageable paging = PageRequest.of(page, size);
			Page<Product> pageTuts = service.findProductByDescription(description, paging);
			products = pageTuts.getContent();

			Map<String, Object> response = new HashMap<>();
			response.put("products", products);
			response.put("currentPage", pageTuts.getNumber());
			response.put("totalItems", pageTuts.getTotalElements());
			response.put("totalPages", pageTuts.getTotalPages());

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/registration")
	public ResponseEntity<Product> postProduct(@RequestBody Product product) {
		return service.createProduct(product).map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

	@PutMapping("/update")
	public ResponseEntity<Product> putProduct(@RequestBody Product product) {
		return service.updateProduct(product).map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

	@Transactional
	@DeleteMapping("/{sku}")
	public void deletePostagem(@PathVariable int sku) {
		service.deleteProductBySku(sku);
	}
}
