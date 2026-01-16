package com.flightontime.api.controller;

import com.flightontime.api.dto.PredictionResponse;
import com.flightontime.api.dto.FlightRequest;
import com.flightontime.api.entity.PredictionLog;
import com.flightontime.api.repository.PredictionLogRepository;
import com.flightontime.api.service.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@CrossOrigin(origins = "*") // Esto evita errores de CORS cuando trabajes con el frontend
@RestController
@RequestMapping("/api/v1/flights")
public class PredictionController {

    private final PredictionService predictionService;
    private final PredictionLogRepository predictionLogRepository;

    // Constructor para inyección de dependencias
    public PredictionController(PredictionService predictionService,
                                PredictionLogRepository predictionLogRepository) {
        this.predictionService = predictionService;
        this.predictionLogRepository = predictionLogRepository;
    }

    /**
     * Endpoint POST para predecir si un vuelo será puntual o retrasado.
     * Delega la lógica al PredictionService.
     */
    @PostMapping("/predict")
    public PredictionResponse predecirVuelo(@Valid @RequestBody FlightRequest solicitud) {
        // Delegamos la responsabilidad al Service (lógica mock o real con FastAPI)
        return predictionService.getPrediction(solicitud);
    }

    /**
     * NUEVO ENDPOINT: GET para obtener el historial completo de predicciones.
     * Retorna todos los registros ordenados por fecha de predicción (más reciente primero).
     */
    @GetMapping("/predictions")
    public ResponseEntity<List<PredictionLog>> getHistorialPredicciones() {
        List<PredictionLog> historial = predictionLogRepository.findAll(
                Sort.by(Sort.Direction.DESC, "fechaPrediccion")
        );
        return ResponseEntity.ok(historial);
    }
}