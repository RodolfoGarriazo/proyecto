package com.example.proyecto.carrera.infrastructure;

import com.example.proyecto.carrera.domail.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarreraRepository extends JpaRepository<Carrera, Long> {
}
