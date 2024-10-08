package com.example.proyecto.actividad.controller;
import com.example.proyecto.actividad.domail.ActividadService;
import com.example.proyecto.actividad.dto.ActividadRequestDto;
import com.example.proyecto.actividad.dto.ActividadResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/actividades")
public class ActividadController {

    private final ActividadService actividadService;

    public ActividadController(ActividadService actividadService) {
        this.actividadService = actividadService;
    }

    @PostMapping
    public ResponseEntity<ActividadResponseDto> createActividad(@RequestBody ActividadRequestDto actividadRequestDto) {
        ActividadResponseDto response = actividadService.createActividad(actividadRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActividadResponseDto> getActividadById(@PathVariable Long id) {
        ActividadResponseDto response = actividadService.getActividadById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActividadResponseDto> updateActividad(@PathVariable Long id, @RequestBody ActividadRequestDto actividadRequestDto) {
        ActividadResponseDto response = actividadService.updateActividad(id, actividadRequestDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActividad(@PathVariable Long id) {
        actividadService.deleteActividad(id);
        return ResponseEntity.noContent().build();
    }
}

