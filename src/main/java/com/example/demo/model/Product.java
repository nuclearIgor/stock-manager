package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    private Integer stock;
    private String inDate;
    private String outDate;

    public Product() {
    }

    public Product(String productName, Integer stock, String inDate, String outDate) {
        this.productName = productName;
        this.stock = stock;
        this.inDate = inDate;
        this.outDate = outDate;
    }

    public Product(Long id, String productName, Integer stock, String inDate, String outDate) {
        this.id = id;
        this.productName = productName;
        this.stock = stock;
        this.inDate = inDate;
        this.outDate = outDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getInDate() {
        return inDate;
    }

    public void setInDate(String inDate) {
        this.inDate = inDate;
    }

    public String getOutDate() {
        return outDate;
    }

    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }
}