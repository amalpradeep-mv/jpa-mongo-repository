package com.dxctraining.wisheditemmgt.wisheditem.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dxctraining.wisheditemmgt.wisheditem.entities.WishedItem;

public interface IWishedItemDao extends JpaRepository<WishedItem, String>{
	
	List<WishedItem> findByName(String name);
	
	@Query("from WishedItem where productId=:productId and customerId=:customerId")
	List<WishedItem> allWishedItemsFromProducts(@Param("productId") String productId, @Param("customerId")Integer customerId);
}
