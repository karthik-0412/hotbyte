package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Customer;
import com.example.demo.JpaRepo.CustomerRepo;

@Service
public class CustomerService {
	
	@Autowired
	CustomerRepo repo;
	
	public Customer dsaveD(Customer c) {
		
		Customer c1 = repo.save(c);
		return c1;
	}
	
	public List<Customer> getData1()
	{
		
		 List l = repo.findAll();
		
		 return l;
	}
	
	public Customer getSearch(int ac) {
		return repo.findById(ac).orElse(null);
	}
	
	public String deleteById(int ac) {
		 Customer c = repo.findById(ac).orElse(null);
		    if (c != null) {
		        repo.deleteById(ac);
		        return "found" + ac;
		    } else {
		        return "not found";
		    }
	}
	
	public Customer updateCustomer(Customer updatedCustomer) {
	    return repo.save(updatedCustomer); 
	}
	public String depositAmount(int ac,double amt) {
	    Customer c = repo.findById(ac).orElse(null);
	    if (c != null) {
	        c.setBalance(c.getBalance() + amt);
	        repo.save(c);
	        return "deposited " + c.getBalance();
	    } else {
	        return "not found";
	    }
	}

}