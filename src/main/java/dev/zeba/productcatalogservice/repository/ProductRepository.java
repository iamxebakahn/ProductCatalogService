package dev.zeba.productcatalogservice.repository;

import dev.zeba.productcatalogservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//why interface whynot a class: coz implementation needs to be done for all methods in JPA instead hibernate has already implemented these methods,
// so we use thede methods instead of implementing them
public interface ProductRepository extends JpaRepository<Product,Long> {

}
