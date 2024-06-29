package br.com.healthcare.schedule.domain.dtos;

import br.com.healthcare.schedule.domain.entities.ConsultaEntity;
import br.com.healthcare.schedule.domain.enums.EnumEspecialidade;
import br.com.healthcare.schedule.domain.enums.EnumStatus;

import java.time.LocalDateTime;

public record ConsultaReturnDto(
        Long idConsulta,
        Long idMedico,
        Long idPaciente,
        EnumEspecialidade especialidade,
        LocalDateTime dataInicioConsulta,
        LocalDateTime dataFimConsulta,
        EnumStatus status
) {
    public ConsultaReturnDto(ConsultaEntity consultaEntity){
        this(
                consultaEntity.getIdConsulta(),
                consultaEntity.getMedico().getIdMedico(),
                consultaEntity.getPaciente().getIdPaciente(),
                consultaEntity.getEspecialidade(),
                consultaEntity.getDataInicioConsulta(),
                consultaEntity.getDataFimConsulta(),
                consultaEntity.getStatus()
        );
    }
}
