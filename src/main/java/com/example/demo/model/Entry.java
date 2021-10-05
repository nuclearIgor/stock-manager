package com.example.demo.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "entry")
public class Entry implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String entryType;
    private String date;
    private Integer quantity;
    private Long productId;
    @Column(name = "productName", nullable = true)
    private String productName;

    public Entry() {
    }

    public Entry(String entryType, String date, Integer quantity, Long productId, String productName) {
        this.entryType = entryType;
        this.date = date;
        this.quantity = quantity;
        this.productId = productId;
        this.productName = productName;
    }

    public Entry(Long id, String entryType, String date, Integer quantity, Long productId, String productName) {
        this.id = id;
        this.entryType = entryType;
        this.date = date;
        this.quantity = quantity;
        this.productId = productId;
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntryType() {
        return entryType;
    }

    public void setEntryType(String entryType) {
        this.entryType = entryType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "id=" + id +
                ", entryType='" + entryType + '\'' +
                ", date='" + date + '\'' +
                ", productId=" + productId +
                '}';
    }
}