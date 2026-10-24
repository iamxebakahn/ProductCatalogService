package dev.zeba.productcatalogservice.service;

import dev.zeba.productcatalogservice.dto.ProductDto;
import dev.zeba.productcatalogservice.model.Product;

public interface IProductService {
    Product getProductById(Long id);

    Product createProduct(ProductDto productDto);

    boolean deleteProdcutById(Long id);

    Product replaceProduct(Long id, ProductDto productDto);
    Product patchProduct(Long id, ProductDto productDto);
}
