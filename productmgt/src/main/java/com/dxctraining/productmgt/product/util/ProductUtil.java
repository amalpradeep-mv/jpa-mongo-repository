package com.dxctraining.productmgt.product.util;

import org.springframework.stereotype.Component;

import com.dxctraining.productmgt.product.dto.ProductDto;
import com.dxctraining.productmgt.product.entities.Product;

@Component
public class ProductUtil {

	public ProductDto productDto(Product product) {
		ProductDto dto = new ProductDto(product.getId(), product.getName());
		return dto;
	}
}
