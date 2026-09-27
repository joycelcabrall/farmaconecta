package br.com.farmaconecta.application.usecase;

import br.com.farmaconecta.domain.model.Ubs;
import br.com.farmaconecta.domain.repository.UbsRepository;

import java.util.Optional;

public class AtualizarUbsUseCase {

    private final UbsRepository ubsRepository;

    public AtualizarUbsUseCase(UbsRepository ubsRepository) {
        this.ubsRepository = ubsRepository;
    }

    public Optional<Ubs> executar(
            Long id,
            String nome,
            String endereco,
            String cidade,
            String uf
    ) {

        Optional<Ubs> ubsEncontrada = ubsRepository.buscarPorId(id);

        if (ubsEncontrada.isEmpty()) {
            return Optional.empty();
        }

        Ubs ubs = ubsEncontrada.get();

        ubs.atualizarDados(
                nome,
                endereco,
                cidade,
                uf
        );

        Ubs ubsAtualizada = ubsRepository.salvar(ubs);

        return Optional.of(ubsAtualizada);
    }
}