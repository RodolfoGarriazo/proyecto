package com.example.proyecto.email.event;

import lombok.Getter;

@Getter
public class EmailEvent {

    private String emailDestino;
    private String nombreUsuario;
    private String nombreActividad;
    private String fechaActividad;
    private String horaInicio;
    private String enlaceActividad;

    public EmailEvent(String emailDestino, String nombreUsuario, String nombreActividad,
                      String fechaActividad, String horaInicio, String enlaceActividad) {
        this.emailDestino = emailDestino;
        this.nombreUsuario = nombreUsuario;
        this.nombreActividad = nombreActividad;
        this.fechaActividad = fechaActividad;
        this.horaInicio = horaInicio;
        this.enlaceActividad = enlaceActividad;

    }
}
