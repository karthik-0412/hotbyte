package com.hexaware.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hexaware.dto.AddressDTO;
import com.hexaware.dto.CustomerDTO;
import com.hexaware.dto.UserCreateDTO;
import com.hexaware.entity.Address;
import com.hexaware.entity.Customer;
import com.hexaware.entity.User;

import jakarta.annotation.PostConstruct;

@Component
public class CustomerMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Customer customerDtoToCustomer(CustomerDTO customerDTO) {
        return modelMapper.map(customerDTO, Customer.class);
    }

    public CustomerDTO customerToCustomerDto(Customer customer) {
        return modelMapper.map(customer, CustomerDTO.class);
    }

    @PostConstruct
    public void init() {
        // Mapping from Entity → DTO
        modelMapper.addMappings(new PropertyMap<Customer, CustomerDTO>() {
            @Override
            protected void configure() {
                using(ctx -> modelMapper.map(((Customer) ctx.getSource()).getUser(), UserCreateDTO.class))
                    .map(source, destination.getUserdto());
                using(ctx -> modelMapper.map(((Customer) ctx.getSource()).getAddress(), AddressDTO.class))
                    .map(source, destination.getAddress());
            }
        });

        // Mapping from DTO → Entity
        modelMapper.addMappings(new PropertyMap<CustomerDTO, Customer>() {
            @Override
            protected void configure() {
                using(ctx -> modelMapper.map(((CustomerDTO) ctx.getSource()).getUserdto(), User.class))
                    .map(source, destination.getUser());
                using(ctx -> modelMapper.map(((CustomerDTO) ctx.getSource()).getAddress(), Address.class))
                    .map(source, destination.getAddress());
            }
        });

        // ID skipping
        modelMapper.typeMap(CustomerDTO.class, Customer.class).addMappings(mapper -> {
            mapper.skip(Customer::setCustomerId);
        });

        modelMapper.typeMap(AddressDTO.class, Address.class).addMappings(mapper -> {
            mapper.skip(Address::setAddressId);
        });

        modelMapper.typeMap(UserCreateDTO.class, User.class).addMappings(mapper -> {
            mapper.skip(User::setUserId);
        });
    }
}
