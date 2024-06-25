package br.com.healthcare.schedule.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Table(name = "TB_MEDICO")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class MedicoEntity implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMedico;

    @Column(nullable = false, unique = true, length = 25)
    private String crm;
    private String nome;
    @Column(nullable = false, unique = true, length = 120)
    private String email;
    private String telefone;

    @Enumerated(EnumType.STRING)
    private EnumEspecialidade especialidade;

    private boolean ativo;


    @OneToMany(mappedBy = "medico")
    private List<ConsultaEntity> consultas;








    public Long getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Long idMedico) {
        this.idMedico = idMedico;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public EnumEspecialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(EnumEspecialidade especialidade) {
        this.especialidade = especialidade;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public List<ConsultaEntity> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<ConsultaEntity> consultas) {
        this.consultas = consultas;
    }
}
