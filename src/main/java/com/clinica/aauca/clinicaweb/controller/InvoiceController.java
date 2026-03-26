package com.clinica.aauca.clinicaweb.controller;

import com.clinica.aauca.clinicaweb.model.Invoice;
import com.clinica.aauca.clinicaweb.repository.InvoiceRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/invoices")
@CrossOrigin(origins = "*")
public class InvoiceController {
    private final InvoiceRepository repository;

    public InvoiceController(InvoiceRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Invoice> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Invoice create(@RequestBody Invoice invoice) {
        // Calculate taxes and total if not provided
        if (invoice.getSubtotal() != null) {
            invoice.setImpuestos(invoice.getSubtotal() * 0.15);
            invoice.setTotal(invoice.getSubtotal() + invoice.getImpuestos());
        }
        return repository.save(invoice);
    }
}
