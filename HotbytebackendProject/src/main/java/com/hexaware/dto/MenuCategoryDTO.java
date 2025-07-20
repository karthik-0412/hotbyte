package com.hexaware.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MenuCategoryDTO {
    private Integer categoryId;
    private String categoryName;
    private String description;
    private Integer itemCount;
    private String status;
    private LocalDate createdAt;
}
