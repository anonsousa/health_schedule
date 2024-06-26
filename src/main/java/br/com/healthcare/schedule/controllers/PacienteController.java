package br.com.healthcare.schedule.controllers;

import br.com.healthcare.schedule.domain.dtos.PacienteAddDto;
import br.com.healthcare.schedule.domain.dtos.PacienteReturnDto;
import br.com.healthcare.schedule.domain.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/paciente")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<PacienteReturnDto> savePaciente(@RequestBody @Valid PacienteAddDto pacienteAddDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteService.savePaciente(pacienteAddDto));
    }
}

