package br.com.farmaconecta.application.dto;

import br.com.farmaconecta.domain.model.Ubs;

public record UbsResponse(
        Long id,
        String nome,
        String cnes,
        String endereco,
        String cidade,
        String uf,
        boolean ativa
) {

    public static UbsResponse de(Ubs ubs) {
        return new UbsResponse(
                ubs.getId(),
                ubs.getNome(),
                ubs.getCnes(),
                ubs.getEndereco(),
                ubs.getCidade(),
                ubs.getUf(),
                ubs.isAtiva()
        );
    }
}