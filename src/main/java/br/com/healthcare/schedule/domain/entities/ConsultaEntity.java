package br.com.healthcare.schedule.domain.entities;

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

    @Column(name = "data_consulta")
    private LocalDateTime dataConsulta;

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

    public LocalDateTime getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(LocalDateTime dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public EnumStatus getStatus() {
        return status;
    }

    public void setStatus(EnumStatus status) {
        this.status = status;
    }
}
