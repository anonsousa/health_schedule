package br.com.healthcare.schedule.domain.repositories;

import br.com.healthcare.schedule.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserDetails findByEmail(String email);
}
