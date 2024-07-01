package br.com.healthcare.schedule.domain.repositories;

import br.com.healthcare.schedule.domain.entities.PacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface PacienteRepository extends JpaRepository<PacienteEntity, Long> {

    @Query("SELECT COUNT(c) > 0 FROM ConsultaEntity c WHERE c.paciente.idPaciente = :idPaciente AND " +
            "((c.dataInicioConsulta <= :dataInicioConsulta AND c.dataFimConsulta > :dataInicioConsulta) OR " +
            "(c.dataInicioConsulta < :dataFimConsulta AND c.dataFimConsulta >= :dataFimConsulta) OR " +
            "(c.dataInicioConsulta >= :dataInicioConsulta AND c.dataFimConsulta <= :dataFimConsulta)) AND c.status = 'AGENDADA'")
    boolean existsConsultaAgendadaByPacienteEHorario(@Param("idPaciente") Long idPaciente,
                                                     @Param("dataInicioConsulta") LocalDateTime dataInicioConsulta,
                                                     @Param("dataFimConsulta") LocalDateTime dataFimConsulta);
}
