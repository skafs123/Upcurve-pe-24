package com.targetindia.repository;


import com.targetindia.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    // w.r.t northwind database, find the list of products between price range of `min` and `max`
    // public List<Product> findByUnitPriceBetween(double min, double max)
    // JPQL --> select p from Product p where p.unitPrice between ?1 and ?2


   @Query(value = "select p from Product p where p.unitPrice between ?1 and ?2 ")
   List<Product> findByUnitPriceBetween(double min, double max);


}
