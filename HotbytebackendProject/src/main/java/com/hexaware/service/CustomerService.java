package com.hexaware.service;

import java.util.List;

import com.hexaware.dto.CustomerDTO;
import com.hexaware.dto.MenuDTO;
import com.hexaware.dto.OrderDTO;
import com.hexaware.dto.MenuFilterDto;

public interface CustomerService {
	
	CustomerDTO registerCustomer(CustomerDTO customerDTO);
    CustomerDTO getCustomer(int customerId);
    CustomerDTO updateCustomer(int customerId, CustomerDTO customerDTO);
    void deleteCustomer(int customerId);
    List<CustomerDTO> getAllCustomers();
	CustomerDTO getCustomerByUserName(String username);
	
}