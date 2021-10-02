package com.example.demo.repo;

import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Long> {

    void deleteProductById(Long id);

    Optional<Product> findProductById(Long id);

    @Query("SELECT p FROM Product p WHERE p.inDate = ?1")
    Product getProductsByPeriod(String inDate);

    @Query("SELECT p.stock FROM Product p WHERE p.id = ?1")
    Integer getProductStock(Long id);

    @Query("SELECT p.inDate FROM Product p WHERE p.id = ?1")
    LocalDate getProductInDate(Long id);

    @Query("SELECT p.outDate FROM Product p WHERE p.id = ?1")
    LocalDate getProductOutDate(Long id);
}
