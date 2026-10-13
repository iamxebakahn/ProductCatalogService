package dev.zeba.productcatalogservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseEntity{
    //Double is not used in production for precision - only BigDecimal is used - for 0 error
    private String name;
    private Double price;
    private String description;
    // for images upload images in blob storage (drive for all files formats e.g. Google drive - which is not scaleable,
    // AWS S3 (solid state) is scalebale, URL of image of S3 bucket is stored in db
    private String imageUrl;
    private Category category; // CATEGORY is not an enum coz, its highly updateable and will req maybe millions of rows to be effected in db
    // Category is now a separate table, so that one is row effected in case of change and coz of its id, in other tables no changes are needed

}
