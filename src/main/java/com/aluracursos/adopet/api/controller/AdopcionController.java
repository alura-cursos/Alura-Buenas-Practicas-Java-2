package com.aluracursos.adopet.api.controller;

import com.aluracursos.adopet.api.dto.AprobacionAdopcionDto;
import com.aluracursos.adopet.api.dto.ReprobacionAdopcionDto;
import com.aluracursos.adopet.api.dto.SolicitudAdopcionDto;
import com.aluracursos.adopet.api.exceptions.ValidacionException;
import com.aluracursos.adopet.api.service.AdopcionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adopciones")
public class AdopcionController {

    @Autowired
    private AdopcionService adopcionService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> solicitar(@RequestBody @Valid SolicitudAdopcionDto dto) {
        try {
            this.adopcionService.solicitar(dto);
            return ResponseEntity.ok("Adopción solicitada satisfactoriamente!");
        } catch (ValidacionException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/aprobar")
    @Transactional
    public ResponseEntity<String> aprobar(@RequestBody @Valid AprobacionAdopcionDto dto) {
        this.adopcionService.aprobar(dto);
        return ResponseEntity.ok("Solicitud aprobada!");
    }

    @PutMapping("/reprobar")
    @Transactional
    public ResponseEntity<String> reprobar(@RequestBody @Valid ReprobacionAdopcionDto dto) {
        this.adopcionService.reprobar(dto);
        return ResponseEntity.ok("Solicitud reprobada.");
    }

}
