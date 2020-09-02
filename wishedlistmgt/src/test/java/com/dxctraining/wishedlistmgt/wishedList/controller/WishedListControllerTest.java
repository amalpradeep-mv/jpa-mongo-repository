package com.dxctraining.wishedlistmgt.wishedList.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.dxctraining.wishedlistmgt.exception.WishedListNotFoundException;
import com.dxctraining.wishedlistmgt.wishedlist.controller.WishedListController;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Import({WishedListController.class})
@Transactional
public class WishedListControllerTest {

	@Autowired
	private WishedListController controller;

	@Test
	public void testGetWishedListById_1() {
		Executable execute = () -> controller.getWishedList("45");
		Assertions.assertThrows(WishedListNotFoundException.class, execute);
	}
	
}
