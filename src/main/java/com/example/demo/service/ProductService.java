package com.example.demo.service;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Entry;
import com.example.demo.model.Product;
import com.example.demo.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class ProductService {
    private final ProductRepo productRepo;
    private final EntryService entryService;

    @Autowired
    public ProductService(ProductRepo productRepo, EntryService entryService) {
        this.productRepo = productRepo;
        this.entryService = entryService;
    }

    public Product addProduct(Product product){
        Product possibleProduct = productRepo.checkForProductName(product.getProductName());
        if(possibleProduct != null){
            return possibleProduct;
        }

        Product newProduct = productRepo.save(product);

        String currentDate = LocalDate.now().toString();
        String productName = newProduct.getProductName();
        Long productId = newProduct.getId();
        Integer productStock = newProduct.getStock();



        Entry newEntry = new Entry();
        newEntry.setEntryType("entrada");
        newEntry.setDate(currentDate);
        newEntry.setQuantity(productStock);
        newEntry.setProductId(productId);
        newEntry.setProductName(productName);

        entryService.newEntry(newEntry);

        product.setInDate(currentDate);
        return newProduct;
    }

    public List<Product> findAllProducts(){
        return productRepo.findAll();
    }

    public Product updateProduct (Entry entry, Long id){
        Product product = productRepo.getProductById(id);
        if(product == null){
            throw new NotFoundException();
        }

        String currentDate = LocalDate.now().toString();

        Integer quantity = entry.getQuantity();
        Integer previousStock = product.getStock();
        String entryType = entry.getEntryType();


        if(entryType.equals("saída") & quantity > previousStock){
            throw new BadRequestException();
        }

        entryService.newEntry(entry);


        if (entryType.equals("entrada")){
            Integer newStock = previousStock + quantity;
            product.setStock(newStock);
            product.setInDate(currentDate);
        }
        if (entryType.equals("saída")){
            Integer newStock = previousStock - quantity;
            product.setStock(newStock);
            product.setOutDate(currentDate);
        }
        if(!entry.getProductName().equals(product.getProductName())){
            product.setProductName(entry.getProductName());
        }

        return productRepo.save(product);
    }

    public Product findProductById(Long id){ return productRepo.getProductById(id);}

    public void deleteProduct(Long id){
        productRepo.deleteProductById(id);
    }


    public Product findProductByName(String name) {
        return productRepo.findProductByName(name);
    }
}
