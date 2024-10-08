package com.example.proyecto.actividad.dto;

import com.example.proyecto.actividad.domail.Categoria;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ActividadResponseDto {

    private Long id;

    private String nombre;

    private Categoria tipo;

    private String enlace;

    private LocalDate fecha;

    private Long postId;

}
