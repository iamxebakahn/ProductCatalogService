package dev.zeba.productcatalogservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseEntity{
    private String name;
    private String description;
    @OneToMany(mappedBy = "category") //Added mapped by to avoid creating mapping table
    private List<Product> products;
}
