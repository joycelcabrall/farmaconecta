package br.com.farmaconecta.infrastructure.configuration;

import br.com.farmaconecta.application.usecase.AtualizarUbsUseCase;
import br.com.farmaconecta.application.usecase.BuscarUbsPorIdUseCase;
import br.com.farmaconecta.application.usecase.CadastrarUbsUseCase;
import br.com.farmaconecta.application.usecase.ListarUbsUseCase;
import br.com.farmaconecta.domain.repository.UbsRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UbsConfiguration {

    @Bean
    public CadastrarUbsUseCase cadastrarUbsUseCase(
            UbsRepository ubsRepository
    ) {
        return new CadastrarUbsUseCase(ubsRepository);
    }

    @Bean
    public ListarUbsUseCase listarUbsUseCase(
            UbsRepository ubsRepository
    ) {
        return new ListarUbsUseCase(ubsRepository);
    }

    @Bean
    public BuscarUbsPorIdUseCase buscarUbsPorIdUseCase(
            UbsRepository ubsRepository
    ) {
        return new BuscarUbsPorIdUseCase(ubsRepository);
    }

    @Bean
    public AtualizarUbsUseCase atualizarUbsUseCase(
            UbsRepository ubsRepository
    ) {
        return new AtualizarUbsUseCase(ubsRepository);
    }
}