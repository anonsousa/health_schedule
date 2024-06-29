package br.com.healthcare.schedule.domain.repositories;

import br.com.healthcare.schedule.domain.entities.ConsultaEntity;
import br.com.healthcare.schedule.domain.enums.EnumEspecialidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<ConsultaEntity, Long> {

}
