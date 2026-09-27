package br.com.farmaconecta.domain.model;

import br.com.farmaconecta.domain.exception.RegraDeNegocioException;

public class Medicamento {

    private Long id;
    private String nome;
    private String principioAtivo;
    private String concentracao;
    private String formaFarmaceutica;
    private String unidadeMedida;
    private boolean ativo;

    public Medicamento(
            Long id,
            String nome,
            String principioAtivo,
            String concentracao,
            String formaFarmaceutica,
            String unidadeMedida,
            boolean ativo
    ) {
        validarNome(nome);
        validarPrincipioAtivo(principioAtivo);

        this.id = id;
        this.nome = nome.trim();
        this.principioAtivo = principioAtivo.trim();
        this.concentracao = concentracao;
        this.formaFarmaceutica = formaFarmaceutica;
        this.unidadeMedida = unidadeMedida;
        this.ativo = ativo;
    }

    public void atualizarDados(
            String nome,
            String principioAtivo,
            String concentracao,
            String formaFarmaceutica,
            String unidadeMedida
    ) {
        validarNome(nome);
        validarPrincipioAtivo(principioAtivo);

        this.nome = nome.trim();
        this.principioAtivo = principioAtivo.trim();
        this.concentracao = concentracao;
        this.formaFarmaceutica = formaFarmaceutica;
        this.unidadeMedida = unidadeMedida;
    }

    private void validarNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new RegraDeNegocioException(
                    "O nome do medicamento é obrigatório."
            );
        }
    }

    private void validarPrincipioAtivo(String principioAtivo) {
        if (principioAtivo == null || principioAtivo.isBlank()) {
            throw new RegraDeNegocioException(
                    "O princípio ativo é obrigatório."
            );
        }
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getPrincipioAtivo() {
        return principioAtivo;
    }

    public String getConcentracao() {
        return concentracao;
    }

    public String getFormaFarmaceutica() {
        return formaFarmaceutica;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public boolean isAtivo() {
        return ativo;
    }
}