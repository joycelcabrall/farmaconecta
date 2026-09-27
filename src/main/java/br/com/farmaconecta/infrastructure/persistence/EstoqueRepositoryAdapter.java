package br.com.farmaconecta.infrastructure.persistence;

import br.com.farmaconecta.domain.model.Estoque;
import br.com.farmaconecta.domain.repository.EstoqueRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class EstoqueRepositoryAdapter implements EstoqueRepository {

    private final EstoqueJpaRepository estoqueJpaRepository;

    public EstoqueRepositoryAdapter(
            EstoqueJpaRepository estoqueJpaRepository
    ) {
        this.estoqueJpaRepository = estoqueJpaRepository;
    }

    @Override
    public Estoque salvar(Estoque estoque) {

        EstoqueEntity entity = paraEntity(estoque);

        EstoqueEntity entitySalva =
                estoqueJpaRepository.save(entity);

        return paraDominio(entitySalva);
    }

    @Override
    public Optional<Estoque> buscarPorId(Long id) {

        return estoqueJpaRepository.findById(id)
                .map(this::paraDominio);
    }

    @Override
    public List<Estoque> listarTodos() {

        return estoqueJpaRepository.findAll()
                .stream()
                .map(this::paraDominio)
                .toList();
    }

    @Override
    public List<Estoque> listarPorUbsId(Long ubsId) {

        return estoqueJpaRepository.findAll()
                .stream()
                .filter(entity -> entity.getUbsId().equals(ubsId))
                .map(this::paraDominio)
                .toList();
    }

    @Override
    public Optional<Estoque> buscarPorUbsMedicamentoLoteEValidade(
            Long ubsId,
            Long medicamentoId,
            String lote,
            LocalDate validade
    ) {

        return estoqueJpaRepository
                .findByUbsIdAndMedicamentoIdAndLoteAndValidade(
                        ubsId,
                        medicamentoId,
                        lote,
                        validade
                )
                .map(this::paraDominio);
    }

    private EstoqueEntity paraEntity(Estoque estoque) {

        return new EstoqueEntity(
                estoque.getId(),
                estoque.getUbsId(),
                estoque.getMedicamentoId(),
                estoque.getLote(),
                estoque.getValidade(),
                estoque.getQuantidade()
        );
    }

    private Estoque paraDominio(EstoqueEntity entity) {

        return new Estoque(
                entity.getId(),
                entity.getUbsId(),
                entity.getMedicamentoId(),
                entity.getLote(),
                entity.getValidade(),
                entity.getQuantidade()
        );
    }
}