package com.clinica.aauca.clinicaweb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "historias_clinicas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicalHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "paciente_id", nullable = false)
    private Long patientId;

    @Column(name = "medico_id", nullable = false)
    private Long doctorId;

    @Column(name = "fecha_consulta")
    private LocalDateTime date = LocalDateTime.now();

    @Column(name = "motivo_consulta")
    private String reason;

    private String diagnostico;
    private String tratamiento;
}
