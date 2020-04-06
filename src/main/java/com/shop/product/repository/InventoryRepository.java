package com.shop.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.product.entity.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long>{

}
