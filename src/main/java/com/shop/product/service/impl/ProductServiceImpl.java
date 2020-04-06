package com.shop.product.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.product.entity.Inventory;
import com.shop.product.entity.Product;
import com.shop.product.entity.ProductDetail;
import com.shop.product.entity.SupplierInfo;
import com.shop.product.exception.DataNotFoundException;
import com.shop.product.model.ProductDetailDTO;
import com.shop.product.model.ProductRequestDTO;
import com.shop.product.repository.ProductDetailRepository;
import com.shop.product.repository.ProductRepository;
import com.shop.product.repository.SupplierRepository;
import com.shop.product.service.ProductService;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private SupplierRepository supplierRepository;

	@Autowired
	private ProductDetailRepository productDetailRepository;

	@Override
	public Map<String, List<ProductDetailDTO>> searchProduct(String key, String groupBy) {
		// TODO Auto-generated method stub
		List<ProductDetail> productDetails = null;
		List<ProductDetailDTO> detailDTOs = null;
		Map<String, List<ProductDetailDTO>> result = null;
		 if (groupBy.equals("brand")) {
			productDetails = productDetailRepository.findByBrand(key);
			detailDTOs = new ArrayList<>();
			copyProperties(productDetails, detailDTOs);
			result = detailDTOs.stream().collect(Collectors.groupingBy(ProductDetailDTO::getBrand));
		} else if (groupBy.equals("color")) {
			productDetails = productDetailRepository.findByColor(key);
			detailDTOs = new ArrayList<>();
			copyProperties(productDetails, detailDTOs);
			result = detailDTOs.stream().collect(Collectors.groupingBy(ProductDetailDTO::getColor));
		} else if (groupBy.equals("size")) {
			productDetails = productDetailRepository.findBySize(key);
			detailDTOs = new ArrayList<>();
			copyProperties(productDetails, detailDTOs);
			result = detailDTOs.stream().collect(Collectors.groupingBy(ProductDetailDTO::getSize));
		}

		return result;
	}

	@Override
	public String saveProduct(List<ProductRequestDTO> productRequestDTOs, String supplierUniqueId) {
		// TODO Auto-generated method stub
		SupplierInfo supplierInfo = supplierRepository.findByUniqueId(supplierUniqueId);
		if (supplierInfo == null) {
			throw new DataNotFoundException("Supplier is not valid");
		}
		Map<String, List<ProductRequestDTO>> groupByType = new HashMap<>();
		List<ProductRequestDTO> filterList = null;
		for (ProductRequestDTO productRequestDTO : productRequestDTOs) {
			if (groupByType.containsKey(productRequestDTO.getProductType())) {
				filterList = groupByType.get(productRequestDTO.getProductType());
				filterList.add(productRequestDTO);
				groupByType.put(productRequestDTO.getProductType(), filterList);
			} else {
				filterList = new ArrayList<>();
				filterList.add(productRequestDTO);
				groupByType.put(productRequestDTO.getProductType(), filterList);
			}
		}
		if (groupByType != null) {
			for (Map.Entry<String, List<ProductRequestDTO>> map : groupByType.entrySet()) {
				Product prd = new Product();
				prd.setProductType(map.getKey());
				prd.setSupplierId(supplierUniqueId);
				List<ProductDetail> prdDtls = new ArrayList<>();
				for(ProductRequestDTO detailDTO : map.getValue()) {
					ProductDetail detail = new ProductDetail();
					detail.setBrand(detailDTO.getBrand());
					detail.setColor(detailDTO.getColor());
					detail.setCurrency(detailDTO.getCurrency());
					detail.setPrice(detailDTO.getPrice());
					detail.setSize(detailDTO.getSize());
					detail.setSku(detailDTO.getSku());
					prdDtls.add(detail);
				}
				prd.setProductDetails(prdDtls);
				// Inventory details
				Inventory inventory = new Inventory();
				inventory.setTotalCount(map.getValue().size());
				inventory.setAvailable(map.getValue().size());
				inventory.setSoldCount(0);
				inventory.setProduct(prd);
				prd.setInventory(inventory);
				productRepository.save(prd);
			}
		}

		return "Added Succesfully";
	}

	@Override
	public List<ProductDetailDTO> getBySKU(String sku) {
		List<ProductDetail> productDetails = productDetailRepository.findBySKU(sku);
		List<ProductDetailDTO> productDetailDTOs = new ArrayList<>();
		copyProperties(productDetails, productDetailDTOs);
		return productDetailDTOs;
	}

	
	private List<ProductDetailDTO> copyProperties(List<ProductDetail> productDetails, List<ProductDetailDTO> detailDTOs){
		
		for(ProductDetail productDetail:productDetails){
		ProductDetailDTO detailDTO = new ProductDetailDTO();
		detailDTO.setBrand(productDetail.getBrand());
		detailDTO.setColor(productDetail.getColor());
		detailDTO.setCurrency(productDetail.getCurrency());
		detailDTO.setPrice(productDetail.getPrice());
		detailDTO.setSize(productDetail.getSize());
		detailDTO.setSku(productDetail.getSku());
		detailDTOs.add(detailDTO);
		}
		return detailDTOs;
	}
}
