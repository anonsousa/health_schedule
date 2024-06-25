package br.com.healthcare.schedule.domain.repositories;

import br.com.healthcare.schedule.domain.entities.EnumEspecialidade;
import br.com.healthcare.schedule.domain.entities.MedicoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<MedicoEntity, Long> {

    Page<MedicoEntity> findByEspecialidade(EnumEspecialidade especialidade, Pageable pageable);
}
