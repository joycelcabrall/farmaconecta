package br.com.farmaconecta.application.usecase;

import br.com.farmaconecta.domain.exception.RegraDeNegocioException;
import br.com.farmaconecta.domain.exception.RecursoNaoEncontradoException;
import br.com.farmaconecta.domain.model.Estoque;
import br.com.farmaconecta.domain.repository.EstoqueRepository;

public class RegistrarEntradaEstoqueUseCase {

    private final EstoqueRepository estoqueRepository;

    public RegistrarEntradaEstoqueUseCase(
            EstoqueRepository estoqueRepository
    ) {
        this.estoqueRepository = estoqueRepository;
    }

    public Estoque executar(Long estoqueId, int quantidadeEntrada) {

        if (estoqueId == null || estoqueId <= 0) {
            throw new RegraDeNegocioException(
                    "O ID do estoque deve ser positivo."
            );
        }

        Estoque estoque = estoqueRepository.buscarPorId(estoqueId)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Registro de estoque não encontrado."
                ));

        estoque.adicionarQuantidade(quantidadeEntrada);

        return estoqueRepository.salvar(estoque);
    }
}