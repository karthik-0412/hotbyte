package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.JpaRepo.ItemRepo;
import com.example.demo.entity.Item;

@Service
public class ItemService {
	
	@Autowired
	ItemRepo repo;

	public Item addItem(Item i) {
		Item i1 = repo.save(i);
		return i1;
	}

	public Item getItemByCode(String n) {
		return repo.findById(n).orElse(null);
//		return i1;
	}

	public Item updatePrice(String code, double price) {
		Item item = getItemByCode(code);
		item.setPrice(price);
		return repo.save(item);
	}
	
	public void bill(int quan, String code) {
		Item r = repo.findById(code).orElse(null);
		int totalQ = r.getQuantity();
		if(totalQ >= quan) {
			double price = r.getPrice();
		 repo.UpdateQuant(quan, code);
		 System.out.println( "Total amount : " +quan*price);
		}else {
			return;
		}
	}
	

}
