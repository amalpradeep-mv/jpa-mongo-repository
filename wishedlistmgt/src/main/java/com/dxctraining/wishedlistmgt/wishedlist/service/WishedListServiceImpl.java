package com.dxctraining.wishedlistmgt.wishedlist.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxctraining.wishedlistmgt.exception.InvalidArgumentException;
import com.dxctraining.wishedlistmgt.exception.WishedListNotFoundException;
import com.dxctraining.wishedlistmgt.wishedlist.dao.IWishedListDao;
import com.dxctraining.wishedlistmgt.wishedlist.entities.WishedList;

@Service
public class WishedListServiceImpl implements IWishedListService {

	@Autowired
	private IWishedListDao dao;

	@Override
	public WishedList save(WishedList wishedList) {
		validate(wishedList);
		wishedList = dao.save(wishedList);
		return wishedList;
	}

	@Override
	public WishedList findById(String id) {
		Optional<WishedList> optional = dao.findById(id);
		boolean exist = optional.isPresent();
		if (!exist) {
			throw new WishedListNotFoundException("list not found for id=" + id);
		}
		WishedList wishedList = optional.get();
		return wishedList;
	}

	@Override
	public List<WishedList> findAll() {
		List<WishedList> list = dao.findAll();
		return list;
	}

	@Override
	public List<WishedList> findByName(String name) {
		List<WishedList> list = dao.findByName(name);
		return list;
	}

	@Override
	public void remove(String id) {
		dao.deleteById(id);
	}

	@Override
	public List<WishedList> allWishedListsFromProducts(String productId, Integer customerId) {
		List<WishedList> list = dao.allWishedListsFromProducts(productId, customerId);
		return list;
	}

	public void validate(Object obj) {
		if (obj == null) {
			throw new InvalidArgumentException("Argument is null");
		}
	}
}
