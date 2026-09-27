package br.com.farmaconecta.application.usecase;

import br.com.farmaconecta.domain.exception.RegraDeNegocioException;
import br.com.farmaconecta.domain.model.Ubs;
import br.com.farmaconecta.domain.repository.UbsRepository;

public class CadastrarUbsUseCase {

    private final UbsRepository ubsRepository;

    public CadastrarUbsUseCase(UbsRepository ubsRepository) {
        this.ubsRepository = ubsRepository;
    }

    public Ubs executar(
            String nome,
            String cnes,
            String endereco,
            String cidade,
            String uf
    ) {

        Ubs ubs = new Ubs(
                null,
                nome,
                cnes,
                endereco,
                cidade,
                uf,
                true
        );

        if (ubsRepository.existePorCnes(ubs.getCnes())) {
            throw new RegraDeNegocioException(
                    "Já existe uma UBS cadastrada com este CNES."
            );
        }

        return ubsRepository.salvar(ubs);
    }
}