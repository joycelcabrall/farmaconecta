package br.com.farmaconecta.domain.model;

import br.com.farmaconecta.domain.exception.RegraDeNegocioException;

public class Ubs {

    private Long id;
    private String nome;
    private String cnes;
    private String endereco;
    private String cidade;
    private String uf;
    private boolean ativa;

    public Ubs(
            Long id,
            String nome,
            String cnes,
            String endereco,
            String cidade,
            String uf,
            boolean ativa
    ) {
        validarNome(nome);
        validarCnes(cnes);
        validarUf(uf);

        this.id = id;
        this.nome = nome.trim();
        this.cnes = cnes.trim();
        this.endereco = endereco;
        this.cidade = cidade;
        this.uf = uf.trim().toUpperCase();
        this.ativa = ativa;
    }

    public void atualizarDados(
            String nome,
            String endereco,
            String cidade,
            String uf
    ) {
        validarNome(nome);
        validarUf(uf);

        this.nome = nome.trim();
        this.endereco = endereco;
        this.cidade = cidade;
        this.uf = uf.trim().toUpperCase();
    }

    private void validarNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new RegraDeNegocioException(
                    "O nome da UBS é obrigatório."
            );
        }
    }

    private void validarCnes(String cnes) {
        if (cnes == null || !cnes.matches("\\d{7}")) {
            throw new RegraDeNegocioException(
                    "O CNES deve conter exatamente 7 números."
            );
        }
    }

    private void validarUf(String uf) {
        if (uf == null || !uf.matches("(?i)[A-Z]{2}")) {
            throw new RegraDeNegocioException(
                    "A UF deve conter exatamente 2 letras."
            );
        }
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCnes() {
        return cnes;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public String getUf() {
        return uf;
    }

    public boolean isAtiva() {
        return ativa;
    }
}