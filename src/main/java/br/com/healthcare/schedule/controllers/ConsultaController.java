package br.com.healthcare.schedule.controllers;

import br.com.healthcare.schedule.domain.dtos.ConsultaAddDto;
import br.com.healthcare.schedule.domain.dtos.ConsultaReturnDto;
import br.com.healthcare.schedule.domain.service.ConsultaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/consulta")
public class ConsultaController {
    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    public ResponseEntity<ConsultaReturnDto> createConsulta(@RequestBody @Valid ConsultaAddDto consultaAddDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(consultaService.createConsulta(consultaAddDto));
    }

    @PutMapping("/finish")
    public ResponseEntity<ConsultaReturnDto> finishConsulta(@RequestParam Long id){
        return ResponseEntity.status(HttpStatus.OK).body(consultaService.finishConsulta(id));
    }

    @PutMapping("/unmark")
    public ResponseEntity<ConsultaReturnDto> unmarkConsulta(@RequestParam Long id){
        return ResponseEntity.status(HttpStatus.OK).body(consultaService.unmarkConsulta(id));
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<ConsultaReturnDto>> findByAnoAndMes(@RequestParam int ano,
                                                                   @RequestParam int mes,
                                                                   Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(consultaService.findAllConsultasByYearAndMonth(ano, mes, pageable));
    }

    @GetMapping
    public ResponseEntity<ConsultaReturnDto> findConsultaById(@RequestParam Long id){
        return ResponseEntity.status(HttpStatus.OK).body(consultaService.findConsultaById(id));
    }
}
