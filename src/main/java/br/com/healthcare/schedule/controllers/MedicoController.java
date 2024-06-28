package br.com.healthcare.schedule.controllers;

import br.com.healthcare.schedule.domain.dtos.MedicoAddDto;
import br.com.healthcare.schedule.domain.dtos.MedicoReturnDto;
import br.com.healthcare.schedule.domain.enums.EnumEspecialidade;
import br.com.healthcare.schedule.domain.service.MedicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MedicoController {
    @Autowired
    private MedicoService medicoService;

    @PostMapping("/medico")
    public ResponseEntity<MedicoReturnDto> saveMedico(@RequestBody @Valid MedicoAddDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(medicoService.saveMedico(dto));
    }

    @GetMapping("/medico/{id}")
    public ResponseEntity<MedicoReturnDto> findOneMedico(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(medicoService.findOneMedico(id));
    }

    @GetMapping("/medico")
    public ResponseEntity<Page<MedicoReturnDto>> findAllMedicos(Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(medicoService.findAllMedicos(pageable));
    }

    @GetMapping("/medico/especialidade")
    public ResponseEntity<Page<MedicoReturnDto>> findMedicosByEspecialidade(@RequestParam EnumEspecialidade especialidade, Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(medicoService.findMedicosByEspecialidade(especialidade, pageable));
    }

    @PutMapping("/medico/{id}")
    public ResponseEntity<MedicoReturnDto> updateMedico(@PathVariable Long id, @RequestBody @Valid MedicoAddDto medicoDto){
        return ResponseEntity.status(HttpStatus.OK).body(medicoService.updateMedico(id, medicoDto));
    }

    @DeleteMapping("/medico/{id}")
    public ResponseEntity<Void> deleteMedico(@PathVariable Long id){
        medicoService.deleteMedicoById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
