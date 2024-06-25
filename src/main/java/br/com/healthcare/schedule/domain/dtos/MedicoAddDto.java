package br.com.healthcare.schedule.domain.dtos;

import br.com.healthcare.schedule.domain.entities.EnumEspecialidade;
import jakarta.validation.constraints.*;

public record MedicoAddDto(

        @Pattern(regexp = "^CRM\\s\\d{1,6}\\s[A-Z]{2}$",
                message = "O formato do CRM deve ser 'CRM XXXXXX UF', onde XXXXXX é o número e UF é a sigla do estado.")
        @NotBlank
        String crm,

        @NotBlank
        @Size(min = 3, max = 110)
        String nome,

        @Email
        @NotBlank
        String email,
        @NotBlank
        String telefone,
        @NotNull(message = "Medico deve conter alguma especialidade disponivel: ORTOPEDIA, DERMATOLOGIA, OFTALMOLOGIA, CARDIOLOGIA, NEUROLOGIA, PEDIATRIA, GINECOLOGIA, ENDOCRINOLOGIA")
        EnumEspecialidade especialidade

) {
}
