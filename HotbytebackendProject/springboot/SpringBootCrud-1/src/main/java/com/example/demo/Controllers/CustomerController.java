package com.example.demo.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Customer;
import com.example.demo.Service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	CustomerService service;
	
	@PostMapping("/saveData")
	
	public Customer saveData1(@RequestBody Customer c) {
		Customer c1 = service.dsaveD(c);
		return c1;
		
		
	}
	
	@GetMapping("/getUsers")
    public   List<Customer> getData()
    {
		
		List l=service.getData1();
		
		return l;
		
		
    }
	
	@GetMapping("/getdataAc/{ac}")
	public Customer getByAct(@PathVariable int ac) {
		return service.getSearch(ac);
	}
	
	@DeleteMapping("/deleteUserAc/{ac}")
	public String deleteCustomer(@PathVariable int ac) {
		return service.deleteById(ac);
	}
	@PutMapping("/updateCustomer")
	public Customer updateCustomer(@RequestBody Customer customer) {
	    return service.updateCustomer(customer);
	}
	@PutMapping("/deposit/{ac}/{amt}")
	public String deposit(@PathVariable int ac, @PathVariable double amt) {
	    return service.depositAmount(ac, amt);
	}
}