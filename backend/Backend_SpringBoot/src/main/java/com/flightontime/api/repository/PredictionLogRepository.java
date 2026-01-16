package com.flightontime.api.repository;

import com.flightontime.api.entity.PredictionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PredictionLogRepository extends JpaRepository<PredictionLog, Long> {
    // Spring Data JPA ya te da métodos automáticos como:
    // save(), findAll(), findById(), deleteById(), etc.
    // Si después quieres consultas personalizadas (ej: por aerolínea), puedes agregarlas aquí
}
