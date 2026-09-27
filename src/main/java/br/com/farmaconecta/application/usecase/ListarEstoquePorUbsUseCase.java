package br.com.farmaconecta.application.usecase;

import br.com.farmaconecta.domain.exception.RegraDeNegocioException;
import br.com.farmaconecta.domain.exception.RecursoNaoEncontradoException;
import br.com.farmaconecta.domain.model.Estoque;
import br.com.farmaconecta.domain.repository.EstoqueRepository;
import br.com.farmaconecta.domain.repository.UbsRepository;

import java.util.List;

public class ListarEstoquePorUbsUseCase {

    private final EstoqueRepository estoqueRepository;
    private final UbsRepository ubsRepository;

    public ListarEstoquePorUbsUseCase(
            EstoqueRepository estoqueRepository,
            UbsRepository ubsRepository
    ) {
        this.estoqueRepository = estoqueRepository;
        this.ubsRepository = ubsRepository;
    }

    public List<Estoque> executar(Long ubsId) {

        if (ubsId == null || ubsId <= 0) {
            throw new RegraDeNegocioException(
                    "O ID da UBS deve ser positivo."
            );
        }

        if (ubsRepository.buscarPorId(ubsId).isEmpty()) {
            throw new RecursoNaoEncontradoException(
                    "UBS não encontrada."
            );
        }

        return estoqueRepository.listarPorUbsId(ubsId);
    }
}