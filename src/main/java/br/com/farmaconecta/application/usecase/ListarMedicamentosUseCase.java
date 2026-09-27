package br.com.farmaconecta.application.usecase;

import br.com.farmaconecta.domain.model.Medicamento;
import br.com.farmaconecta.domain.repository.MedicamentoRepository;

import java.util.List;

public class ListarMedicamentosUseCase {

    private final MedicamentoRepository medicamentoRepository;

    public ListarMedicamentosUseCase(
            MedicamentoRepository medicamentoRepository
    ) {
        this.medicamentoRepository = medicamentoRepository;
    }

    public List<Medicamento> executar() {
        return medicamentoRepository.listarTodos();
    }
}