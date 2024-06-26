package br.com.healthcare.schedule.domain.dtos;

import br.com.healthcare.schedule.domain.entities.EnumSexo;
import br.com.healthcare.schedule.domain.entities.PacienteEntity;

import java.time.LocalDate;

public record PacienteReturnDto(

        Long idPaciente,
        String cpf,
        String nome,
        LocalDate dataNascimento,
        String email,
        String telefone,
        EnumSexo sexo,
        EnderecoDto endereco
) {
    public PacienteReturnDto(PacienteEntity paciente){
        this(
                paciente.getIdPaciente(),
                paciente.getCpf(),
                paciente.getNome(),
                paciente.getDataNascimento(),
                paciente.getEmail(),
                paciente.getTelefone(),
                paciente.getSexo(),
                new EnderecoDto(
                        paciente.getEndereco().getLogradouro(),
                        paciente.getEndereco().getNumero(),
                        paciente.getEndereco().getComplemento(),
                        paciente.getEndereco().getBairro(),
                        paciente.getEndereco().getCidade(),
                        paciente.getEndereco().getEstado(),
                        paciente.getEndereco().getCep()
                )
        );
    }
}
