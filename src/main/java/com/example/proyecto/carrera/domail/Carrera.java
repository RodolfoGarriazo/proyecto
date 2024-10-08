package com.example.proyecto.carrera.domail;

import com.example.proyecto.curso.domail.Curso;
import com.example.proyecto.usuario.domail.Usuario;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Carrera {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;

    @OneToMany(mappedBy = "carrera")
    private List<Curso> cursos;

}
