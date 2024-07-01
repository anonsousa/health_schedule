package br.com.healthcare.schedule.domain.repositories;

import br.com.healthcare.schedule.domain.entities.ConsultaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ConsultaRepository extends JpaRepository<ConsultaEntity, Long> {

    @Query("SELECT c FROM ConsultaEntity c WHERE YEAR(c.dataInicioConsulta) = :ano AND MONTH(c.dataInicioConsulta) = :mes")
    Page<ConsultaEntity> findConsultasByMesEAno(@Param("ano") int ano, @Param("mes") int mes, Pageable pageable);

}
