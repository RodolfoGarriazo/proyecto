package com.example.proyecto.actividad.domail;

import com.example.proyecto.post.domail.Post;
import com.example.proyecto.usuario.domail.Usuario;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Actividad {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String nombre;

    private Categoria tipo;

    private String enlace;

    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @PrePersist
    public void prePersist(){
        fecha = LocalDate.now();
    }


}
