package br.com.healthcare.schedule.domain.repositories;

import br.com.healthcare.schedule.domain.enums.EnumEspecialidade;
import br.com.healthcare.schedule.domain.entities.MedicoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MedicoRepository extends JpaRepository<MedicoEntity, Long> {

    Page<MedicoEntity> findByEspecialidade(EnumEspecialidade especialidade, Pageable pageable);


    @Query("SELECT m FROM MedicoEntity m WHERE m.ativo = true AND m.especialidade = :especialidade AND " +
            "NOT EXISTS (SELECT c FROM ConsultaEntity c WHERE c.medico = m AND " +
            "((c.dataInicioConsulta <= :dataInicioConsulta AND c.dataFimConsulta > :dataInicioConsulta) OR " +
            "(c.dataInicioConsulta < :dataFimConsulta AND c.dataFimConsulta >= :dataFimConsulta) OR " +
            "(c.dataInicioConsulta >= :dataInicioConsulta AND c.dataFimConsulta <= :dataFimConsulta)))")
    List<MedicoEntity> findMedicosDisponiveis(@Param("especialidade") EnumEspecialidade especialidade,
                                              @Param("dataInicioConsulta") LocalDateTime dataInicioConsulta,
                                              @Param("dataFimConsulta") LocalDateTime dataFimConsulta);


}
