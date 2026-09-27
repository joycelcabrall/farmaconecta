package br.com.farmaconecta.infrastructure.persistence;

import br.com.farmaconecta.domain.model.Medicamento;
import br.com.farmaconecta.domain.repository.MedicamentoRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MedicamentoRepositoryAdapter
        implements MedicamentoRepository {

    private final MedicamentoJpaRepository medicamentoJpaRepository;

    public MedicamentoRepositoryAdapter(
            MedicamentoJpaRepository medicamentoJpaRepository
    ) {
        this.medicamentoJpaRepository = medicamentoJpaRepository;
    }

    @Override
    public Medicamento salvar(Medicamento medicamento) {

        MedicamentoEntity entity = paraEntity(medicamento);

        MedicamentoEntity entitySalva =
                medicamentoJpaRepository.save(entity);

        return paraDominio(entitySalva);
    }

    @Override
    public Optional<Medicamento> buscarPorId(Long id) {

        return medicamentoJpaRepository
                .findById(id)
                .map(this::paraDominio);
    }

    @Override
    public List<Medicamento> listarTodos() {

        return medicamentoJpaRepository
                .findAll()
                .stream()
                .map(this::paraDominio)
                .toList();
    }

    private MedicamentoEntity paraEntity(
            Medicamento medicamento
    ) {

        return new MedicamentoEntity(
                medicamento.getId(),
                medicamento.getNome(),
                medicamento.getPrincipioAtivo(),
                medicamento.getConcentracao(),
                medicamento.getFormaFarmaceutica(),
                medicamento.getUnidadeMedida(),
                medicamento.isAtivo()
        );
    }

    private Medicamento paraDominio(
            MedicamentoEntity entity
    ) {

        return new Medicamento(
                entity.getId(),
                entity.getNome(),
                entity.getPrincipioAtivo(),
                entity.getConcentracao(),
                entity.getFormaFarmaceutica(),
                entity.getUnidadeMedida(),
                entity.isAtivo()
        );
    }
}