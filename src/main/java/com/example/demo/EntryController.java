package com.example.demo;

import com.example.demo.model.Entry;
import com.example.demo.model.Product;
import com.example.demo.service.EntryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entry")
public class EntryController {
    private final EntryService entryService;

    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }

    @PostMapping("/new")
    public ResponseEntity<Entry> newEntry (@RequestBody Entry entry){
        Entry newEntry = entryService.newEntry(entry);
        return new ResponseEntity<>(newEntry, HttpStatus.CREATED);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Entry>> findByProductName(@PathVariable("name") String name){
        List<Entry> results = entryService.findByproduct(name);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<Entry>> findByDate(@PathVariable("date")String date){
        List<Entry> results = entryService.findByDate(date);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }
}
