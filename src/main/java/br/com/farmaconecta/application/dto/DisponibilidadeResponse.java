package br.com.farmaconecta.application.dto;

public record DisponibilidadeResponse(

        Long estoqueId,
        Long ubsId,
        String nomeUbs,
        Long medicamentoId,
        String lote,
        int quantidade

) {
}