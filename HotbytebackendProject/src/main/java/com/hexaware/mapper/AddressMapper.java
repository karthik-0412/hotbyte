package com.hexaware.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hexaware.dto.AddressDTO;
import com.hexaware.entity.Address;
import com.hexaware.entity.User;

@Component
public class AddressMapper {
	
	@Autowired
	private ModelMapper modelMapper;

	public Address addressDtoToAddress(AddressDTO dto) {
	    Address address = modelMapper.map(dto, Address.class);

	    return address;
	}

	public AddressDTO addressToAddressDto(Address address) {
	    AddressDTO dto = modelMapper.map(address, AddressDTO.class);
	    
	    return dto;
	}

	public Address map(AddressDTO addressDTO, Class<Address> class1) {
		// TODO Auto-generated method stub
		return null;
	}


}
