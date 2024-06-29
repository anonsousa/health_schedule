package br.com.healthcare.schedule.domain.entities;

import br.com.healthcare.schedule.domain.enums.EnumEspecialidade;
import br.com.healthcare.schedule.domain.enums.EnumStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_CONSULTAS")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ConsultaEntity implements Serializable {
    public static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idConsulta;

    @ManyToOne
    @JoinColumn(name = "idMedico")
    @JsonIgnore
    private MedicoEntity medico;

    @ManyToOne
    @JoinColumn(name = "idPaciente")
    @JsonIgnore
    private PacienteEntity paciente;

    @Enumerated(EnumType.STRING)
    private EnumEspecialidade especialidade;

    @Column(name = "data_inicio_consulta")
    private LocalDateTime dataInicioConsulta;

    @Column(name = "data_fim_consulta")
    private LocalDateTime dataFimConsulta;

    @Enumerated(EnumType.STRING)
    private EnumStatus status;


    public long getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(long idConsulta) {
        this.idConsulta = idConsulta;
    }

    public MedicoEntity getMedico() {
        return medico;
    }

    public void setMedico(MedicoEntity medico) {
        this.medico = medico;
    }

    public PacienteEntity getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteEntity paciente) {
        this.paciente = paciente;
    }

    public LocalDateTime getDataInicioConsulta() {
        return dataInicioConsulta;
    }

    public void setDataInicioConsulta(LocalDateTime dataInicioConsulta) {
        this.dataInicioConsulta = dataInicioConsulta;
    }

    public LocalDateTime getDataFimConsulta() {
        return dataFimConsulta;
    }

    public void setDataFimConsulta(LocalDateTime dataFimConsulta) {
        this.dataFimConsulta = dataFimConsulta;
    }


    public EnumEspecialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(EnumEspecialidade especialidade) {
        this.especialidade = especialidade;
    }

    public EnumStatus getStatus() {
        return status;
    }

    public void setStatus(EnumStatus status) {
        this.status = status;
    }
}
