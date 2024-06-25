package br.com.healthcare.schedule.domain.repositories;

import br.com.healthcare.schedule.domain.entities.MedicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<MedicoEntity, Long> {
}
