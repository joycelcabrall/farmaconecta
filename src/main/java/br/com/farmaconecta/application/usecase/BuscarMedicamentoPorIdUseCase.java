package br.com.farmaconecta.application.usecase;

import br.com.farmaconecta.domain.model.Medicamento;
import br.com.farmaconecta.domain.repository.MedicamentoRepository;

import java.util.Optional;

public class BuscarMedicamentoPorIdUseCase {

    private final MedicamentoRepository medicamentoRepository;

    public BuscarMedicamentoPorIdUseCase(
            MedicamentoRepository medicamentoRepository
    ) {
        this.medicamentoRepository = medicamentoRepository;
    }

    public Optional<Medicamento> executar(Long id) {

        return medicamentoRepository.buscarPorId(id);
    }
}
