package dev.zeba.productcatalogservice.dto;


import dev.zeba.productcatalogservice.model.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private String name;
    private Double price;
    private String description;
    //private Category category; // Cant have another entity being exposed here, hence go for a separate DTO for the entity being exposed
    private CategoryDto categoryDto;
}
