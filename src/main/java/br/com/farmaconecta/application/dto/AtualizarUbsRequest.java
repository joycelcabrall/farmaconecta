package br.com.farmaconecta.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AtualizarUbsRequest(

        @NotBlank(message = "O nome da UBS é obrigatório.")
        @Size(
                max = 150,
                message = "O nome deve ter no máximo 150 caracteres."
        )
        String nome,

        @Size(
                max = 255,
                message = "O endereço deve ter no máximo 255 caracteres."
        )
        String endereco,

        @Size(
                max = 100,
                message = "A cidade deve ter no máximo 100 caracteres."
        )
        String cidade,

        @NotBlank(message = "A UF é obrigatória.")
        @Pattern(
                regexp = "(?i)[A-Z]{2}",
                message = "A UF deve conter exatamente 2 letras."
        )
        String uf

) {
}