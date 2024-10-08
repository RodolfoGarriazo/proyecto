package com.example.proyecto.actividad.dto;

import com.example.proyecto.actividad.domail.Categoria;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class ActividadRequestDto {

    private String nombre;

    private Categoria tipo;

    private String enlace;

    private Long postId;

    private String emailUsuario;
    private String nombreUsuario;
    private String fechaActividad;
    private String horaInicio;
    private String duracionActividad;


}
