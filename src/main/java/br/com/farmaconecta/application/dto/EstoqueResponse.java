package br.com.farmaconecta.application.dto;

import br.com.farmaconecta.domain.model.Estoque;

import java.time.LocalDate;

public record EstoqueResponse(

        Long id,
        Long ubsId,
        Long medicamentoId,
        String lote,
        LocalDate validade,
        int quantidade

) {

    public static EstoqueResponse de(Estoque estoque) {

        return new EstoqueResponse(
                estoque.getId(),
                estoque.getUbsId(),
                estoque.getMedicamentoId(),
                estoque.getLote(),
                estoque.getValidade(),
                estoque.getQuantidade()
        );
    }
}