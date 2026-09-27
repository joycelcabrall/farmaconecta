package br.com.farmaconecta.domain.model;

import br.com.farmaconecta.domain.exception.RegraDeNegocioException;

import java.time.LocalDate;

public class Estoque {

    private Long id;
    private Long ubsId;
    private Long medicamentoId;
    private String lote;
    private LocalDate validade;
    private int quantidade;

    public Estoque(
            Long id,
            Long ubsId,
            Long medicamentoId,
            String lote,
            LocalDate validade,
            int quantidade
    ) {
        validarUbsId(ubsId);
        validarMedicamentoId(medicamentoId);
        validarLote(lote);
        validarValidade(validade);
        validarQuantidade(quantidade);

        this.id = id;
        this.ubsId = ubsId;
        this.medicamentoId = medicamentoId;
        this.lote = lote.trim();
        this.validade = validade;
        this.quantidade = quantidade;
    }

    private void validarUbsId(Long ubsId) {
        if (ubsId == null || ubsId <= 0) {
            throw new RegraDeNegocioException(
                    "A UBS é obrigatória."
            );
        }
    }

    private void validarMedicamentoId(Long medicamentoId) {
        if (medicamentoId == null || medicamentoId <= 0) {
            throw new RegraDeNegocioException(
                    "O medicamento é obrigatório."
            );
        }
    }

    private void validarLote(String lote) {
        if (lote == null || lote.isBlank()) {
            throw new RegraDeNegocioException(
                    "O lote do medicamento é obrigatório."
            );
        }
    }

    private void validarValidade(LocalDate validade) {
        if (validade == null) {
            throw new RegraDeNegocioException(
                    "A validade do medicamento é obrigatória."
            );
        }
    }

    private void validarQuantidade(int quantidade) {
        if (quantidade < 0) {
            throw new RegraDeNegocioException(
                    "A quantidade em estoque não pode ser negativa."
            );
        }
    }

    // ENTRADA DE MEDICAMENTOS NO ESTOQUE

    public void adicionarQuantidade(int quantidadeEntrada) {

        if (quantidadeEntrada <= 0) {
            throw new RegraDeNegocioException(
                    "A quantidade de entrada deve ser maior que zero."
            );
        }

        if (quantidadeEntrada > Integer.MAX_VALUE - this.quantidade) {
            throw new RegraDeNegocioException(
                    "A quantidade informada excede o limite permitido."
            );
        }

        this.quantidade += quantidadeEntrada;
    }

    // SAÍDA DE MEDICAMENTOS DO ESTOQUE

    public void retirarQuantidade(int quantidadeSaida) {

        if (quantidadeSaida <= 0) {
            throw new RegraDeNegocioException(
                    "A quantidade de saída deve ser maior que zero."
            );
        }

        if (quantidadeSaida > this.quantidade) {
            throw new RegraDeNegocioException(
                    "Estoque insuficiente para realizar a saída."
            );
        }

        this.quantidade -= quantidadeSaida;
    }

    // GETTERS

    public Long getId() {
        return id;
    }

    public Long getUbsId() {
        return ubsId;
    }

    public Long getMedicamentoId() {
        return medicamentoId;
    }

    public String getLote() {
        return lote;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public int getQuantidade() {
        return quantidade;
    }
}