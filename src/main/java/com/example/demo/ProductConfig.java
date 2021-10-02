package com.example.demo;

import com.example.demo.model.Product;
import com.example.demo.repo.ProductRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class ProductConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            ProductRepo repository) {
        return args -> {
            Product fruta = new Product(
                    "banana",
                    40,
                    LocalDate.of(2021, 9, 20),
                    LocalDate.of(2021, 8, 25)
            );

            Product legume = new Product(
                    "pepino",
                    50,
                    LocalDate.of(2020, 7, 15),
                    LocalDate.of(2020, 6, 10)
            );

            repository.saveAll(
                    List.of(legume, fruta)
            );

        };
    }
}

