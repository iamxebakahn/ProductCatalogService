package dev.zeba.productcatalogservice.service;

import dev.zeba.productcatalogservice.model.Product;
import dev.zeba.productcatalogservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
@Slf4j
public class StorageProductService implements  IProductService{
    private final ProductRepository productRepository;

    public StorageProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getProductById(Long id) {
//        Optional<Product> productOptional = productRepository.findById(id);
//        //findById : returns obj based on P.K. : returns Optional
//        //findAll(): select *
//        //deletebyId, deleteAll()
//        if(productOptional.isEmpty()){
//            return null;
//        }
//
//        return productOptional.get();
        log.info("Reading product from DB:" + id);
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
}
