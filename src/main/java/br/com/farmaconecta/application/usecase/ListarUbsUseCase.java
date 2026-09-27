package br.com.farmaconecta.application.usecase;

import br.com.farmaconecta.domain.model.Ubs;
import br.com.farmaconecta.domain.repository.UbsRepository;

import java.util.List;

public class ListarUbsUseCase {

    private final UbsRepository ubsRepository;

    public ListarUbsUseCase(UbsRepository ubsRepository) {
        this.ubsRepository = ubsRepository;
    }

    public List<Ubs> executar() {
        return ubsRepository.listarTodas();
    }
}