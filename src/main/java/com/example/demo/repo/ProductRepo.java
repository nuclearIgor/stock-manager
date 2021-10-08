package com.example.demo.repo;

import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ProductRepo extends JpaRepository<Product, Long> {

    void deleteProductById(Long id);

    @Query("SELECT p FROM Product p WHERE p.id = ?1")
    Product getProductById(Long id);

    @Query("SELECT p FROM Product p WHERE p.productName = ?1")
    Product findProductByName(String name);

    @Query("SELECT p FROM Product p WHERE p.productName = ?1")
    Product checkForProductName(String name);
}
