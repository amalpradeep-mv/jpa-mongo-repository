package com.dxctraining.wisheditemmgt.wisheditem.service;

import java.util.List;

import com.dxctraining.wisheditemmgt.wisheditem.entities.WishedItem;

public interface IWishedItemService {

	WishedItem save(WishedItem wishedItem);

	WishedItem findById(String id);

	List<WishedItem> findAll();

	List<WishedItem> findByName(String name);
	
	void remove(String id);

	List<WishedItem> allWishedItemsFromProducts(String productId, Integer customerId);

}
