package com.example.proyecto.usuario.infrastructure;

import com.example.proyecto.usuario.domail.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
