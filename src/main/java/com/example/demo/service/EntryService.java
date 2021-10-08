package com.example.demo.service;

import com.example.demo.model.Entry;
import com.example.demo.model.Product;
import com.example.demo.repo.EntryRepo;
import com.example.demo.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EntryService {
    private final EntryRepo entryRepo;
    private final ProductRepo productRepo;

    @Autowired
    public EntryService(EntryRepo entryRepo, ProductRepo productRepo) {
        this.entryRepo = entryRepo;
        this.productRepo = productRepo;
    }

    public Entry newEntry(Entry entry){
        entry.setDate(LocalDate.now().toString());
        return entryRepo.save(entry);
    }

    public List<Entry> findByproduct(String name) {
        return entryRepo.getEntryByProductName(name);
    }

    public List<Entry> findByDate(String date) {
        return entryRepo.getEntryByDate(date);
    }
}
