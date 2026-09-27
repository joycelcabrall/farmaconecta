package br.com.farmaconecta.application.usecase;

import br.com.farmaconecta.domain.model.Medicamento;
import br.com.farmaconecta.domain.repository.MedicamentoRepository;

import java.util.Optional;

public class AtualizarMedicamentoUseCase {

    private final MedicamentoRepository medicamentoRepository;

    public AtualizarMedicamentoUseCase(
            MedicamentoRepository medicamentoRepository
    ) {
        this.medicamentoRepository = medicamentoRepository;
    }

    public Optional<Medicamento> executar(
            Long id,
            String nome,
            String principioAtivo,
            String concentracao,
            String formaFarmaceutica,
            String unidadeMedida
    ) {

        Optional<Medicamento> medicamentoEncontrado =
                medicamentoRepository.buscarPorId(id);

        if (medicamentoEncontrado.isEmpty()) {
            return Optional.empty();
        }

        Medicamento medicamento = medicamentoEncontrado.get();

        medicamento.atualizarDados(
                nome,
                principioAtivo,
                concentracao,
                formaFarmaceutica,
                unidadeMedida
        );

        Medicamento medicamentoAtualizado =
                medicamentoRepository.salvar(medicamento);

        return Optional.of(medicamentoAtualizado);
    }
}