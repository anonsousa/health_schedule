package br.com.healthcare.schedule.controllers;

import br.com.healthcare.schedule.domain.dtos.MedicoAddDto;
import br.com.healthcare.schedule.domain.dtos.MedicoReturnDto;
import br.com.healthcare.schedule.domain.service.MedicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MedicoController {
    @Autowired
    private MedicoService medicoService;

    @PostMapping("/medico")
    public ResponseEntity<MedicoReturnDto> saveMedico(@RequestBody @Valid MedicoAddDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(medicoService.saveMedico(dto));
    }
}
