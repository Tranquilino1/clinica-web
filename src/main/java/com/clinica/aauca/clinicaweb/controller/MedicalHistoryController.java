package com.clinica.aauca.clinicaweb.controller;

import com.clinica.aauca.clinicaweb.model.MedicalHistory;
import com.clinica.aauca.clinicaweb.repository.MedicalHistoryRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/medical-history")
@CrossOrigin(origins = "*")
public class MedicalHistoryController {
    private final MedicalHistoryRepository repository;

    public MedicalHistoryController(MedicalHistoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/patient/{patientId}")
    public List<MedicalHistory> getByPatient(@PathVariable Long patientId) {
        return repository.findByPatientId(patientId);
    }

    @PostMapping
    public MedicalHistory create(@RequestBody MedicalHistory history) {
        return repository.save(history);
    }
}
