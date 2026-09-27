package br.com.farmaconecta.domain.repository;

import br.com.farmaconecta.domain.model.Ubs;

import java.util.List;
import java.util.Optional;

public interface UbsRepository {

    Ubs salvar(Ubs ubs);

    Optional<Ubs> buscarPorId(Long id);

    Optional<Ubs> buscarPorCnes(String cnes);

    List<Ubs> listarTodas();

    boolean existePorCnes(String cnes);
}