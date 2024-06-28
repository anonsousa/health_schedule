package br.com.healthcare.schedule.domain.dtos;

import br.com.healthcare.schedule.domain.enums.EnumSexo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record PacienteAddDto(
        @NotBlank
        @CPF
        String cpf,
        @NotBlank
        String nome,

        @NotNull
        LocalDate dataNascimento,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String telefone,

        EnderecoDto endereco,

        @NotNull
        EnumSexo sexo
) {
}
