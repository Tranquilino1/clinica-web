package com.clinica.aauca.clinicaweb.repository;

import com.clinica.aauca.clinicaweb.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
