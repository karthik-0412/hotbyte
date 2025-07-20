package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Item;
import com.example.demo.service.ItemService;

@RestController
public class ItemController {
	
	@Autowired
	ItemService service;
	
	@PostMapping("/addItem")
	public Item addItem(@RequestBody Item i) {
		Item i1 = service.addItem(i);
		return i1;
		
	}
	
	@GetMapping("/getItemById/{code}")
	public Item getItembyCode(@PathVariable String code) {
		return service.getItemByCode(code);
	}
	
	@PutMapping("/updateItem/{code}/{price}")
	public Item updateItem(@PathVariable String code ,@PathVariable double price) {
		return service.updatePrice(code,price);
	}
	@PutMapping("/bill/{quan}/{code}")
	public void genBill(@PathVariable int quan, @PathVariable String code) {
		service.bill(quan, code);
		
	}

}
