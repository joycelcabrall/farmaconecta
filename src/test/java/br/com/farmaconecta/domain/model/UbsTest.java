package br.com.farmaconecta.domain.model;

import br.com.farmaconecta.domain.exception.RegraDeNegocioException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UbsTest {

    @Test
    void deveCriarUbsComDadosValidos() {

        Ubs ubs = new Ubs(
                1L,
                "UBS Central",
                "1234567",
                "Rua das Flores, 100",
                "Santos",
                "SP",
                true
        );

        assertEquals(1L, ubs.getId());
        assertEquals("UBS Central", ubs.getNome());
        assertEquals("1234567", ubs.getCnes());
        assertEquals("SP", ubs.getUf());
        assertTrue(ubs.isAtiva());
    }

    @Test
    void naoDeveCriarUbsSemNome() {

        RegraDeNegocioException excecao = assertThrows(
                RegraDeNegocioException.class,
                () -> new Ubs(
                        null,
                        "",
                        "1234567",
                        "Rua das Flores, 100",
                        "Santos",
                        "SP",
                        true
                )
        );

        assertEquals(
                "O nome da UBS é obrigatório.",
                excecao.getMessage()
        );
    }

    @Test
    void naoDeveCriarUbsComCnesInvalido() {

        RegraDeNegocioException excecao = assertThrows(
                RegraDeNegocioException.class,
                () -> new Ubs(
                        null,
                        "UBS Central",
                        "123",
                        "Rua das Flores, 100",
                        "Santos",
                        "SP",
                        true
                )
        );

        assertEquals(
                "O CNES deve conter exatamente 7 números.",
                excecao.getMessage()
        );
    }

    @Test
    void naoDeveCriarUbsComUfInvalida() {

        RegraDeNegocioException excecao = assertThrows(
                RegraDeNegocioException.class,
                () -> new Ubs(
                        null,
                        "UBS Central",
                        "1234567",
                        "Rua das Flores, 100",
                        "Santos",
                        "SAO PAULO",
                        true
                )
        );

        assertEquals(
                "A UF deve conter exatamente 2 letras.",
                excecao.getMessage()
        );
    }

    @Test
    void devePadronizarNomeEuf() {

        Ubs ubs = new Ubs(
                null,
                "  UBS Central  ",
                "1234567",
                "Rua das Flores, 100",
                "Santos",
                "sp",
                true
        );

        assertEquals("UBS Central", ubs.getNome());
        assertEquals("SP", ubs.getUf());
    }
}