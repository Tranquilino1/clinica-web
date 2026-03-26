package com.clinica.aauca.clinicaweb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "facturas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "paciente_id", nullable = false)
    private Long patientId;

    @Column(name = "fecha_emision")
    private LocalDateTime emissionDate = LocalDateTime.now();

    private Double subtotal;
    private Double impuestos; // 15% IVA
    private Double total;
    
    private String estado = "PAGADA";

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "factura_id")
    private List<InvoiceItem> items;
}
