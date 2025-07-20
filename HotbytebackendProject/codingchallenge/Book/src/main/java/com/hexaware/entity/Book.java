package com.hexaware.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
	@Id
	@NotBlank(message = "ISBN must not be blank")
	@Pattern(regexp = "^[0-9]{10}|[0-9]{13}$", message = "ISBN must be 10 or 13 digits long")
	private String isbn;
	
	@NotBlank(message = "Title must not be blank")
	@Size(min = 2, message = "Title must be greater than 2 characters")
	private String title;

	@NotBlank(message = "Author must not be blank")
	@Size(min=2, message = "Author name must be greater 2 characters")
	private String author;

	@NotNull(message = "Publication year must not be null")
	@Positive(message = "Publication year must be a positive number")
	@Min(1500)
	private int publicationYear;
	

}
