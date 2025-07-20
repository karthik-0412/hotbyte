package com.hexaware.serviceImplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.dto.AddressDTO;
import com.hexaware.dto.CustomerDTO;
import com.hexaware.dto.UserCreateDTO;
import com.hexaware.dto.UserDTO;
import com.hexaware.entity.Address;
import com.hexaware.entity.Customer;
import com.hexaware.entity.User;
import com.hexaware.enums.UserRole;
import com.hexaware.mapper.AddressMapper;
import com.hexaware.mapper.CustomerMapper;
import com.hexaware.repository.AddressRepository;
import com.hexaware.repository.CustomerRepository;

import com.hexaware.repository.UserRepository;
import com.hexaware.service.CustomerService;
import com.hexaware.service.UserService;

import jakarta.transaction.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private UserService userService;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CustomerMapper customerMapper;

	@Autowired
	private AddressMapper addressMapper;

	@Transactional
	@Override
	public CustomerDTO registerCustomer(CustomerDTO customerDTO) {
	    // Extract UserCreateDTO
	    UserCreateDTO userCreateDTO = customerDTO.getUserdto(); // or getUserCreateDTO() depending on your getter name
	    userCreateDTO.setRole(UserRole.CUSTOMER); // Ensure role is always CUSTOMER

	    // Call user service to create the user
	    UserDTO createdUserDTO = userService.createUser(userCreateDTO);

	    // Fetch the full user entity (to assign to customer)
	    User user = userRepository.findById(createdUserDTO.getUserId())
	            .orElseThrow(() -> new RuntimeException("User not found after creation: " + createdUserDTO.getUserId()));

	    // Save address
	    AddressDTO addressDTO = customerDTO.getAddress();
	    Address address = addressMapper.addressDtoToAddress(addressDTO);
	    address = addressRepository.save(address);

	    // Map and save customer
	    Customer customer = customerMapper.customerDtoToCustomer(customerDTO);
	    customer.setUser(user);
	    customer.setAddress(address);
	    customer = customerRepository.save(customer);

	    return customerMapper.customerToCustomerDto(customer);
	}


	@Override
	public CustomerDTO getCustomer(int customerId) {
		Customer customer = customerRepository.findById(customerId).orElseThrow();
		return customerMapper.customerToCustomerDto(customer);
	}

	@Override
	public CustomerDTO updateCustomer(int customerId, CustomerDTO customerDTO) {
	    Customer customer = customerRepository.findById(customerId)
	            .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));

	    // Update customer fields only if provided
	    if (customerDTO.getFullName() != null && !customerDTO.getFullName().isBlank()) {
	        customer.setFullName(customerDTO.getFullName());
	    }
	    if (customerDTO.getAge() > 0) {
	        customer.setAge(customerDTO.getAge());
	    }
	    if (customerDTO.getGender() != null) {
	        customer.setGender(customerDTO.getGender());
	    }
	    if (customerDTO.getMobileNumber() != null && !customerDTO.getMobileNumber().isBlank()) {
	        customer.setMobileNumber(customerDTO.getMobileNumber());
	    }

	    // Update address if provided
	    if (customerDTO.getAddress() != null) {
	        Address address = addressMapper.addressDtoToAddress(customerDTO.getAddress());
	        Address savedAddress = addressRepository.save(address); // ensure address is saved separately
	        customer.setAddress(savedAddress);
	    }

	    Customer updatedCustomer = customerRepository.save(customer);
	    return customerMapper.customerToCustomerDto(updatedCustomer);
	}


	@Override
	public void deleteCustomer(int customerId) {
		customerRepository.deleteById(customerId);
	}

	@Override
	public List<CustomerDTO> getAllCustomers() {
		List<Customer> customers = customerRepository.findAll();
		return customers.stream().map(customerMapper::customerToCustomerDto).collect(Collectors.toList());
	}

	@Override
	public CustomerDTO getCustomerByUserName(String username) {
		Customer customer = customerRepository.findByUsername(username);
		return customerMapper.customerToCustomerDto(customer);
	}

}