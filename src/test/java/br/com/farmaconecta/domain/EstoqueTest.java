package br.com.farmaconecta.domain;

import br.com.farmaconecta.domain.exception.RegraDeNegocioException;
import br.com.farmaconecta.domain.model.Estoque;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EstoqueTest {

    private Estoque criarEstoque() {
        return new Estoque(
                1L,
                1L,
                1L,
                "DIP2026001",
                LocalDate.of(2027, 12, 31),
                150
        );
    }

    @Test
    void deveAdicionarQuantidadeAoEstoque() {

        Estoque estoque = criarEstoque();

        estoque.adicionarQuantidade(50);

        assertEquals(200, estoque.getQuantidade());
    }

    @Test
    void deveRetirarQuantidadeDoEstoque() {

        Estoque estoque = criarEstoque();

        estoque.retirarQuantidade(30);

        assertEquals(120, estoque.getQuantidade());
    }

    @Test
    void naoDevePermitirSaidaMaiorQueEstoque() {

        Estoque estoque = criarEstoque();

        assertThrows(
                RegraDeNegocioException.class,
                () -> estoque.retirarQuantidade(1000)
        );

        assertEquals(150, estoque.getQuantidade());
    }

    @Test
    void naoDevePermitirEntradaNegativa() {

        Estoque estoque = criarEstoque();

        assertThrows(
                RegraDeNegocioException.class,
                () -> estoque.adicionarQuantidade(-10)
        );

        assertEquals(150, estoque.getQuantidade());
    }

    @Test
    void naoDevePermitirSaidaNegativa() {

        Estoque estoque = criarEstoque();

        assertThrows(
                RegraDeNegocioException.class,
                () -> estoque.retirarQuantidade(-10)
        );

        assertEquals(150, estoque.getQuantidade());
    }
}