package com.clinica.aauca.clinicaweb.controller;

import com.clinica.aauca.clinicaweb.model.Appointment;
import com.clinica.aauca.clinicaweb.repository.AppointmentRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = "*")
public class AppointmentController {
    private final AppointmentRepository repository;

    public AppointmentController(AppointmentRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Appointment> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Appointment create(@RequestBody Appointment appointment) {
        return repository.save(appointment);
    }
}
