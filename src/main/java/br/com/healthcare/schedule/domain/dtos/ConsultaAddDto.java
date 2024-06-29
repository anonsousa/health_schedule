package br.com.healthcare.schedule.domain.dtos;

import br.com.healthcare.schedule.domain.enums.EnumEspecialidade;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ConsultaAddDto(

        @NotNull
        Long idPaciente,
        @NotNull
        EnumEspecialidade especialidade,
        @FutureOrPresent
        @NotNull
        LocalDateTime dataConsulta

) {
}
