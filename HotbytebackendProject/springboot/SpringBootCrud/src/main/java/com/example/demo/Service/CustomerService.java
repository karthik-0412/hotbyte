package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.JpaRepo.CustomerRepo;
import com.example.demo.entity.Customer;

@Service
public class CustomerService {
	@Autowired
	CustomerRepo Respo;
	public  Customer dsaveD(Customer c )
	 {
	    Customer  c1=	Respo.save(c);
	    return c1;
		
	 }
//	public List getData1() {
//		List l=Respo.findAll();
//		 return l;
//	}
	public List<Customer> getData2()
	{
		 List l=Respo.findAllCustomer();
		 return l;
	}
	
//	public Customer getSearch(int ac)
//	{
//		 return Respo.findById(ac).orElse(null);
//		
//	}
	public Customer getSearch(int ac)
	{
		return Respo.findByActJPQL(ac);
	}
	
	public Customer getSearchName(String n) {
		return Respo.findByNameJPQL(n);
	}
	public int deposite(int ac,double amt) {
		return Respo.deposit1(ac,amt);
	}
	
	public void deleteById(int id) {
	    Respo.deleteById(id);
	}
	public void deleteByCustomerId(int id) {
	    Respo.deleteCustomer(id);
	}
	public String depositAmount(int ac,double amt) {
	    Customer c = Respo.findById(ac).orElse(null);
	    if (c != null) {
	        c.setBalance(c.getBalance() + amt);
	        Respo.save(c);
	        return "deposited " + c.getBalance();
	    } else {
	        return "not found";
	    }
	}
	public String withdrawAmount(int actno, double amt) {
	    Customer c = Respo.findById(actno).orElse(null);

	    if (c == null) {
	        return "Account not found!";
	    }

	    if (c.getBalance() - amt < 1000) {
	        return "Insufficient balance. Minimum ₹1000 must be maintained.";
	    }

	    Respo.withdraw(actno, amt);
	    return "Withdrawal successful. New balance: ₹" + (c.getBalance() - amt);
	}
	
}
