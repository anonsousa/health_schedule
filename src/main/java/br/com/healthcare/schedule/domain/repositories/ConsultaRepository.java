package br.com.healthcare.schedule.domain.repositories;

import br.com.healthcare.schedule.domain.entities.ConsultaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaRepository extends JpaRepository<ConsultaEntity, Long> {
}
