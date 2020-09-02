package com.dxctraining.wishedlistmgt.wishedlist.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dxctraining.wishedlistmgt.wishedlist.entities.WishedList;

public interface IWishedListDao extends JpaRepository<WishedList, String>{
	
	List<WishedList> findByName(String name);
	
	@Query("from WishedList where productId=:productId and customerId=:customerId")
	List<WishedList> allWishedListsFromProducts(@Param("productId") String productId, @Param("customerId")Integer customerId);
}
