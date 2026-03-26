package com.clinica.aauca.clinicaweb.controller;

import com.clinica.aauca.clinicaweb.model.Patient;
import com.clinica.aauca.clinicaweb.repository.PatientRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin(origins = "*")
public class PatientController {
    private final PatientRepository repository;

    public PatientController(PatientRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Patient> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Patient create(@RequestBody Patient patient) {
        return repository.save(patient);
    }
}
