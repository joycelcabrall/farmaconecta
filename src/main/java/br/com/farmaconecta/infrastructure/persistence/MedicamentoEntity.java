package br.com.farmaconecta.infrastructure.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "medicamentos")
public class MedicamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 150)
    private String nome;

    @Column(name = "principio_ativo", nullable = false, length = 150)
    private String principioAtivo;

    @Column(name = "concentracao", length = 100)
    private String concentracao;

    @Column(name = "forma_farmaceutica", length = 100)
    private String formaFarmaceutica;

    @Column(name = "unidade_medida", length = 50)
    private String unidadeMedida;

    @Column(name = "ativo", nullable = false)
    private boolean ativo;

    protected MedicamentoEntity() {
    }

    public MedicamentoEntity(
            Long id,
            String nome,
            String principioAtivo,
            String concentracao,
            String formaFarmaceutica,
            String unidadeMedida,
            boolean ativo
    ) {
        this.id = id;
        this.nome = nome;
        this.principioAtivo = principioAtivo;
        this.concentracao = concentracao;
        this.formaFarmaceutica = formaFarmaceutica;
        this.unidadeMedida = unidadeMedida;
        this.ativo = ativo;
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