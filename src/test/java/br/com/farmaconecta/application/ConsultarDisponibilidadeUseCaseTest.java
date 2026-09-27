package br.com.farmaconecta.application;

import br.com.farmaconecta.application.dto.DisponibilidadeResponse;
import br.com.farmaconecta.application.usecase.ConsultarDisponibilidadeUseCase;
import br.com.farmaconecta.domain.exception.RegraDeNegocioException;
import br.com.farmaconecta.domain.model.Estoque;
import br.com.farmaconecta.domain.model.Medicamento;
import br.com.farmaconecta.domain.model.Ubs;
import br.com.farmaconecta.domain.repository.EstoqueRepository;
import br.com.farmaconecta.domain.repository.MedicamentoRepository;
import br.com.farmaconecta.domain.repository.UbsRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ConsultarDisponibilidadeUseCaseTest {

    private EstoqueRepository estoqueRepository;
    private MedicamentoRepository medicamentoRepository;
    private UbsRepository ubsRepository;

    private ConsultarDisponibilidadeUseCase useCase;

    @BeforeEach
    void preparar() {

        estoqueRepository = mock(EstoqueRepository.class);
        medicamentoRepository = mock(MedicamentoRepository.class);
        ubsRepository = mock(UbsRepository.class);

        useCase = new ConsultarDisponibilidadeUseCase(
                estoqueRepository,
                medicamentoRepository,
                ubsRepository
        );
    }

    @Test
    void deveRetornarListaVaziaQuandoMedicamentoNaoTemEstoque() {

        Medicamento medicamento = mock(Medicamento.class);

        when(medicamentoRepository.buscarPorId(2L))
                .thenReturn(Optional.of(medicamento));

        when(estoqueRepository.listarTodos())
                .thenReturn(List.of());

        List<DisponibilidadeResponse> resultado =
                useCase.executar(2L);

        assertTrue(resultado.isEmpty());
    }

    @Test
    void deveRejeitarMedicamentoInexistente() {

        when(medicamentoRepository.buscarPorId(99L))
                .thenReturn(Optional.empty());

        assertThrows(
                RegraDeNegocioException.class,
                () -> useCase.executar(99L)
        );

        verify(estoqueRepository, never()).listarTodos();
    }

    @Test
    void naoDeveRetornarEstoqueComQuantidadeZero() {

        Medicamento medicamento = mock(Medicamento.class);

        Estoque estoque = new Estoque(
                1L,
                1L,
                1L,
                "DIP2026001",
                LocalDate.of(2027, 12, 31),
                0
        );

        when(medicamentoRepository.buscarPorId(1L))
                .thenReturn(Optional.of(medicamento));

        when(estoqueRepository.listarTodos())
                .thenReturn(List.of(estoque));

        List<DisponibilidadeResponse> resultado =
                useCase.executar(1L);

        assertTrue(resultado.isEmpty());
    }

    @Test
    void deveRetornarMedicamentoDisponivelEmDuasUbs() {

        Medicamento medicamento = mock(Medicamento.class);

        Ubs primeiraUbs = mock(Ubs.class);
        Ubs segundaUbs = mock(Ubs.class);

        when(primeiraUbs.getId()).thenReturn(1L);
        when(primeiraUbs.getNome())
                .thenReturn("UBS Central de Santos");

        when(segundaUbs.getId()).thenReturn(2L);
        when(segundaUbs.getNome())
                .thenReturn("UBS Zona Noroeste");

        Estoque primeiroEstoque = new Estoque(
                1L,
                1L,
                1L,
                "DIP2026001",
                LocalDate.of(2027, 12, 31),
                150
        );

        Estoque segundoEstoque = new Estoque(
                2L,
                2L,
                1L,
                "DIP2026002",
                LocalDate.of(2027, 12, 31),
                80
        );

        when(medicamentoRepository.buscarPorId(1L))
                .thenReturn(Optional.of(medicamento));

        when(estoqueRepository.listarTodos())
                .thenReturn(List.of(
                        primeiroEstoque,
                        segundoEstoque
                ));

        when(ubsRepository.buscarPorId(1L))
                .thenReturn(Optional.of(primeiraUbs));

        when(ubsRepository.buscarPorId(2L))
                .thenReturn(Optional.of(segundaUbs));

        List<DisponibilidadeResponse> resultado =
                useCase.executar(1L);

        assertEquals(2, resultado.size());

        assertEquals(
                "UBS Central de Santos",
                resultado.get(0).nomeUbs()
        );

        assertEquals(
                150,
                resultado.get(0).quantidade()
        );

        assertEquals(
                "UBS Zona Noroeste",
                resultado.get(1).nomeUbs()
        );

        assertEquals(
                80,
                resultado.get(1).quantidade()
        );
    }
}