package com.shop.product.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import com.shop.product.model.ProductDetailDTO;
import com.shop.product.model.ProductRequestDTO;
 
public interface ProductService {
	
	public Map<String,List<ProductDetailDTO>> searchProduct(String key,String groupBy);
	
	@Transactional
	public String saveProduct(List<ProductRequestDTO> productRequestDTOs,String supplierId);
	
	public List<ProductDetailDTO> getBySKU(String sku);

}
