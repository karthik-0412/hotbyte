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

import com.example.demo.Service.CustomerService;
import com.example.demo.entity.Customer;

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
		
		List l=service.getData2();
		
		return l;
		
		
    }
//	@GetMapping("/getdataAc/{ac}")
//    public  Customer getByAct(@PathVariable("ac") int  ac)
//    {
//		   
//		  return  service.getSearch(ac);  
//		   
//    }
	@GetMapping("/getdataAc/{ac}")
    public  Customer getByAct(@PathVariable("ac") int ac)
    {
		   
		  return  service.getSearch(ac);  
		   
    }
	@GetMapping("/getdataName/{n}")
    public  Customer getByName(@PathVariable("n") String n)
    {
		   
		  return  service.getSearchName(n);  
		   
    }
	@DeleteMapping("/deleteAc/{id}")
	public String deleteByAct(@PathVariable("id") int id) {
	    service.deleteById(id);
	    return "Deleted account with ID " + id;
	}
	@DeleteMapping("/deleteCusAc/{id}")
	public String deleteByCustAc(@PathVariable("id") int id) {
	    service.deleteByCustomerId(id);
	    return "Deleted account with ID " + id;
	}
	@PutMapping("/deposit/{ac}/{amt}")
	public String deposit(@PathVariable int ac, @PathVariable double amt) {
	    return service.depositAmount(ac, amt);
	}
	
	@PutMapping("/deposit1/{ac}/{amt}")
	public int deposit1(@PathVariable("ac") int ac, @PathVariable("amt") double amt) {
	    return service.deposite(ac, amt);
	}
	@PutMapping("/withdraw/{actno}/{amt}")
	public String withdraw(@PathVariable("actno") int actno, @PathVariable("amt") double amt) {
	    return service.withdrawAmount(actno, amt);
	}
}
