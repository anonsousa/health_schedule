package br.com.healthcare.schedule.domain.dtos;

import br.com.healthcare.schedule.domain.enums.EnumEspecialidade;
import br.com.healthcare.schedule.domain.entities.MedicoEntity;

public record MedicoReturnDto(

        Long idMedico,
        String crm,
        String nome,
        String email,
        String telefone,
        EnumEspecialidade especialidade
) {
    public MedicoReturnDto(MedicoEntity medico){
        this(
                medico.getIdMedico(),
                medico.getCrm(),
                medico.getNome(),
                medico.getEmail(),
                medico.getTelefone(),
                medico.getEspecialidade()
        );
    }
}
