package br.com.farmaconecta.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UbsJpaRepository
        extends JpaRepository<UbsEntity, Long> {

    Optional<UbsEntity> findByCnes(String cnes);

    boolean existsByCnes(String cnes);
}