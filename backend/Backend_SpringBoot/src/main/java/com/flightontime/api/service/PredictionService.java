package com.flightontime.api.service;

import com.flightontime.api.dto.PredictionResponse;
import com.flightontime.api.dto.FlightRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
public class PredictionService {

    private final RestTemplate restTemplate;

    @Value("${ds.api.url}")
    private String dsApiUrl;

    public PredictionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Envía los datos del vuelo al microservicio de Python y retorna la predicción.
     * MODIFICACIÓN: Se cambió el orden de las clases para que coincida con el Controller.
     */
    public PredictionResponse getPrediction(FlightRequest request) {

        String endpoint = dsApiUrl;

        //configure header para que fastapi entienda el json
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        //creo la peticion
        HttpEntity<FlightRequest> entity = new HttpEntity<>(request, headers);

        try {
            PredictionResponse response = restTemplate.postForObject(endpoint, entity, PredictionResponse.class);
            System.out.println("response: " + response);
            return response;
        } catch (Exception e) {
            System.err.println("Error conectando con la API de DS: " + e.getMessage());

            PredictionResponse errorRes = new PredictionResponse();
            errorRes.setPrevision("Error de conexión");
            errorRes.setProbabilidad(0.0);
            return errorRes;
        }
    }
}