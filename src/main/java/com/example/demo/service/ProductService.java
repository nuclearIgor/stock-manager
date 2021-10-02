package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {
    private final ProductRepo productRepo;

    @Autowired
    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Product addProduct(Product product){

        product.setInDate(LocalDate.now());
        return productRepo.save(product);
    }

    public List<Product> findAllProducts(){
        return productRepo.findAll();
    }

    public Product updateProduct (Product product, Long id){

        Integer previousStock = productRepo.getProductStock(id);
        LocalDate previousInDate = productRepo.getProductInDate(id);
        LocalDate previousOutDate = productRepo.getProductOutDate(id);

        if(product.getStock().equals(previousStock)){
            product.setInDate(previousInDate);
            product.setOutDate(previousOutDate);
        }

        if(product.getStock() > previousStock){
            product.setInDate(LocalDate.now());
            product.setOutDate(previousOutDate);
        }

        if(product.getStock() < previousStock){
            product.setOutDate(LocalDate.now());
            product.setInDate(previousInDate);
        }

        return productRepo.save(product);
    }

    public Product findProductById(Long id){
        return productRepo.findProductById(id).orElseThrow(() -> new IllegalStateException("nao existe"));

    }

    public void deleteProduct(Long id){
        productRepo.deleteProductById(id);
    }


//    tentativa de relatorio

    public Product findByInDate(String inDate){
        return productRepo.getProductsByPeriod(inDate);
    }
}
