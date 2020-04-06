package com.shop.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shop.product.entity.ProductDetail;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail,Long> {

	@Query(value="SELECT * FROM PRODUCT_DETAILS pd WHERE pd.price =:key",nativeQuery = true)
	public List<ProductDetail> findByPrice(@Param("key") String key);
	
	@Query(value="SELECT * FROM PRODUCT_DETAILS pd WHERE pd.color =:key",nativeQuery = true)
	public List<ProductDetail> findByColor(String key);
	
	@Query(value="SELECT * FROM PRODUCT_DETAILS pd WHERE pd.size =:key",nativeQuery = true)
	public List<ProductDetail> findBySize(String key);
	
	@Query(value="SELECT * FROM PRODUCT_DETAILS pd WHERE pd.brand =:key",nativeQuery = true)
	public List<ProductDetail> findByBrand(String key);
	
	@Query(value="SELECT * FROM PRODUCT_DETAILS pd WHERE pd.SKU_CODE =:key",nativeQuery = true)
	public List<ProductDetail> findBySKU(String key);
	
}
