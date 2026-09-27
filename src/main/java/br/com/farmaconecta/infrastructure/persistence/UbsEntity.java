package br.com.farmaconecta.infrastructure.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ubs")
public class UbsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 150)
    private String nome;

    @Column(name = "cnes", nullable = false, unique = true, length = 7)
    private String cnes;

    @Column(name = "endereco", length = 255)
    private String endereco;

    @Column(name = "cidade", length = 100)
    private String cidade;

    @Column(name = "uf", nullable = false, length = 2)
    private String uf;

    @Column(name = "ativa", nullable = false)
    private boolean ativa;

    protected UbsEntity() {
        // Construtor utilizado pelo JPA.
    }

    public UbsEntity(
            Long id,
            String nome,
            String cnes,
            String endereco,
            String cidade,
            String uf,
            boolean ativa
    ) {
        this.id = id;
        this.nome = nome;
        this.cnes = cnes;
        this.endereco = endereco;
        this.cidade = cidade;
        this.uf = uf;
        this.ativa = ativa;
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