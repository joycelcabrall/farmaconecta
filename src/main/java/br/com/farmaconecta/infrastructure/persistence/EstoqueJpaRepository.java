package br.com.farmaconecta.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface EstoqueJpaRepository
        extends JpaRepository<EstoqueEntity, Long> {

    Optional<EstoqueEntity>
    findByUbsIdAndMedicamentoIdAndLoteAndValidade(
            Long ubsId,
            Long medicamentoId,
            String lote,
            LocalDate validade
    );
}