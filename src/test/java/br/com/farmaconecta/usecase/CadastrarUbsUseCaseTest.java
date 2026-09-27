package br.com.farmaconecta.application.usecase;

import br.com.farmaconecta.domain.exception.RegraDeNegocioException;
import br.com.farmaconecta.domain.model.Ubs;
import br.com.farmaconecta.domain.repository.UbsRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CadastrarUbsUseCaseTest {

    @Test
    void deveCadastrarUbsQuandoCnesNaoExiste() {

        UbsRepository repositorio = new RepositorioFalso(false);

        CadastrarUbsUseCase casoDeUso =
                new CadastrarUbsUseCase(repositorio);

        Ubs ubs = casoDeUso.executar(
                "UBS Central",
                "1234567",
                "Rua das Flores, 100",
                "Santos",
                "SP"
        );

        assertEquals("UBS Central", ubs.getNome());
        assertEquals("1234567", ubs.getCnes());
        assertTrue(ubs.isAtiva());
    }

    @Test
    void naoDeveCadastrarUbsComCnesDuplicado() {

        UbsRepository repositorio = new RepositorioFalso(true);

        CadastrarUbsUseCase casoDeUso =
                new CadastrarUbsUseCase(repositorio);

        RegraDeNegocioException excecao = assertThrows(
                RegraDeNegocioException.class,
                () -> casoDeUso.executar(
                        "UBS Central",
                        "1234567",
                        "Rua das Flores, 100",
                        "Santos",
                        "SP"
                )
        );

        assertEquals(
                "Já existe uma UBS cadastrada com este CNES.",
                excecao.getMessage()
        );
    }

    private static class RepositorioFalso implements UbsRepository {

        private final boolean cnesExiste;

        RepositorioFalso(boolean cnesExiste) {
            this.cnesExiste = cnesExiste;
        }

        @Override
        public Ubs salvar(Ubs ubs) {
            return ubs;
        }

        @Override
        public Optional<Ubs> buscarPorId(Long id) {
            return Optional.empty();
        }

        @Override
        public Optional<Ubs> buscarPorCnes(String cnes) {
            return Optional.empty();
        }

        @Override
        public List<Ubs> listarTodas() {
            return List.of();
        }

        @Override
        public boolean existePorCnes(String cnes) {
            return cnesExiste;
        }
    }
}