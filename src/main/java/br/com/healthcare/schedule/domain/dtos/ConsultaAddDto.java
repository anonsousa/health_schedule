package br.com.healthcare.schedule.domain.dtos;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ConsultaAddDto(

        @NotNull
        Long idMedico,
        @NotNull
        Long idPaciente,
        @FutureOrPresent
        @NotNull
        LocalDateTime dataConsulta

) {
}
