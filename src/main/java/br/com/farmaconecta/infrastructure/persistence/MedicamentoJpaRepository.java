package br.com.farmaconecta.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicamentoJpaRepository
        extends JpaRepository<MedicamentoEntity, Long> {

}