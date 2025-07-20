package com.hexaware.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hexaware.dto.AddressDTO;
import com.hexaware.dto.RestaurantDTO;
import com.hexaware.dto.RestaurantPublicDTO;
import com.hexaware.dto.UserCreateDTO;
import com.hexaware.entity.Address;
import com.hexaware.entity.Restaurant;
import com.hexaware.entity.User;

import jakarta.annotation.PostConstruct;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
public class RestaurantMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Restaurant restaurantDtoToRestaurant(RestaurantDTO dto) {
        return modelMapper.map(dto, Restaurant.class);
    }

    public RestaurantDTO restaurantToRestaurantDto(Restaurant restaurant) {
        return modelMapper.map(restaurant, RestaurantDTO.class);
    }

    public Restaurant restaurantPublicDtoToRestaurant(RestaurantPublicDTO dto) {
        return modelMapper.map(dto, Restaurant.class);
    }

    public RestaurantPublicDTO restaurantToRestaurantPublicDto(Restaurant restaurant) {
        return modelMapper.map(restaurant, RestaurantPublicDTO.class);
    }

    @PostConstruct
    public void init() {
        // Converter for String to LocalTime (RestaurantDTO to Restaurant, input as HH:mm)
//        modelMapper.typeMap(RestaurantDTO.class, Restaurant.class).addMappings(mapper -> {
//            mapper.using(ctx -> ctx.getSource() != null ? LocalTime.parse((String) ctx.getSource(), DateTimeFormatter.ofPattern("HH:mm")) : null)
//                  .map(RestaurantDTO::getOpeningTime, Restaurant::setOpeningTime);
//            mapper.using(ctx -> ctx.getSource() != null ? LocalTime.parse((String) ctx.getSource(), DateTimeFormatter.ofPattern("HH:mm")) : null)
//                  .map(RestaurantDTO::getClosingTime, Restaurant::setClosingTime);
//        });
//
//        // Converter for LocalTime to String (Restaurant to RestaurantDTO, output as HH:mm:ss)
//        modelMapper.typeMap(Restaurant.class, RestaurantDTO.class).addMappings(mapper -> {
//            mapper.using(ctx -> ctx.getSource() != null ? ((LocalTime) ctx.getSource()).format(DateTimeFormatter.ofPattern("HH:mm:ss")) : null)
//                  .map(Restaurant::getOpeningTime, RestaurantDTO::setOpeningTime);
//            mapper.using(ctx -> ctx.getSource() != null ? ((LocalTime) ctx.getSource()).format(DateTimeFormatter.ofPattern("HH:mm:ss")) : null)
//                  .map(Restaurant::getClosingTime, RestaurantDTO::setClosingTime);
//        });

        // Restaurant -> RestaurantDTO
        modelMapper.addMappings(new PropertyMap<Restaurant, RestaurantDTO>() {
            @Override
            protected void configure() {
                using(ctx -> {
                    User user = ((Restaurant) ctx.getSource()).getUser();
                    return user != null ? modelMapper.map(user, UserCreateDTO.class) : null;
                }).map(source, destination.getUserdto());

                using(ctx -> {
                    Address address = ((Restaurant) ctx.getSource()).getAddress();
                    return address != null ? modelMapper.map(address, AddressDTO.class) : null;
                }).map(source, destination.getAddressdto());
            }
        });

        // RestaurantDTO -> Restaurant
        modelMapper.addMappings(new PropertyMap<RestaurantDTO, Restaurant>() {
            @Override
            protected void configure() {
                using(ctx -> {
                    UserCreateDTO userDTO = ((RestaurantDTO) ctx.getSource()).getUserdto();
                    return userDTO != null ? modelMapper.map(userDTO, User.class) : null;
                }).map(source, destination.getUser());

                using(ctx -> {
                    AddressDTO addressDTO = ((RestaurantDTO) ctx.getSource()).getAddressdto();
                    return addressDTO != null ? modelMapper.map(addressDTO, Address.class) : null;
                }).map(source, destination.getAddress());
            }
        });

        // Skip auto-generated IDs
        modelMapper.typeMap(AddressDTO.class, Address.class).addMappings(mapper -> {
            mapper.skip(Address::setAddressId);
        });
    }
}