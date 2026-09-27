package br.com.farmaconecta.application.usecase;

import br.com.farmaconecta.domain.model.Ubs;
import br.com.farmaconecta.domain.repository.UbsRepository;

import java.util.Optional;

public class BuscarUbsPorIdUseCase {

    private final UbsRepository ubsRepository;

    public BuscarUbsPorIdUseCase(UbsRepository ubsRepository) {
        this.ubsRepository = ubsRepository;
    }

    public Optional<Ubs> executar(Long id) {
        return ubsRepository.buscarPorId(id);
    }
}