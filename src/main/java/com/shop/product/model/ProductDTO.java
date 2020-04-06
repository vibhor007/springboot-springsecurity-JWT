package com.shop.product.model;

import java.util.List;

public class ProductDTO {
	private Long id;
	private String productType;
	private String supplierId;
	private List<ProductDetailDTO> productDetails;
	private InventoryDTO inventoryDTO;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	public List<ProductDetailDTO> getProductDetails() {
		return productDetails;
	}
	public void setProductDetails(List<ProductDetailDTO> productDetails) {
		this.productDetails = productDetails;
	}
	public InventoryDTO getInventoryDTO() {
		return inventoryDTO;
	}
	public void setInventoryDTO(InventoryDTO inventoryDTO) {
		this.inventoryDTO = inventoryDTO;
	}
	
	
}
