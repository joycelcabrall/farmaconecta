package br.com.farmaconecta.application.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CadastrarEstoqueRequest(

        @NotNull(message = "A UBS é obrigatória.")
        @Positive(message = "O ID da UBS deve ser positivo.")
        Long ubsId,

        @NotNull(message = "O medicamento é obrigatório.")
        @Positive(message = "O ID do medicamento deve ser positivo.")
        Long medicamentoId,

        @NotBlank(message = "O lote é obrigatório.")
        @Size(
                max = 100,
                message = "O lote deve ter no máximo 100 caracteres."
        )
        String lote,

        @NotNull(message = "A validade é obrigatória.")
        @Future(message = "A validade deve ser uma data futura.")
        LocalDate validade,

        @NotNull(message = "A quantidade é obrigatória.")
        @Positive(message = "A quantidade deve ser maior que zero.")
        Integer quantidade

) {
}