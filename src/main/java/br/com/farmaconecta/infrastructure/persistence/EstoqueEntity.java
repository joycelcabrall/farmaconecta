package br.com.farmaconecta.infrastructure.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import java.time.LocalDate;

@Entity
@Table(
        name = "estoque",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_estoque_ubs_medicamento_lote_validade",
                        columnNames = {
                                "ubs_id",
                                "medicamento_id",
                                "lote",
                                "validade"
                        }
                )
        }
)
public class EstoqueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ubs_id", nullable = false)
    private Long ubsId;

    @Column(name = "medicamento_id", nullable = false)
    private Long medicamentoId;

    @Column(name = "lote", nullable = false, length = 100)
    private String lote;

    @Column(name = "validade", nullable = false)
    private LocalDate validade;

    @Column(name = "quantidade", nullable = false)
    private int quantidade;

    protected EstoqueEntity() {
    }

    public EstoqueEntity(
            Long id,
            Long ubsId,
            Long medicamentoId,
            String lote,
            LocalDate validade,
            int quantidade
    ) {
        this.id = id;
        this.ubsId = ubsId;
        this.medicamentoId = medicamentoId;
        this.lote = lote;
        this.validade = validade;
        this.quantidade = quantidade;
    }

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