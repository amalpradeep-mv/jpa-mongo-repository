package com.dxctraining.wishedlistmgt.wishedList.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.dxctraining.wishedlistmgt.exception.InvalidArgumentException;
import com.dxctraining.wishedlistmgt.wishedlist.entities.WishedList;
import com.dxctraining.wishedlistmgt.wishedlist.service.IWishedListService;
import com.dxctraining.wishedlistmgt.wishedlist.service.WishedListServiceImpl;

@DataJpaTest
@Import({ WishedListServiceImpl.class })
@ExtendWith(SpringExtension.class)
public class WishedListSericeImplTest {

	@Autowired
	private IWishedListService service;

	@Autowired
	private EntityManager em;

	@Test
	public void testAdd_1() {
		Executable executable = () -> service.save(null);
		Assertions.assertThrows(InvalidArgumentException.class, executable);
	}

	@Test
	public void testAdd_2() {
		String name = "Charger";
		WishedList wished = new WishedList();
		wished.setName(name);
		wished = service.save(wished);
		TypedQuery<WishedList> query = em.createQuery("from WishedList", WishedList.class);
		List<WishedList> list = query.getResultList();
		WishedList stored = list.get(0);
		Assertions.assertEquals(wished.getId(), stored.getId());
		Assertions.assertEquals(name, stored.getName());
	}

	@Test
	public void testFindById_1() {
		String name = "Headset";
		WishedList wished = new WishedList();
		wished.setName(name);
		wished = em.merge(wished);
		String id = wished.getId();
		WishedList result = service.findById(id);
		Assertions.assertEquals(id, result.getId());
		Assertions.assertEquals(wished.getName(), result.getName());
	}

}
