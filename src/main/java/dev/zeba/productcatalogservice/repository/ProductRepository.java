package dev.zeba.productcatalogservice.repository;

import dev.zeba.productcatalogservice.model.Product;
import dev.zeba.productcatalogservice.model.enums.State;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.util.Optional;

@Repository
//why interface whynot a class: coz implementation needs to be done for all methods in JPA instead hibernate has already implemented these methods,
// so we use thede methods instead of implementing them
public interface ProductRepository extends JpaRepository<Product,Long> {

    Optional<Product> findByIdAndState(Long id, State state);

    //2 methods of using JPA a. inbuilt: findAll(), findById() b. custom JPA function: findByName() : filter /reading/sorting/searching no computations
    //or calculations can be done like. avg, sum etc
    //final resort we write query on our own: @Query -> 2 syntaxes : JPQL and SQL(Native query or normal way of writing SQL)
    //prod preference JPQL (written in terms of Java entities)

    @EntityGraph(attributePaths = {"category"})
    Page<Product> findByPriceBetweenAndState(BigDecimal minPrice, BigDecimal maxPrice, State state, Pageable pageable);
}
