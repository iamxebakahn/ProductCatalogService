package dev.zeba.productcatalogservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DummyJsonProductDto {
    private Long id;
    private String title;
    private String description;
    private String category;
    private Double price;
    private String thumbnail;
}
