package com.dxctraining.wisheditemmgt.wishedItem.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.dxctraining.wisheditemmgt.exception.WishedItemNotFoundException;
import com.dxctraining.wisheditemmgt.wisheditem.controller.WishedItemController;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Import({WishedItemController.class})
@Transactional
public class WishedItemControllerTest {

	@Autowired
	private WishedItemController controller;

	@Test
	public void testGetWishedItemById_1() {
		Executable execute = () -> controller.getWishedItem("45");
		Assertions.assertThrows(WishedItemNotFoundException.class, execute);
	}
	
}
