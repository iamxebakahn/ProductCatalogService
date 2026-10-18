package dev.zeba.productcatalogservice.service;

import dev.zeba.productcatalogservice.model.Product;

public interface IProductService {
    Product getProductById(Long id);
}
