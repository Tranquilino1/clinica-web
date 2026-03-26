package com.clinica.aauca.clinicaweb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "citas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "paciente_id", nullable = false)
    private Long patientId;

    @Column(name = "medico_id", nullable = false)
    private Long doctorId;

    @Column(name = "fecha_cita", nullable = false)
    private String date;

    @Column(name = "hora_cita", nullable = false)
    private String time;

    private String estado = "PENDIENTE";
    private String motivo;

    @Transient
    private String patientName;
    @Transient
    private String doctorName;
}
