package br.com.farmaconecta.domain.repository;

import br.com.farmaconecta.domain.model.Medicamento;

import java.util.List;
import java.util.Optional;

public interface MedicamentoRepository {

    Medicamento salvar(Medicamento medicamento);

    Optional<Medicamento> buscarPorId(Long id);

    List<Medicamento> listarTodos();

}