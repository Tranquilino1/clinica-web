package com.clinica.aauca.clinicaweb.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@Component
public class DataInitializer {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        try {
            String dbDest = "/data/clinica_aauca.db";
            String dbSrc = "clinica_aauca.db";
            
            File destFile = new File(dbDest);
            if (!destFile.exists()) {
                System.out.println("Copiando base de datos inicial a /data...");
                File srcFile = new File(dbSrc);
                if (srcFile.exists()) {
                    Files.copy(srcFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
            }
            
            System.out.println("Verificando usuarios...");
            // Asegurar que los usuarios están bien hasheados para la web (Opcional si ya vienen bien)
            // Aquí podríamos forzar una sincronización si el usuario lo pide.
            
        } catch (Exception e) {
            System.err.println("Error en inicialización de datos: " + e.getMessage());
        }
    }
}
