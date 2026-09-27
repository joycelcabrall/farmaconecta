package br.com.farmaconecta.application.usecase;

import br.com.farmaconecta.application.dto.DisponibilidadeResponse;
import br.com.farmaconecta.domain.exception.RegraDeNegocioException;
import br.com.farmaconecta.domain.exception.RecursoNaoEncontradoException;
import br.com.farmaconecta.domain.model.Estoque;
import br.com.farmaconecta.domain.model.Ubs;
import br.com.farmaconecta.domain.repository.EstoqueRepository;
import br.com.farmaconecta.domain.repository.MedicamentoRepository;
import br.com.farmaconecta.domain.repository.UbsRepository;

import java.util.List;

public class ConsultarDisponibilidadeUseCase {

    private final EstoqueRepository estoqueRepository;
    private final MedicamentoRepository medicamentoRepository;
    private final UbsRepository ubsRepository;

    public ConsultarDisponibilidadeUseCase(
            EstoqueRepository estoqueRepository,
            MedicamentoRepository medicamentoRepository,
            UbsRepository ubsRepository
    ) {
        this.estoqueRepository = estoqueRepository;
        this.medicamentoRepository = medicamentoRepository;
        this.ubsRepository = ubsRepository;
    }

    public List<DisponibilidadeResponse> executar(Long medicamentoId) {

        if (medicamentoId == null || medicamentoId <= 0) {
            throw new RegraDeNegocioException(
                    "O ID do medicamento deve ser positivo."
            );
        }

        if (medicamentoRepository.buscarPorId(medicamentoId).isEmpty()) {
            throw new RecursoNaoEncontradoException(
                    "Medicamento não encontrado."
            );
        }

        return estoqueRepository.listarTodos()
                .stream()
                .filter(estoque ->
                        estoque.getMedicamentoId().equals(medicamentoId)
                )
                .filter(estoque -> estoque.getQuantidade() > 0)
                .map(this::montarResposta)
                .toList();
    }

    private DisponibilidadeResponse montarResposta(Estoque estoque) {

        Ubs ubs = ubsRepository.buscarPorId(estoque.getUbsId())
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "UBS do estoque não encontrada."
                ));

        return new DisponibilidadeResponse(
                estoque.getId(),
                ubs.getId(),
                ubs.getNome(),
                estoque.getMedicamentoId(),
                estoque.getLote(),
                estoque.getQuantidade()
        );
    }
}