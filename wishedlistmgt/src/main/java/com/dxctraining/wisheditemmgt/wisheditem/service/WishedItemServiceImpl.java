package com.dxctraining.wisheditemmgt.wisheditem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxctraining.wisheditemmgt.exception.InvalidArgumentException;
import com.dxctraining.wisheditemmgt.exception.WishedItemNotFoundException;
import com.dxctraining.wisheditemmgt.wisheditem.dao.IWishedItemDao;
import com.dxctraining.wisheditemmgt.wisheditem.entities.WishedItem;

@Service
public class WishedItemServiceImpl implements IWishedItemService {

	@Autowired
	private IWishedItemDao dao;

	@Override
	public WishedItem save(WishedItem wishedItem) {
		validate(wishedItem);
		wishedItem = dao.save(wishedItem);
		return wishedItem;
	}

	@Override
	public WishedItem findById(String id) {
		Optional<WishedItem> optional = dao.findById(id);
		boolean exist = optional.isPresent();
		if (!exist) {
			throw new WishedItemNotFoundException("list not found for id=" + id);
		}
		WishedItem wishedItem = optional.get();
		return wishedItem;
	}

	@Override
	public List<WishedItem> findAll() {
		List<WishedItem> item = dao.findAll();
		return item;
	}

	@Override
	public List<WishedItem> findByName(String name) {
		List<WishedItem> list = dao.findByName(name);
		return list;
	}

	@Override
	public void remove(String id) {
		dao.deleteById(id);
	}

	@Override
	public List<WishedItem> allWishedItemsFromProducts(String productId, Integer customerId) {
		List<WishedItem> list = dao.allWishedItemsFromProducts(productId, customerId);
		return list;
	}

	public void validate(Object obj) {
		if (obj == null) {
			throw new InvalidArgumentException("Argument is null");
		}
	}
}
