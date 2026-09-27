package br.com.farmaconecta.application.usecase;

import br.com.farmaconecta.domain.exception.RegraDeNegocioException;
import br.com.farmaconecta.domain.exception.RecursoNaoEncontradoException;
import br.com.farmaconecta.domain.model.Estoque;
import br.com.farmaconecta.domain.repository.EstoqueRepository;
import br.com.farmaconecta.domain.repository.MedicamentoRepository;
import br.com.farmaconecta.domain.repository.UbsRepository;

import java.time.LocalDate;

public class CadastrarEstoqueUseCase {

    private final EstoqueRepository estoqueRepository;
    private final UbsRepository ubsRepository;
    private final MedicamentoRepository medicamentoRepository;

    public CadastrarEstoqueUseCase(
            EstoqueRepository estoqueRepository,
            UbsRepository ubsRepository,
            MedicamentoRepository medicamentoRepository
    ) {
        this.estoqueRepository = estoqueRepository;
        this.ubsRepository = ubsRepository;
        this.medicamentoRepository = medicamentoRepository;
    }

    public Estoque executar(
            Long ubsId,
            Long medicamentoId,
            String lote,
            LocalDate validade,
            int quantidade
    ) {

        if (ubsId == null || ubsId <= 0) {
            throw new RegraDeNegocioException(
                    "A UBS é obrigatória."
            );
        }

        if (medicamentoId == null || medicamentoId <= 0) {
            throw new RegraDeNegocioException(
                    "O medicamento é obrigatório."
            );
        }

        if (lote == null || lote.isBlank()) {
            throw new RegraDeNegocioException(
                    "O lote é obrigatório."
            );
        }

        if (validade == null) {
            throw new RegraDeNegocioException(
                    "A validade é obrigatória."
            );
        }

        if (quantidade <= 0) {
            throw new RegraDeNegocioException(
                    "A quantidade de entrada deve ser maior que zero."
            );
        }

        if (ubsRepository.buscarPorId(ubsId).isEmpty()) {
            throw new RecursoNaoEncontradoException(
                    "UBS não encontrada."
            );
        }

        if (medicamentoRepository.buscarPorId(medicamentoId).isEmpty()) {
            throw new RecursoNaoEncontradoException(
                    "Medicamento não encontrado."
            );
        }

        String loteNormalizado = lote.trim();

        boolean loteJaCadastrado = estoqueRepository
                .buscarPorUbsMedicamentoLoteEValidade(
                        ubsId,
                        medicamentoId,
                        loteNormalizado,
                        validade
                )
                .isPresent();

        if (loteJaCadastrado) {
            throw new RegraDeNegocioException(
                    "Este lote já está cadastrado no estoque desta UBS."
            );
        }

        Estoque estoque = new Estoque(
                null,
                ubsId,
                medicamentoId,
                loteNormalizado,
                validade,
                quantidade
        );

        return estoqueRepository.salvar(estoque);
    }
}