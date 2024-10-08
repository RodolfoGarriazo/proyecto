package com.example.proyecto.usuario.domail;

import com.example.proyecto.carrera.domail.Carrera;
import com.example.proyecto.comentario.domail.Comentario;
import com.example.proyecto.material.domail.Material;
import com.example.proyecto.post.domail.Post;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String nombre;

    @Email
    private String email;

    @Size(min = 8, message = "la contraseña debe tener un min de 8 caracteres")
    private String password;

    private LocalDate fechaRegistro;

    @ManyToOne
    private Carrera carrera;

    @OneToMany(mappedBy = "usuario")
    private List<Post> posts;

    @OneToMany(mappedBy = "usuario")
    private List<Material> materiales;

    @OneToMany(mappedBy = "usuario")
    private List<Comentario> comentarios;

    public Usuario() {

    }

    @PrePersist
    public void registro(){
        fechaRegistro = LocalDate.now();
    }

    public Usuario(Long id) {
        this.id = id;
    }


}
