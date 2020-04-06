package com.shop.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.product.entity.SupplierInfo;

@Repository
public interface SupplierRepository extends JpaRepository<SupplierInfo,Long> {
	
	
	public SupplierInfo findByUniqueId(String uniqueId);

}
