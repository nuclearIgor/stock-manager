package com.example.demo;

import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Product;
import com.example.demo.model.Entry;
import com.example.demo.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = productService.findAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Product> getProductByName(@PathVariable("name") String name){
        Product product = productService.findProductByName(name);
        if(product == null){
            throw new NotFoundException();
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Product> getProductById (@PathVariable("id") Long id){
        Product product = productService.findProductById(id);
        if(product == null){
            throw new NotFoundException();
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<Product> createNewProduct(@RequestBody Product product){
        Product newProduct = productService.addProduct(product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Entry entry, @PathVariable("id") Long id){
        Product updatedProduct = productService.updateProduct(entry, id);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id){
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
