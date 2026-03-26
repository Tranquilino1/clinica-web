package com.clinica.aauca.clinicaweb.controller;

import com.clinica.aauca.clinicaweb.model.Product;
import com.clinica.aauca.clinicaweb.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {
    private final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Product> getAll() {
        return repository.findAll();
    }
}
