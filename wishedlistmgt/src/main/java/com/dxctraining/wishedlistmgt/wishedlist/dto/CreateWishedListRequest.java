package com.dxctraining.wishedlistmgt.wishedlist.dto;

public class CreateWishedListRequest {

	private String name;

	private String productId;
	
	private Integer customerId;
	

	public CreateWishedListRequest() {
	}

	public CreateWishedListRequest(String name, String productId, Integer custoemrId) {
		this.name = name;
		this.productId =productId;
		this.customerId=custoemrId;
	}

	
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

}
