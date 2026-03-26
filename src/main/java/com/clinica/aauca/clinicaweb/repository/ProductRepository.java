package com.clinica.aauca.clinicaweb.repository;

import com.clinica.aauca.clinicaweb.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
