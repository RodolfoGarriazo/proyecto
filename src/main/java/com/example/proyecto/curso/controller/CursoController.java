package com.example.proyecto.curso.controller;

import com.example.proyecto.curso.domail.CursoService;
import com.example.proyecto.curso.dto.CursoRequestDto;
import com.example.proyecto.curso.dto.CursoResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService){
        this.cursoService = cursoService;
    }


    @PostMapping
    public ResponseEntity<CursoResponseDto> createCurso(@RequestBody CursoRequestDto cursoRequestDto) {
        CursoResponseDto response = cursoService.createCurso(cursoRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoResponseDto> getCursoById(@PathVariable Long id) {
        CursoResponseDto response = cursoService.getCursoById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoResponseDto> updateCurso(@PathVariable Long id, @RequestBody CursoRequestDto cursoRequestDto) {
        CursoResponseDto response = cursoService.updateCurso(id, cursoRequestDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurso(@PathVariable Long id) {
        cursoService.deleteCurso(id);
        return ResponseEntity.noContent().build();
    }
}
