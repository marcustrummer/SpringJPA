package br.valtech.ecommerce.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import br.valtech.ecommerce.entity.Product;
import br.valtech.ecommerce.entity.ProductPage;
import br.valtech.ecommerce.entity.ProductSearchCriteria;
import lombok.Getter;
import lombok.Setter;

@Repository
@Getter
@Setter
public class ProductCriteriaRepository {

	
	private final EntityManager entityManager;


	private final CriteriaBuilder criteriaBuilder;

	public ProductCriteriaRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
		this.criteriaBuilder = entityManager.getCriteriaBuilder();
	}

	public Page<Product> findAllWithFilter(ProductPage productPage, ProductSearchCriteria productSearchCriteria) {

		CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);

		Root<Product> productRoot = criteriaQuery.from(Product.class);

		Predicate predicate = getPredicate(productSearchCriteria, productRoot);

		criteriaQuery.where(predicate);

//		setOrder(productPage, criteriaQuery, productRoot);

		TypedQuery<Product> typedQuery = entityManager.createQuery(criteriaQuery);
		typedQuery.setFirstResult(productPage.getPageNumber() * productPage.getPageSize());
		typedQuery.setMaxResults(productPage.getPageSize());

		Pageable pageable = getPageable(productPage);

		long productCount = getProductCount(predicate);
		
		

		return new PageImpl<>(typedQuery.getResultList(), pageable, productCount);
	}

	private Predicate getPredicate(ProductSearchCriteria productSearchCriteria, Root<Product> productRoot) {

		List<Predicate> predicates = new ArrayList<>();

		if (Objects.nonNull(productSearchCriteria.getBrand())) {
			predicates
					.add(criteriaBuilder.like(productRoot.get("brand"), "%" + productSearchCriteria.getBrand() + "%"));
		}

		if (Objects.nonNull(productSearchCriteria.getBrand())) {
			predicates.add( criteriaBuilder.like(productRoot.get("material"),
					"%" + productSearchCriteria.getMaterials() + "%"));
		}

		return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
	}

//	private void setOrder(ProductPage productPage, CriteriaQuery<Product> criteriaQuery, Root<Product> productRoot) {
//		// TODO Auto-generated method stub
//
//		if (productPage.getSortDirection().equals(Sort.Direction.ASC)) {
//			criteriaQuery.orderBy(criteriaBuilder.asc(productRoot.get(productPage.getSortBy())));
//
//		} else {
//			criteriaQuery.orderBy(criteriaBuilder.desc(productRoot.get(productPage.getSortBy())));
//		}
//	}

	private Pageable getPageable(ProductPage productPage) {

		return PageRequest.of(productPage.getPageNumber(), productPage.getPageSize());
	}

	private long getProductCount(Predicate predicate) {
		CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
		Root<Product> countRoot = countQuery.from(Product.class);

		countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
		return entityManager.createQuery(countQuery).getSingleResult();
	}
}
