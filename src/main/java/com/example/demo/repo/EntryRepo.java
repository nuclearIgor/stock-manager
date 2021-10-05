package com.example.demo.repo;

import com.example.demo.model.Entry;
import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EntryRepo extends JpaRepository<Entry, Long> {

    @Query("SELECT e FROM Entry e WHERE e.productName = ?1")
    List<Entry> getEntryByProductName(String name);

    @Query("SELECT e FROM Entry e WHERE e.date = ?1")
    List<Entry> getEntryByDate(String date);
}
