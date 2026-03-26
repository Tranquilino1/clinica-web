package com.clinica.aauca.clinicaweb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "pacientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String dni;

    @Column(name = "nombre_completo", nullable = false)
    private String fullName;

    private String telefono;
    private String email;
    private String direccion;
    
    @Column(name = "fecha_nacimiento")
    private String birthDate;

    @Column(name = "fecha_registro")
    private LocalDateTime registrationDate = LocalDateTime.now();
}
