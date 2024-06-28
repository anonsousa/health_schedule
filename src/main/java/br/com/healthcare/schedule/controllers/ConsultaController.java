package br.com.healthcare.schedule.controllers;

import br.com.healthcare.schedule.domain.dtos.ConsultaAddDto;
import br.com.healthcare.schedule.domain.dtos.ConsultaReturnDto;
import br.com.healthcare.schedule.domain.service.ConsultaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/consulta")
public class ConsultaController {
    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    public ResponseEntity<ConsultaReturnDto> createConsulta(@RequestBody @Valid ConsultaAddDto consultaAddDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(consultaService.createConsulta(consultaAddDto));
    }
}
