package com.hexaware.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hexaware.dto.MenuCategoryDTO;
import com.hexaware.entity.MenuCategory;

import jakarta.annotation.PostConstruct;

@Component
public class MenuCategoryMapper {

    @Autowired
    private ModelMapper modelMapper;
    
    @PostConstruct
    public void configureMapping() {
        modelMapper.typeMap(MenuCategoryDTO.class, MenuCategory.class)
                   .addMappings(mapper -> mapper.skip(MenuCategory::setCreatedAt));
    }
    @PostConstruct
    public void setup() {
        modelMapper.typeMap(MenuCategory.class, MenuCategoryDTO.class)
            .addMappings(mapper -> mapper.map(MenuCategory::getCreatedAt, MenuCategoryDTO::setCreatedAt));

        modelMapper.typeMap(MenuCategoryDTO.class, MenuCategory.class)
            .addMappings(mapper -> mapper.map(MenuCategoryDTO::getCreatedAt, MenuCategory::setCreatedAt));
    }

    
    public MenuCategory dtoToEntity(MenuCategoryDTO dto) {
        return modelMapper.map(dto, MenuCategory.class);
    }

    public MenuCategoryDTO entityToDto(MenuCategory entity) {
        return modelMapper.map(entity, MenuCategoryDTO.class);
    }
}
