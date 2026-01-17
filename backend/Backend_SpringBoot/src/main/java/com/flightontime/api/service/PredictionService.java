package com.flightontime.api.service;

import com.flightontime.api.entity.PredictionLog;
import com.flightontime.api.repository.PredictionLogRepository;
import com.flightontime.api.dto.PredictionResponse;
import com.flightontime.api.dto.FlightRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PredictionService {

    private final RestTemplate restTemplate;

    @Autowired
    private PredictionLogRepository predictionLogRepository;  // ← Nuevo: inyectamos el repositorio

    @Value("${ds.api.url}")
    private String dsApiUrl;

    public PredictionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Envía los datos del vuelo al microservicio de Python y retorna la predicción.
     * MODIFICACIÓN: Se cambió el orden de las clases para que coincida con el Controller.
     * NUEVO: Guarda el log de la predicción en MySQL
     */
    public PredictionResponse getPrediction(FlightRequest request) {

        // --- INICIO DE BLOQUE MOCK (PARA TESTEO LOCAL) ---
        // Comenta este bloque cuando quieras usar la IA real en FastAPI
        PredictionResponse respuestaMock = new PredictionResponse();
        respuestaMock.setPrevision("On Time");
        respuestaMock.setProbabilidad(0.95);
        // --- FIN DE BLOQUE MOCK ---

        /*
        // LÓGICA REAL PARA FAST API (Se activará después)
        String endpoint = dsApiUrl + "/predict";
        PredictionResponse respuestaReal = restTemplate.postForObject(endpoint, request, PredictionResponse.class);
        // Usa respuestaReal en vez de respuestaMock cuando actives esto
        */

        // Usamos la respuesta del mock (o real cuando lo actives)
        PredictionResponse response = respuestaMock;  // ← Cambia a respuestaReal cuando uses la API

        // NUEVO: Guardamos el log en la base de datos
        PredictionLog log = new PredictionLog(
                request.getAerolinea(),
                request.getOrigen(),
                request.getDestino(),
                request.getFechaPartida(),
                request.getDistanciaKm(),
                response.getPrevision(),
                response.getProbabilidad()
        );

        predictionLogRepository.save(log);  // ← Guarda automáticamente en MySQL

        // Retornamos la respuesta normal al controller
        return response;
    }
}