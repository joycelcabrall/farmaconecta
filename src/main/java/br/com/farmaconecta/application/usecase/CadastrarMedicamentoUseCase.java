package br.com.farmaconecta.application.usecase;

import br.com.farmaconecta.domain.model.Medicamento;
import br.com.farmaconecta.domain.repository.MedicamentoRepository;

public class CadastrarMedicamentoUseCase {

    private final MedicamentoRepository medicamentoRepository;

    public CadastrarMedicamentoUseCase(
            MedicamentoRepository medicamentoRepository
    ) {
        this.medicamentoRepository = medicamentoRepository;
    }

    public Medicamento executar(
            String nome,
            String principioAtivo,
            String concentracao,
            String formaFarmaceutica,
            String unidadeMedida
    ) {

        Medicamento medicamento = new Medicamento(
                null,
                nome,
                principioAtivo,
                concentracao,
                formaFarmaceutica,
                unidadeMedida,
                true
        );

        return medicamentoRepository.salvar(medicamento);
    }
}