package br.com.farmaconecta.application.dto;

import br.com.farmaconecta.domain.model.Medicamento;

public record MedicamentoResponse(

        Long id,
        String nome,
        String principioAtivo,
        String concentracao,
        String formaFarmaceutica,
        String unidadeMedida,
        boolean ativo

) {

    public static MedicamentoResponse de(
            Medicamento medicamento
    ) {

        return new MedicamentoResponse(
                medicamento.getId(),
                medicamento.getNome(),
                medicamento.getPrincipioAtivo(),
                medicamento.getConcentracao(),
                medicamento.getFormaFarmaceutica(),
                medicamento.getUnidadeMedida(),
                medicamento.isAtivo()
        );
    }
}