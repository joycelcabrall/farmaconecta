package br.com.farmaconecta.infrastructure.configuration;

import br.com.farmaconecta.application.usecase.AtualizarMedicamentoUseCase;
import br.com.farmaconecta.application.usecase.BuscarMedicamentoPorIdUseCase;
import br.com.farmaconecta.application.usecase.CadastrarMedicamentoUseCase;
import br.com.farmaconecta.application.usecase.ListarMedicamentosUseCase;

import br.com.farmaconecta.domain.repository.MedicamentoRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MedicamentoConfiguration {

    @Bean
    public CadastrarMedicamentoUseCase cadastrarMedicamentoUseCase(
            MedicamentoRepository medicamentoRepository
    ) {
        return new CadastrarMedicamentoUseCase(
                medicamentoRepository
        );
    }

    @Bean
    public ListarMedicamentosUseCase listarMedicamentosUseCase(
            MedicamentoRepository medicamentoRepository
    ) {
        return new ListarMedicamentosUseCase(
                medicamentoRepository
        );
    }

    @Bean
    public BuscarMedicamentoPorIdUseCase buscarMedicamentoPorIdUseCase(
            MedicamentoRepository medicamentoRepository
    ) {
        return new BuscarMedicamentoPorIdUseCase(
                medicamentoRepository
        );
    }

    @Bean
    public AtualizarMedicamentoUseCase atualizarMedicamentoUseCase(
            MedicamentoRepository medicamentoRepository
    ) {
        return new AtualizarMedicamentoUseCase(
                medicamentoRepository
        );
    }
}