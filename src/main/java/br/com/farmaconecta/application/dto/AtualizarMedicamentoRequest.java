package br.com.farmaconecta.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AtualizarMedicamentoRequest(

        @NotBlank(message = "O nome do medicamento é obrigatório.")
        @Size(
                max = 150,
                message = "O nome deve ter no máximo 150 caracteres."
        )
        String nome,

        @NotBlank(message = "O princípio ativo é obrigatório.")
        @Size(
                max = 150,
                message = "O princípio ativo deve ter no máximo 150 caracteres."
        )
        String principioAtivo,

        @Size(
                max = 100,
                message = "A concentração deve ter no máximo 100 caracteres."
        )
        String concentracao,

        @Size(
                max = 100,
                message = "A forma farmacêutica deve ter no máximo 100 caracteres."
        )
        String formaFarmaceutica,

        @Size(
                max = 50,
                message = "A unidade de medida deve ter no máximo 50 caracteres."
        )
        String unidadeMedida

) {
}