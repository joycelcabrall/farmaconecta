package br.com.farmaconecta.application.usecase;

import br.com.farmaconecta.domain.exception.RegraDeNegocioException;
import br.com.farmaconecta.domain.exception.RecursoNaoEncontradoException;
import br.com.farmaconecta.domain.model.Estoque;
import br.com.farmaconecta.domain.repository.EstoqueRepository;

public class RegistrarSaidaEstoqueUseCase {

    private final EstoqueRepository estoqueRepository;

    public RegistrarSaidaEstoqueUseCase(
            EstoqueRepository estoqueRepository
    ) {
        this.estoqueRepository = estoqueRepository;
    }

    public Estoque executar(Long estoqueId, int quantidadeSaida) {

        if (estoqueId == null || estoqueId <= 0) {
            throw new RegraDeNegocioException(
                    "O ID do estoque deve ser positivo."
            );
        }

        Estoque estoque = estoqueRepository.buscarPorId(estoqueId)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Registro de estoque não encontrado."
                ));

        estoque.retirarQuantidade(quantidadeSaida);

        return estoqueRepository.salvar(estoque);
    }
}