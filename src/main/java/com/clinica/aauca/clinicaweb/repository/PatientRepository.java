package com.clinica.aauca.clinicaweb.repository;

import com.clinica.aauca.clinicaweb.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByDni(String dni);
}
