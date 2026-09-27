package br.com.farmaconecta.infrastructure.configuration;

import br.com.farmaconecta.application.usecase.CadastrarEstoqueUseCase;
import br.com.farmaconecta.application.usecase.ConsultarDisponibilidadeUseCase;
import br.com.farmaconecta.application.usecase.ListarEstoquePorUbsUseCase;
import br.com.farmaconecta.application.usecase.RegistrarEntradaEstoqueUseCase;
import br.com.farmaconecta.application.usecase.RegistrarSaidaEstoqueUseCase;

import br.com.farmaconecta.domain.repository.EstoqueRepository;
import br.com.farmaconecta.domain.repository.MedicamentoRepository;
import br.com.farmaconecta.domain.repository.UbsRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EstoqueConfiguration {

    @Bean
    public CadastrarEstoqueUseCase cadastrarEstoqueUseCase(
            EstoqueRepository estoqueRepository,
            UbsRepository ubsRepository,
            MedicamentoRepository medicamentoRepository
    ) {
        return new CadastrarEstoqueUseCase(
                estoqueRepository,
                ubsRepository,
                medicamentoRepository
        );
    }

    @Bean
    public ListarEstoquePorUbsUseCase listarEstoquePorUbsUseCase(
            EstoqueRepository estoqueRepository,
            UbsRepository ubsRepository
    ) {
        return new ListarEstoquePorUbsUseCase(
                estoqueRepository,
                ubsRepository
        );
    }

    @Bean
    public RegistrarEntradaEstoqueUseCase registrarEntradaEstoqueUseCase(
            EstoqueRepository estoqueRepository
    ) {
        return new RegistrarEntradaEstoqueUseCase(
                estoqueRepository
        );
    }

    @Bean
    public RegistrarSaidaEstoqueUseCase registrarSaidaEstoqueUseCase(
            EstoqueRepository estoqueRepository
    ) {
        return new RegistrarSaidaEstoqueUseCase(
                estoqueRepository
        );
    }

    @Bean
    public ConsultarDisponibilidadeUseCase consultarDisponibilidadeUseCase(
            EstoqueRepository estoqueRepository,
            MedicamentoRepository medicamentoRepository,
            UbsRepository ubsRepository
    ) {
        return new ConsultarDisponibilidadeUseCase(
                estoqueRepository,
                medicamentoRepository,
                ubsRepository
        );
    }
}