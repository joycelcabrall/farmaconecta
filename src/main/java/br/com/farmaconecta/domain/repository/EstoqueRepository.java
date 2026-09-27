package br.com.farmaconecta.domain.repository;

import br.com.farmaconecta.domain.model.Estoque;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EstoqueRepository {

    Estoque salvar(Estoque estoque);

    Optional<Estoque> buscarPorId(Long id);

    List<Estoque> listarTodos();

    List<Estoque> listarPorUbsId(Long ubsId);

    Optional<Estoque> buscarPorUbsMedicamentoLoteEValidade(
            Long ubsId,
            Long medicamentoId,
            String lote,
            LocalDate validade
    );
}