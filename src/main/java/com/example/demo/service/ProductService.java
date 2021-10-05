package com.example.demo.service;

import com.example.demo.model.Entry;
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
    private final EntryService entryService;

    @Autowired
    public ProductService(ProductRepo productRepo, EntryService entryService) {
        this.productRepo = productRepo;
        this.entryService = entryService;
    }

    public Product addProduct(Product product){
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
        Product product = productRepo.getById(id);
        Integer quantity = entry.getQuantity();
        Integer previousStock = product.getStock();
        String entryType = entry.getEntryType();


        if(entryType.equals("saída") & quantity > previousStock){
            throw new IllegalStateException("nao pode");
        }

        entryService.newEntry(entry);


        if (entryType.equals("entrada")){
            Integer newStock = previousStock + quantity;
            product.setStock(newStock);
        }
        if (entryType.equals("saída")){
            Integer newStock = previousStock - quantity;
            product.setStock(newStock);
        }
        if(!entry.getProductName().equals(product.getProductName())){
            product.setProductName(entry.getProductName());
        }

        return productRepo.save(product);
    }

    public Product findProductById(Long id){
//        return productRepo.findProductById(id).orElseThrow(() -> new IllegalStateException("nao existe"));
//        return productRepo.getById(id);
        return productRepo.getProductById(id);
    }

    public void deleteProduct(Long id){
        productRepo.deleteProductById(id);
    }


//    tentativa de relatorio

    public Product findByInDate(String inDate){
        return productRepo.getProductsByPeriod(inDate);
    }
}
