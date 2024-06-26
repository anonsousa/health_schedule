package br.com.healthcare.schedule.domain.dtos;

import jakarta.validation.constraints.NotBlank;

public record EnderecoDto(
        @NotBlank
        String logradouro,
        @NotBlank
        String numero,
        @NotBlank
        String complemento,
        @NotBlank
        String bairro,
        @NotBlank
        String cidade,
        @NotBlank
        String estado,
        @NotBlank
        String cep
) {
}
