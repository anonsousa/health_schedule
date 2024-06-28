package br.com.healthcare.schedule.controllers;

import br.com.healthcare.schedule.domain.dtos.PacienteAddDto;
import br.com.healthcare.schedule.domain.dtos.PacienteReturnDto;
import br.com.healthcare.schedule.domain.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/paciente")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<PacienteReturnDto> savePaciente(@RequestBody @Valid PacienteAddDto pacienteAddDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteService.savePaciente(pacienteAddDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteReturnDto> findOnePaciente(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(pacienteService.findOnePaciente(id));
    }

    @GetMapping
    public ResponseEntity<Page<PacienteReturnDto>> findAllPacientes(Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(pacienteService.findAllPacientes(pageable));
    }

    @PutMapping
    public ResponseEntity<PacienteReturnDto> updatePaciente(@RequestParam Long id, @RequestBody @Valid PacienteAddDto pacienteDto){
        return ResponseEntity.status(HttpStatus.OK).body(pacienteService.updatePaciente(id, pacienteDto));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePacienteById(@RequestParam Long id){
        pacienteService.deletePacienteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

