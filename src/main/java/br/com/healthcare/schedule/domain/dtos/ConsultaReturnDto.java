package br.com.healthcare.schedule.domain.dtos;

import br.com.healthcare.schedule.domain.entities.ConsultaEntity;
import br.com.healthcare.schedule.domain.enums.EnumStatus;

import java.time.LocalDateTime;

public record ConsultaReturnDto(
        Long idConsulta,
        Long idMedico,
        Long idPaciente,
        LocalDateTime dataConsulta,
        EnumStatus status
) {
    public ConsultaReturnDto(ConsultaEntity consultaEntity){
        this(
                consultaEntity.getIdConsulta(),
                consultaEntity.getMedico().getIdMedico(),
                consultaEntity.getPaciente().getIdPaciente(),
                consultaEntity.getDataConsulta(),
                consultaEntity.getStatus()
        );
    }
}
