package com.dxctraining.wishedlistmgt.wishedlist.util;

import org.springframework.stereotype.Component;

import com.dxctraining.wishedlistmgt.wishedlist.dto.WishedListDto;
import com.dxctraining.wishedlistmgt.wishedlist.entities.WishedList;

@Component
public class WishedListUtil {

	public WishedListDto wishedListDto(WishedList wishedList, String productId, String productName, Integer customerId, String customerName) {
		WishedListDto dto = new WishedListDto(wishedList.getId(), wishedList.getName());
		dto.setProductId(productId);
		dto.setProductName(productName);
		dto.setCustomerId(customerId);
		dto.setCustomerName(customerName);
		return dto;
	}
}
