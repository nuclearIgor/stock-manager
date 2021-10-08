package com.example.demo;

import com.example.demo.model.Product;
import com.example.demo.repo.ProductRepo;
import com.example.demo.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfig {

    @Bean
    CommandLineRunner commandLineRunner(ProductService productService) {
        return args -> {
            Product banana = new Product();
                   banana.setProductName("banana");
                   banana.setStock(800);

           Product abacate = new Product();
            abacate.setProductName("abacate");
            abacate.setStock(25);

            Product item = new Product();
            item.setProductName("item");
            item.setStock(1);

            productService.addProduct(banana);
            productService.addProduct(abacate);
            productService.addProduct(item);

        };
    }
}

