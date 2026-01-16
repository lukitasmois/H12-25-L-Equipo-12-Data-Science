package com.flightontime.api.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "prediction_log")
public class PredictionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String aerolinea;
    private String origen;
    private String destino;
    private String fechaPartida;
    private Integer distanciaKm;

    private String prevision;
    private Double probabilidad;

    private LocalDateTime fechaPrediccion;

    // Constructor vacío (obligatorio para JPA/Hibernate)
    public PredictionLog() {
    }

    // Constructor útil para crear objetos rápidamente
    public PredictionLog(String aerolinea, String origen, String destino,
                         String fechaPartida, Integer distanciaKm,
                         String prevision, Double probabilidad) {
        this.aerolinea = aerolinea;
        this.origen = origen;
        this.destino = destino;
        this.fechaPartida = fechaPartida;
        this.distanciaKm = distanciaKm;
        this.prevision = prevision;
        this.probabilidad = probabilidad;
        this.fechaPrediccion = LocalDateTime.now();
    }

    // Getters y Setters (puedes generarlos automáticamente en IntelliJ:
    // Click derecho en la clase → Generate → Getter and Setter → selecciona todos los campos)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAerolinea() {
        return aerolinea;
    }

    public void setAerolinea(String aerolinea) {
        this.aerolinea = aerolinea;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getFechaPartida() {
        return fechaPartida;
    }

    public void setFechaPartida(String fechaPartida) {
        this.fechaPartida = fechaPartida;
    }

    public Integer getDistanciaKm() {
        return distanciaKm;
    }

    public void setDistanciaKm(Integer distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    public String getPrevision() {
        return prevision;
    }

    public void setPrevision(String prevision) {
        this.prevision = prevision;
    }

    public Double getProbabilidad() {
        return probabilidad;
    }

    public void setProbabilidad(Double probabilidad) {
        this.probabilidad = probabilidad;
    }

    public LocalDateTime getFechaPrediccion() {
        return fechaPrediccion;
    }

    public void setFechaPrediccion(LocalDateTime fechaPrediccion) {
        this.fechaPrediccion = fechaPrediccion;
    }
}