package com.dxctraining.wishedlistmgt.wishedlist.service;

import java.util.List;

import com.dxctraining.wishedlistmgt.wishedlist.entities.WishedList;

public interface IWishedListService {

	WishedList save(WishedList wishedList);

	WishedList findById(String id);

	List<WishedList> findAll();

	List<WishedList> findByName(String name);
	
	void remove(String id);

	List<WishedList> allWishedListsFromProducts(String productId, Integer customerId);

}
